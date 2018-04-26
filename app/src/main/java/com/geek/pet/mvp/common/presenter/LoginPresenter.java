package com.geek.pet.mvp.common.presenter;

import com.geek.pet.api.utils.RxUtil;
import com.geek.pet.common.utils.Constants;
import com.geek.pet.mvp.common.contract.LoginContract;
import com.geek.pet.storage.entity.UserBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.DataHelper;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    private RxErrorHandler mErrorHandler;
    private AppManager mAppManager;

    @Inject
    LoginPresenter(LoginContract.Model model, LoginContract.View rootView
            , RxErrorHandler handler, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
    }

    /**
     * 登录
     *
     * @param account     用户名、手机号
     * @param md5Password 密码
     */
    public void login(String account, String md5Password) {
        mModel.login(account, md5Password)
                .retryWhen(new RetryWithDelay(3, 2))
                .compose(RxUtil.applySchedulers(mRootView))
                .compose(RxUtil.handleBaseResult(mAppManager.getTopActivity()))
                .subscribeWith(new ErrorHandleSubscriber<UserBean>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull UserBean userBean) {
                        DataHelper.setStringSF(mAppManager.getTopActivity(), Constants.SP_TOKEN, userBean.getToken());
                        DataHelper.saveDeviceData(mAppManager.getTopActivity(), Constants.SP_USER_INFO, userBean);
                        mRootView.killMyself();
//                        RongIM.connect("xkvZfF3zvY//gwZCOerYDS/mvXZv/KNkR8ZJyEKI9cfUyZ1DuYjwlfAxq9vCrgF7ND6pM9jyzfw="
//                                , new RongIMClient.ConnectCallback() {
//                                    @Override
//                                    public void onTokenIncorrect() {
//                                        Timber.d("=====融云TokenIncorrect");
//                                    }
//
//                                    @Override
//                                    public void onSuccess(String s) {
//                                        Timber.d("=====融云Success：" + s);
//                                        DataHelper.setStringSF(mAppManager.getTopActivity(), Constants.SP_TOKEN, userBean.getToken());
//                                        DataHelper.saveDeviceData(mAppManager.getTopActivity(), Constants.SP_USER_INFO, userBean);
//                                        mRootView.killMyself();
//                                    }
//
//                                    @Override
//                                    public void onError(RongIMClient.ErrorCode errorCode) {
//                                        Timber.d("=====融云errorCode：" + errorCode);
//                                    }
//                                });

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
