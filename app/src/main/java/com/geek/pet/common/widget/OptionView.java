package com.geek.pet.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geek.pet.R;


/**
 * 选项卡
 * Created by LiuLi on 2017/7/15.
 */
public class OptionView extends RelativeLayout {

    private TextView option_right;

    public OptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.include_option_view, this, true);
        TextView option_left = findViewById(R.id.option_left);
        option_right = findViewById(R.id.option_right);


        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.OptionView);
        if (attributes != null) {

            //处理option背景色
            int titleBarBackGround = attributes.getResourceId(R.styleable.
                    OptionView_option_background, R.color.white);
            if (titleBarBackGround != 1) {
                setBackgroundResource(titleBarBackGround);
            }

            //设置左边图片icon
            int leftImageViewDrawable = attributes.getResourceId(R.styleable.
                    OptionView_option_left_ImageView_drawable, -1);
            if (leftImageViewDrawable != -1) {
                Drawable img = context.getResources().getDrawable(leftImageViewDrawable);
                img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                option_left.setCompoundDrawables(img, null, null, null); //设置左图标
            }

            //处理标题
            //获取文字标题
            String titleText = attributes.getString(R.styleable.OptionView_option_title_text);

            if (!TextUtils.isEmpty(titleText)) {
                option_left.setText(titleText);
            }
            //获取标题字体显示颜色/大小
            int titleTextColor = attributes.getColor(R.styleable.OptionView_option_title_text_color,
                    getResources().getColor(R.color.color_option_text));
            if (titleTextColor != -1) {
                option_left.setTextColor(titleTextColor);
            }

//            设置右边图片icon
            int rightImageViewDrawable = attributes.getResourceId(R.styleable.
                    OptionView_option_right_ImageView_drawable, R.drawable.icon_next);
            if (rightImageViewDrawable != -1) {
                Drawable img = context.getResources().getDrawable(rightImageViewDrawable);
                img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                option_right.setCompoundDrawables(null, null, img, null); //设置右图标
            }
            attributes.recycle();
        }
    }

    public void setRightText(String str) {
        option_right.setText(str);
    }

}
