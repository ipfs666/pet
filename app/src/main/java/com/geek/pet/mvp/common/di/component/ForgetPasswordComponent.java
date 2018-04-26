package com.geek.pet.mvp.common.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.geek.pet.mvp.common.di.module.ForgetPasswordModule;

import com.geek.pet.mvp.common.ui.activity.ForgetPasswordActivity;

@ActivityScope
@Component(modules = ForgetPasswordModule.class, dependencies = AppComponent.class)
public interface ForgetPasswordComponent {
    void inject(ForgetPasswordActivity activity);
}