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

import android.content.Context;
import android.support.annotation.NonNull;

import com.sergeich.autoradioclub.app.model.local.MessageTable;

import io.reist.visum.model.local.BaseDbHelper;

public class DbOpenHelper extends BaseDbHelper {

    private static final String DATABASE_NAME = "autoradioclub";
    private static final int DATABASE_VERSION = 1;

    public DbOpenHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, DATABASE_VERSION);
        addTable(MessageTable.class);
    }

}
