package com.geek.pet.mvp.housewifery.presenter;

import android.app.Application;

import com.geek.pet.api.utils.RxUtil;
import com.geek.pet.mvp.housewifery.contract.HomeServicesContract;
import com.geek.pet.storage.BaseArrayData;
import com.geek.pet.storage.entity.housewifery.HomeServiceBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class HomeServicesPresenter extends BasePresenter<HomeServicesContract.Model, HomeServicesContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    HomeServicesPresenter(HomeServicesContract.Model model, HomeServicesContract.View rootView) {
        super(model, rootView);
    }

    /**
     * 家政服务项目列表
     */
    public void homeServiceList() {
        mModel.homeServiceList().retryWhen(new RetryWithDelay(3, 2))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.endRefresh();//隐藏下拉刷新的进度条
                }).compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .compose(RxUtil.handleBaseResult(mAppManager.getTopActivity()))
                .subscribeWith(new ErrorHandleSubscriber<BaseArrayData<HomeServiceBean>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull BaseArrayData<HomeServiceBean> arrayData) {
                        mRootView.updateView(arrayData.getPageData());
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}
