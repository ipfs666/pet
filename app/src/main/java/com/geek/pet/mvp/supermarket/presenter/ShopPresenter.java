package com.geek.pet.mvp.supermarket.presenter;

import com.geek.pet.api.utils.RxUtil;
import com.geek.pet.storage.BaseArrayData;
import com.geek.pet.storage.entity.shop.CategoryBean;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import io.reactivex.annotations.NonNull;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;

import com.geek.pet.mvp.supermarket.contract.ShopContract;


@ActivityScope
public class ShopPresenter extends BasePresenter<ShopContract.Model, ShopContract.View> {
    private RxErrorHandler mErrorHandler;
    private AppManager mAppManager;

    @Inject
    ShopPresenter(ShopContract.Model model, ShopContract.View rootView, RxErrorHandler handler, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
    }

    public void getGoodsCategorys() {
        mModel.goodsCategoryRoot().retryWhen(new RetryWithDelay(0, 30))
                .compose(RxUtil.applySchedulers(mRootView))
                .compose(RxUtil.handleBaseResult(mAppManager.getTopActivity()))
                .subscribeWith(new ErrorHandleSubscriber<BaseArrayData<CategoryBean>>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull BaseArrayData<CategoryBean> categoryBaseArrayData) {
                        mRootView.setViewPager(categoryBaseArrayData.getPageData());
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
    }

}
