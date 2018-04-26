package com.geek.pet.mvp.housewifery.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.geek.pet.mvp.housewifery.di.module.HomeServicesModule;

import com.geek.pet.mvp.housewifery.ui.activity.HomeServicesActivity;

@ActivityScope
@Component(modules = HomeServicesModule.class, dependencies = AppComponent.class)
public interface HomeServicesComponent {
    void inject(HomeServicesActivity activity);
}