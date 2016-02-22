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

