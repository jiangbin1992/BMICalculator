package com.expresstemplate.bmicalc;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class SqliteHelper extends SQLiteAssetHelper {
    private static final String DB_NAME = "Database.db";
    private static final int DB_VER = 1;

    public SqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

}
