package com.sergeich.autoradioclub.app.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import io.reist.visum.model.VisumResponse;

public class ArcResponse<T> implements VisumResponse<T> {

    @SerializedName("result")
    private T result;

    @SerializedName("error")
    private ArcError error;

    public ArcResponse(T result) {
        this.result = result;
    }

    @Nullable
    @Override
    public T getResult() {
        return result;
    }

    @Nullable
    @Override
    public ArcError getError() {
        return error;
    }

    @Override
    public boolean isSuccessful() {
        return error == null;
    }

}

