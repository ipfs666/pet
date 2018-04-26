package com.geek.pet.mvp.supermarket.model;

import com.geek.pet.api.BaseApi;
import com.geek.pet.storage.BaseArrayData;
import com.geek.pet.storage.BaseResponse;
import com.geek.pet.storage.entity.shop.GoodsBean;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.geek.pet.mvp.supermarket.contract.GoodsListContract;

import io.reactivex.Observable;

@ActivityScope
public class GoodsListModel extends BaseModel implements GoodsListContract.Model {

    @Inject
    GoodsListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<BaseResponse<BaseArrayData<GoodsBean>>> goodsList(int category_id, int pageNum) {
        return mRepositoryManager.obtainRetrofitService(BaseApi.class).goodsList(category_id, pageNum);
    }
}