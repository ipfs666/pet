package com.geek.pet.mvp.person.contract;

import com.geek.pet.storage.BaseResponse;
import com.geek.pet.storage.entity.shop.ReceiverBean;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import io.reactivex.Observable;

public interface ReceiverAddContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void setCityWheel(String address);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<BaseResponse<ReceiverBean>> receiverSave(String token, String consignee, String areaName, String address, String zipCode, String phone, boolean isDefault, String areaId);

        Observable<BaseResponse<ReceiverBean>> receiverUpdate(String token, String consignee, String areaName, String address, String zipCode, String phone, boolean isDefault, String areaId, String id, String oId);
    }
}
