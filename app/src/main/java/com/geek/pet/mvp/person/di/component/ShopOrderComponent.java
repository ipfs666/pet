package com.geek.pet.mvp.person.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.geek.pet.mvp.person.di.module.ShopOrderModule;

import com.geek.pet.mvp.person.ui.fragment.ShopOrderFragment;

@ActivityScope
@Component(modules = ShopOrderModule.class, dependencies = AppComponent.class)
public interface ShopOrderComponent {
    void inject(ShopOrderFragment fragment);
}