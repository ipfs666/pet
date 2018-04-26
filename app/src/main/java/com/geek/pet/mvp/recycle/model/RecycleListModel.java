package com.geek.pet.mvp.recycle.model;

import com.geek.pet.api.BaseApi;
import com.geek.pet.mvp.recycle.contract.RecycleListContract;
import com.geek.pet.storage.BaseArrayData;
import com.geek.pet.storage.BaseResponse;
import com.geek.pet.storage.entity.recycle.ArticleBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class RecycleListModel extends BaseModel implements RecycleListContract.Model {

    @Inject
    RecycleListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseResponse<BaseArrayData<ArticleBean>>> articleList(int pageNumber, int pageSize,
                                                                           String type, String category) {
        return mRepositoryManager.obtainRetrofitService(BaseApi.class).articleList(pageNumber,
                pageSize, type, category);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}