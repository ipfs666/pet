package com.geek.pet.mvp.common.model;

import com.geek.pet.storage.BaseArrayData;
import com.geek.pet.storage.BaseResponse;
import com.geek.pet.storage.entity.BannerBean;
import com.geek.pet.api.BaseApi;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.geek.pet.mvp.common.contract.MainContract;

import io.reactivex.Observable;


@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {

    @Inject
    MainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<BaseResponse<BaseArrayData<BannerBean>>> articleBanner() {
        return mRepositoryManager.obtainRetrofitService(BaseApi.class).articleBanner();
    }
}