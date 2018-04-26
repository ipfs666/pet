package com.geek.pet.mvp.recycle.model;

import com.geek.pet.api.BaseApi;
import com.geek.pet.mvp.recycle.contract.RecycleAddContract;
import com.geek.pet.storage.BaseResponse;
import com.geek.pet.storage.entity.recycle.ArticleBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class RecycleAddModel extends BaseModel implements RecycleAddContract.Model {

    @Inject
    RecycleAddModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<BaseResponse<ArticleBean>> recycleAdd(String token,String category,String content) {
        return  mRepositoryManager.obtainRetrofitService(BaseApi.class).articleAdd(token, category, content);
    }
}