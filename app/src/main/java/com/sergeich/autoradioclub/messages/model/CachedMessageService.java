package com.sergeich.autoradioclub.messages.model;

import com.sergeich.autoradioclub.app.model.Message;

import io.reist.visum.model.CachedService;

public class CachedMessageService
        extends CachedService<Message>
        implements MessageService {

    public CachedMessageService(MessageService local, MessageService remote) {
        super(local, remote);
    }

}

