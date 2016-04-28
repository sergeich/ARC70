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

package com.sergeich.autoradioclub.app.model.remote;

import com.sergeich.autoradioclub.app.model.ArcResponse;
import com.sergeich.autoradioclub.app.model.Message;
import com.sergeich.autoradioclub.app.model.QueryDate;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ArcApi {

    @GET("/archive.php")
    Observable<ArcResponse<List<Message>>> messagesByDate(@Query("date") QueryDate date);

}

