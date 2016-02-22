package com.sergeich.autoradioclub.app;

import android.content.Context;

import com.sergeich.autoradioclub.messages.view.MessageListFragment;

import io.reist.visum.ComponentCache;
import io.reist.visum.view.VisumClient;

public class ArcComponentCache extends ComponentCache {

    private final ArcComponent arcComponent;

    public ArcComponentCache(Context context) {
        this(DaggerArcComponent.builder().arcModule(new ArcModule(context)).build());
    }

    public ArcComponentCache(ArcComponent arcComponent) {
        this.arcComponent = arcComponent;
    }

    @Override
    public Object buildComponentFor(Class<? extends VisumClient> viewClass) {
        if (MessageListFragment.class.isAssignableFrom(viewClass)) {
            return arcComponent.messagesComponent();
        } else {
            throw new RuntimeException("Unknown view class: " + viewClass.getName());
        }
    }

}

