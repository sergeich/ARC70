package com.sergeich.autoradioclub.messages.model.local;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;
import com.sergeich.autoradioclub.app.model.Message;
import com.sergeich.autoradioclub.app.model.local.MessageTable;
import com.sergeich.autoradioclub.messages.model.MessageService;

import java.util.List;

import io.reist.visum.model.BaseResponse;
import io.reist.visum.model.VisumResponse;
import io.reist.visum.model.local.StorIoService;
import rx.Observable;

public class StorIoMessageService extends StorIoService<Message>
        implements MessageService {

    public StorIoMessageService(StorIOSQLite storIoSqLite) {
        super(storIoSqLite);
    }

    @Override
    public Observable<? extends VisumResponse<Message>> byId(Long id) {
        return unique(Message.class, MessageTable.NAME, id)
                .map(BaseResponse<Message>::new);
    }

    @Override
    public Observable<VisumResponse<Integer>> delete(Long id) {
        throw new IllegalStateException("Unsupported");
    }

    @Override
    public Observable<VisumResponse<List<Message>>> list() {
        return preparedGetBuilder(Message.class)
                .withQuery(Query
                        .builder()
                        .table(MessageTable.NAME)
                        .orderBy(MessageTable.Column.ID)
                        .build())
                .prepare()
                .createObservable()
                .map(BaseResponse::new);
    }
}
