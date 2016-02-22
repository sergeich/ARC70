package com.sergeich.autoradioclub.messages.view;

import com.sergeich.autoradioclub.app.model.Message;

import java.util.List;

import io.reist.visum.model.VisumError;

public interface MessageListView {

    void showLoader(boolean show);

    void displayError(VisumError error);

    void displayData(List<Message> users);

}
