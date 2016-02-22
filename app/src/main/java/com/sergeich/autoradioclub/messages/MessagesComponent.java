package com.sergeich.autoradioclub.messages;

import com.sergeich.autoradioclub.messages.view.MessageListFragment;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent
public interface MessagesComponent {

    void inject(MessageListFragment userFragment);

}

