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

