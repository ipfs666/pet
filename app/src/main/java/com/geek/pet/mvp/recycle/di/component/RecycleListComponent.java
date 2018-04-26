package com.geek.pet.mvp.recycle.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.geek.pet.mvp.recycle.di.module.RecycleListModule;

import com.geek.pet.mvp.recycle.ui.activity.RecycleListActivity;

@ActivityScope
@Component(modules = RecycleListModule.class, dependencies = AppComponent.class)
public interface RecycleListComponent {
    void inject(RecycleListActivity activity);
}