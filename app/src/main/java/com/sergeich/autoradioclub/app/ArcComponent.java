package com.sergeich.autoradioclub.app;

import com.sergeich.autoradioclub.messages.MessagesComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ArcModule.class)
public interface ArcComponent {

    MessagesComponent messagesComponent();

}

