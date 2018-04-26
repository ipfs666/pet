package com.geek.pet.mvp.supermarket.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.geek.pet.mvp.supermarket.di.module.GoodsDetailModule;

import com.geek.pet.mvp.supermarket.ui.activity.GoodsDetailActivity;

@ActivityScope
@Component(modules = GoodsDetailModule.class, dependencies = AppComponent.class)
public interface GoodsDetailComponent {
    void inject(GoodsDetailActivity activity);
}