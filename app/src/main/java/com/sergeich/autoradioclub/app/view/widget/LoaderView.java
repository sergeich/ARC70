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

package com.sergeich.autoradioclub.app.view.widget;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sergeich.autoradioclub.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by defuera on 10/11/2015.
 */
public class LoaderView extends FrameLayout {

    @Bind(R.id.progress_bar)
    View progressBar;

    @Bind(R.id.message)
    View message;

    private OnClickListener retryClickListener;

    public LoaderView(Context context) {
        super(context);
        init();
    }

    public LoaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_loader, this);
        ButterKnife.bind(this);
    }

    public void showLoading(boolean show) {
        setVisibility(show ? VISIBLE : GONE);
        message.setVisibility(GONE);
        setOnClickListener(null);
    }

    public void hide() {
        setVisibility(GONE);
        setOnClickListener(null);
    }

    public void showNetworkError() {
        setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        message.setVisibility(VISIBLE);
        setOnClickListener(v -> {
            if (retryClickListener != null) {
                if (!isNetworkAvailable()) {
                    Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
                } else {
                    showLoading(true);
                    retryClickListener.onClick(v);
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    public void setOnRetryClickListener(OnClickListener retryClickListener) {
        this.retryClickListener = retryClickListener;
    }
}
