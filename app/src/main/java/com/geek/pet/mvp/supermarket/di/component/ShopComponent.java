package com.geek.pet.mvp.supermarket.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.geek.pet.mvp.supermarket.di.module.ShopModule;

import com.geek.pet.mvp.supermarket.ui.activity.ShopActivity;

@ActivityScope
@Component(modules = ShopModule.class, dependencies = AppComponent.class)
public interface ShopComponent {
    void inject(ShopActivity activity);
}