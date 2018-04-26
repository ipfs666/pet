package com.geek.pet.mvp.common.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.geek.pet.mvp.common.di.module.CaptchaModule;

import com.geek.pet.mvp.common.ui.activity.CaptchaActivity;

@ActivityScope
@Component(modules = CaptchaModule.class, dependencies = AppComponent.class)
public interface CaptchaComponent {
    void inject(CaptchaActivity activity);
}