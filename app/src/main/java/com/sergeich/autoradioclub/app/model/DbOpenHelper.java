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
