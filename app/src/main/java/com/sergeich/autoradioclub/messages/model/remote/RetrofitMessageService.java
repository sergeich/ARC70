package com.sergeich.autoradioclub.messages.model.remote;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.sergeich.autoradioclub.app.model.Message;
import com.sergeich.autoradioclub.app.model.QueryDate;
import com.sergeich.autoradioclub.app.model.remote.ArcApi;
import com.sergeich.autoradioclub.messages.model.MessageService;

import java.util.GregorianCalendar;
import java.util.List;

import io.reist.visum.model.VisumResponse;
import io.reist.visum.model.remote.RetrofitService;
import rx.Observable;

public class RetrofitMessageService extends RetrofitService<Message> implements MessageService {

    protected final ArcApi arcApi;

    public RetrofitMessageService(ArcApi arcApi) {
        this.arcApi = arcApi;
    }

    @RxLogObservable
    @Override
    public Observable<? extends VisumResponse<List<Message>>> list() {
        return arcApi.messagesByDate(new QueryDate(new GregorianCalendar(2015, 12, 01).getTime()));
    }

    @RxLogObservable
    @Override
    public VisumResponse<Message> saveSync(Message message) {
        throw new IllegalStateException("Unsupported");
    }

    @RxLogObservable
    @Override
    public Observable<VisumResponse<Message>> byId(Long id) {
        throw new IllegalStateException("Unsupported");
    }

    @RxLogObservable
    @Override
    public Observable<VisumResponse<Integer>> delete(Long id) {
        throw new IllegalStateException("Unsupported");
    }

    @RxLogObservable
    @Override
    public VisumResponse<List<Message>> saveSync(List<Message> list) {
        throw new IllegalStateException("Unsupported");
    }

}

