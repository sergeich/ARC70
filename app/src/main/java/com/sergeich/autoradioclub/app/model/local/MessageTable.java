package com.sergeich.autoradioclub.app.model.local;

import io.reist.visum.model.local.BaseTable;

public class MessageTable extends BaseTable {

    public static final String NAME = "messages";

    public interface Column extends BaseTable.Column {
        String TIME = "time";
        String DURATION = "duration";
        String URL = "url";
    }

    private static final String CREATE_TABLE = "create table " + NAME + "(" +
            Column.ID + " integer not null primary key, " +
            Column.REVISION + " integer," +
            Column.TIME + " integer, " +
            Column.DURATION + " integer, " +
            Column.URL + " text " +
            ")";

    @Override
    public String getCreateTableQuery() {
        return CREATE_TABLE;
    }

    @Override
    public String[] getUpgradeTableQueries(int oldVersion) {
        switch (oldVersion) {
            default:
                return new String[0];
        }
    }

}
