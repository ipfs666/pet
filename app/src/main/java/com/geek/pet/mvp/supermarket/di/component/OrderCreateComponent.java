package com.geek.pet.mvp.supermarket.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.geek.pet.mvp.supermarket.di.module.OrderCreateModule;

import com.geek.pet.mvp.supermarket.ui.activity.OrderCreateActivity;

@ActivityScope
@Component(modules = OrderCreateModule.class, dependencies = AppComponent.class)
public interface OrderCreateComponent {
    void inject(OrderCreateActivity activity);
}