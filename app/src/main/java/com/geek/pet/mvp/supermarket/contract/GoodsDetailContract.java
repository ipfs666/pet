package com.geek.pet.mvp.supermarket.contract;

import com.geek.pet.storage.BaseArrayData;
import com.geek.pet.storage.BaseResponse;
import com.geek.pet.storage.entity.shop.GoodsBean;
import com.geek.pet.storage.entity.SingleResultBean;
import com.geek.pet.storage.entity.shop.SpecificationBean;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Observable;

public interface GoodsDetailContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void updateFavoriteState(boolean isFavorite);

        void updateView(List<SpecificationBean> specificationList);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<BaseResponse<SingleResultBean>> goodsHasFavorite(String token, String goods_sn);

        Observable<BaseResponse<SingleResultBean>> goodsFavoriteAdd(String token, String goods_sn);

        Observable<BaseResponse<SingleResultBean>> goodsFavoriteDelete(String token, String goods_sn);

        Observable<BaseResponse<BaseArrayData<SpecificationBean>>> goodsSpecification(String goods_sn);

        Observable<BaseResponse<GoodsBean>> cartAdd(String token, String productId, int quantity);
    }
}
