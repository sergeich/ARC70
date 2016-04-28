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

package com.sergeich.autoradioclub.messages.presenter;

import com.sergeich.autoradioclub.app.model.Message;
import com.sergeich.autoradioclub.messages.model.MessageService;
import com.sergeich.autoradioclub.messages.view.MessageListView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reist.visum.model.VisumResponse;
import io.reist.visum.presenter.VisumPresenter;
import rx.Observer;

@Singleton
public class MessageListPresenter extends VisumPresenter<MessageListView> {

    MessageService mMessageService;
    private boolean mIsDataLoaded = false;

    @Inject
    MessageListPresenter(MessageService messageService) {
        mMessageService = messageService;
    }

    @Override
    protected void onViewAttached() {
        mIsDataLoaded = false;

        view().showLoader(true);
        loadData();
    }

    public void loadData() {
        subscribe(mMessageService.list(), new UsersObserver());
    }

    /**
     * Used in test only
     */
    public boolean isDataLoaded() {
        return mIsDataLoaded;
    }

    private class UsersObserver implements Observer<VisumResponse<List<Message>>> {

        @Override
        public void onNext(VisumResponse<List<Message>> response) {
            MessageListView view = view();
            if (response.isSuccessful()) {
                view.displayData(response.getResult());
                view.showLoader(false);
                mIsDataLoaded = true;
            } else {
                view.displayError(response.getError());
            }

        }

        @Override
        public void onCompleted() {}

        @Override
        public void onError(Throwable e) {
            view().showLoader(false);
        }

    }
}

