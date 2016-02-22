package com.sergeich.autoradioclub.messages;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.sergeich.autoradioclub.app.model.remote.ArcApi;
import com.sergeich.autoradioclub.messages.model.CachedMessageService;
import com.sergeich.autoradioclub.messages.model.MessageService;
import com.sergeich.autoradioclub.messages.model.local.StorIoMessageService;
import com.sergeich.autoradioclub.messages.model.remote.RetrofitMessageService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MessagesModule {

    @Singleton
    @Provides
    protected MessageService messageService(ArcApi arcApi, StorIOSQLite storIOSQLite) {
        return new CachedMessageService(new StorIoMessageService(storIOSQLite), new RetrofitMessageService(arcApi));
    }

}
