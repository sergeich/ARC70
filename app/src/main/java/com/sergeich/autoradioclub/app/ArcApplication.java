package com.sergeich.autoradioclub.app;

import android.app.Application;

import io.reist.visum.ComponentCache;
import io.reist.visum.ComponentCacheProvider;

public class ArcApplication extends Application implements ComponentCacheProvider {

    private ComponentCache componentCache;

    @Override
    public ComponentCache getComponentCache() {
        if (componentCache == null) {
            componentCache = new ArcComponentCache(this);
        }
        return componentCache;
    }

    public void setComponentCache(ComponentCache componentCache) {
        this.componentCache = componentCache;
    }

}

