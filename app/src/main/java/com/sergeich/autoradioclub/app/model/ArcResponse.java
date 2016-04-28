/*
 * Copyright (c) 2016 Sergey Glotov.
 *
 *
 * This file is part of ARC70.
 *
 * ARC70 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ARC70 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ARC70.  If not, see <http://www.gnu.org/licenses/>.
 */

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

