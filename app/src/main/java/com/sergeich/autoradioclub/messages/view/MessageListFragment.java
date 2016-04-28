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

package com.sergeich.autoradioclub.messages.view;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sergeich.autoradioclub.R;
import com.sergeich.autoradioclub.app.model.Message;
import com.sergeich.autoradioclub.app.view.BaseFragment;
import com.sergeich.autoradioclub.app.view.widget.LoaderView;
import com.sergeich.autoradioclub.messages.MessagesComponent;
import com.sergeich.autoradioclub.messages.presenter.MessageListAdapter;
import com.sergeich.autoradioclub.messages.presenter.MessageListPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import io.reist.visum.model.VisumError;

public class MessageListFragment extends BaseFragment<MessageListPresenter>
        implements MessageListView {

    @Inject
    MessageListPresenter mPresenter;

    @Bind(R.id.recycler)
    RecyclerView mRecyclerView;

    @Bind(R.id.loader)
    LoaderView mLoaderView;

    MessageListAdapter mAdapter;

    public MessageListFragment() {
        super(R.layout.fragment_messages);
    }

    public static MessageListFragment newInstance() {
        return new MessageListFragment();
    }

    @Override
    public void attachPresenter() {
        super.attachPresenter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter = new MessageListAdapter());

        mAdapter.setOnMessageClickListener(message -> playMessage(message.url));
    }

    private void playMessage(String url) {
        // TODO:
    }

    @NonNull
    @Override
    public MessageListPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void inject(Object from) {
        ((MessagesComponent) from).inject(this);
    }

    @Override
    public void displayData(List<Message> messages) {
        mAdapter.setMessages(messages);
        mLoaderView.hide();
    }

    @Override
    public void displayError(VisumError error) {
        if (mAdapter == null || mAdapter.getItemCount() == 0) {
            mLoaderView.showNetworkError();
        } else {
            Snackbar
                    .make(mRecyclerView, R.string.network_error, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.retry, v -> mPresenter.loadData())
                    .show();
        }
    }

    @Override
    public void showLoader(boolean show) {
        mLoaderView.showLoading(show);
    }
}

