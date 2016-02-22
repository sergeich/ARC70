package com.sergeich.autoradioclub.app.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import io.reist.visum.model.VisumError;

public class ArcError implements VisumError {

    @SerializedName("message")
    private String message;

    @Nullable
    @Override
    public Throwable getThrowable() {
        return null;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

}

