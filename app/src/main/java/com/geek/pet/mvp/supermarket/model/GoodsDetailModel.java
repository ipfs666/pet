package com.geek.pet.mvp.supermarket.model;

import com.geek.pet.api.BaseApi;
import com.geek.pet.storage.BaseArrayData;
import com.geek.pet.storage.BaseResponse;
import com.geek.pet.storage.entity.shop.GoodsBean;
import com.geek.pet.storage.entity.SingleResultBean;
import com.geek.pet.storage.entity.shop.SpecificationBean;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.geek.pet.mvp.supermarket.contract.GoodsDetailContract;

import io.reactivex.Observable;


@ActivityScope
public class GoodsDetailModel extends BaseModel implements GoodsDetailContract.Model {

    @Inject
    GoodsDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
    @Override
    public Observable<BaseResponse<SingleResultBean>> goodsHasFavorite(String token, String goods_sn) {
        return mRepositoryManager.obtainRetrofitService(BaseApi.class).goodsHasFavorite(token, goods_sn);
    }

    @Override
    public Observable<BaseResponse<SingleResultBean>> goodsFavoriteAdd(String token, String goods_sn) {
        return mRepositoryManager.obtainRetrofitService(BaseApi.class).goodsFavoriteAdd(token, goods_sn);
    }

    @Override
    public Observable<BaseResponse<SingleResultBean>> goodsFavoriteDelete(String token, String goods_sn) {
        return mRepositoryManager.obtainRetrofitService(BaseApi.class).goodsFavoriteDelete(token, goods_sn);
    }

    @Override
    public Observable<BaseResponse<BaseArrayData<SpecificationBean>>> goodsSpecification(String goods_sn) {
        return mRepositoryManager.obtainRetrofitService(BaseApi.class).goodsSpecification(goods_sn);
    }

    @Override
    public Observable<BaseResponse<GoodsBean>> cartAdd(String token, String goods_sn, int quantity) {
        return mRepositoryManager.obtainRetrofitService(BaseApi.class).cartAdd(token, goods_sn,quantity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}