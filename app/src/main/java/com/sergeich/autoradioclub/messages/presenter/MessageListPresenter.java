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

