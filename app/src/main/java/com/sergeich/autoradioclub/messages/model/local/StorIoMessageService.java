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
