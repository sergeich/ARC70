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


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryDate {
    private static final ThreadLocal<DateFormat> DF = new ThreadLocal<DateFormat>() {
        @Override
        public DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    private final Date date;

    public QueryDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return DF.get().format(date);
    }
}