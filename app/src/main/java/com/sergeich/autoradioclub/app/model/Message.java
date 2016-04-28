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

package com.sergeich.autoradioclub.app.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;
import com.sergeich.autoradioclub.app.model.local.MessageTable;

@JsonPropertyOrder({ "date", "time", "duration", "url" })
@StorIOSQLiteType(table = MessageTable.NAME)
public class Message {

    @StorIOSQLiteColumn(name = MessageTable.Column.ID, key = true)
    public Long id;

    @StorIOSQLiteColumn(name = MessageTable.Column.TIME)
    public long time;

    @StorIOSQLiteColumn(name = MessageTable.Column.URL)
    public String url;

    @StorIOSQLiteColumn(name = MessageTable.Column.DURATION)
    public int duration;

}

