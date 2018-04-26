package com.geek.pet.mvp.housewifery.model;

import android.app.Application;

import com.geek.pet.api.BaseApi;
import com.geek.pet.mvp.housewifery.contract.HomeServicesContract;
import com.geek.pet.storage.BaseArrayData;
import com.geek.pet.storage.BaseResponse;
import com.geek.pet.storage.entity.housewifery.HomeServiceBean;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class HomeServicesModel extends BaseModel implements HomeServicesContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    HomeServicesModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponse<BaseArrayData<HomeServiceBean>>> homeServiceList() {
        return mRepositoryManager.obtainRetrofitService(BaseApi.class).homeServiceList();
    }
}