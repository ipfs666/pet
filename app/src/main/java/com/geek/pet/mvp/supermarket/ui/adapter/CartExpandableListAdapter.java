package com.geek.pet.mvp.supermarket.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.geek.pet.R;
import com.geek.pet.storage.entity.shop.MerchantBean;
import com.geek.pet.storage.entity.shop.ProductBean;
import com.jess.arms.http.imageloader.glide.GlideArms;

import java.util.List;

public class CartExpandableListAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<MerchantBean> modelList;
    private CartEditControl cartEditControl;

    public CartExpandableListAdapter(Context context, CartEditControl cartEditControl, List<MerchantBean> list) {
        mContext = context;
        modelList = list;
        this.cartEditControl = cartEditControl;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return modelList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return modelList.get(groupPosition).getItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return modelList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return modelList.get(groupPosition).getItems().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = mInflater.inflate(R.layout.item_shop_merchant, null);
            groupViewHolder.tvShopName = convertView.findViewById(R.id.tv_shop_name);
            groupViewHolder.ivShop = convertView.findViewById(R.id.iv_shop);
            groupViewHolder.tvOrderStatus = convertView.findViewById(R.id.tv_order_status);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.tvOrderStatus.setVisibility(View.GONE);
        groupViewHolder.tvShopName.setText(modelList.get(groupPosition).getName());
        GlideArms.with(mContext).load(modelList.get(groupPosition).getHeadURL()).circleCrop()
                .error(R.drawable.icon_user_head)
                .into(groupViewHolder.ivShop)
        ;
        convertView.setPadding(0, 20, 0, 0);//设置列表间距
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder holder;
        if (convertView == null) {
            holder = new ChildViewHolder();
            convertView = mInflater.inflate(R.layout.item_shop_cart, null);
            holder.ivProduct = convertView.findViewById(R.id.iv_product);
            holder.tvProductName = convertView.findViewById(R.id.tv_product_name);
            holder.tvProductSpecifications = convertView.findViewById(R.id.tv_product_specifications);
            holder.ivDelete = convertView.findViewById(R.id.iv_delete);
            holder.tvProductPrice = convertView.findViewById(R.id.tv_product_price);
            holder.tvQuantity = convertView.findViewById(R.id.tvQuantity);
            holder.tvPlus = convertView.findViewById(R.id.tv_plus);
            holder.tvReduce = convertView.findViewById(R.id.tv_reduce);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }

        ProductBean productInfo = modelList.get(groupPosition).getItems().get(childPosition).getProduct();

        GlideArms.with(mContext).load(productInfo.getGoods_image()).centerCrop().into(holder.ivProduct);

        holder.tvProductName.setText(productInfo.getGoods_name());
        if (productInfo.getSpecifications() == null || productInfo.getSpecifications().size() == 0) {
            holder.tvProductSpecifications.setText("");
        } else {
            holder.tvProductSpecifications.setText(productInfo.getSpecification());
        }
//        holder.mTvCaption.setText(productInfo.getGoods_caption());
        holder.tvProductPrice.setText(String.format("￥%s", productInfo.getPrice()));
        holder.tvQuantity.setText(String.valueOf(modelList.get(groupPosition).getItems()
                .get(childPosition).getQuantity()));
        holder.tvPlus.setOnClickListener(new MyListener(groupPosition, childPosition));
        holder.tvReduce.setOnClickListener(new MyListener(groupPosition, childPosition));
        holder.ivDelete.setOnClickListener(new MyListener(groupPosition, childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class MyListener implements View.OnClickListener {

        int groupPosition;
        int childPosition;

        MyListener(int groupPosition, int childPosition) {
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_plus:
                    cartEditControl.plus(groupPosition, childPosition);
                    break;
                case R.id.tv_reduce:
                    cartEditControl.reduce(groupPosition, childPosition);
                    break;
                case R.id.iv_delete:
                    cartEditControl.delete(groupPosition, childPosition);
                    break;
                default:
                    break;
            }
        }

    }

    //加减方法内部接口
    public interface CartEditControl {
        void plus(int groupPosition, int childPosition);

        void reduce(int groupPosition, int childPosition);

        void delete(int groupPosition, int childPosition);
    }

    private class GroupViewHolder {
        TextView tvShopName;//地址
        ImageView ivShop;
        TextView tvOrderStatus;
    }

    private class ChildViewHolder {
        private ImageView ivProduct;
        private TextView tvProductName;//商品名
        private TextView tvProductSpecifications;
        private TextView tvProductPrice; // 售价
        //        private TextView mTvCaption; // 介绍
        private TextView tvQuantity;//数量
        private TextView tvReduce;
        private TextView tvPlus;
        private ImageView ivDelete;

    }
}