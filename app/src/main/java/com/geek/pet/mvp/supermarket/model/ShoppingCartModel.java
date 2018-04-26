package com.geek.pet.mvp.supermarket.model;

import com.geek.pet.api.BaseApi;
import com.geek.pet.mvp.supermarket.contract.ShoppingCartContract;
import com.geek.pet.mvp.supermarket.ui.activity.CartEditResultBean;
import com.geek.pet.storage.BaseResponse;
import com.geek.pet.storage.entity.shop.CartBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class ShoppingCartModel extends BaseModel implements ShoppingCartContract.Model {

    @Inject
    ShoppingCartModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<BaseResponse<CartBean>> cartList(String token) {
        return mRepositoryManager.obtainRetrofitService(BaseApi.class).cartList(token);
    }

    @Override
    public Observable<BaseResponse<CartEditResultBean>> cartEdit(String token, String id, int quantity) {
        return  mRepositoryManager.obtainRetrofitService(BaseApi.class).cartEdit(token, id, quantity);
    }

    @Override
    public Observable<BaseResponse<CartEditResultBean>> cartDelete(String token, String id) {
        return  mRepositoryManager.obtainRetrofitService(BaseApi.class).cartDelete(token, id);
    }

    @Override
    public Observable<BaseResponse<CartEditResultBean>> cartClear(String token) {
        return  mRepositoryManager.obtainRetrofitService(BaseApi.class).cartClear(token);
    }
}