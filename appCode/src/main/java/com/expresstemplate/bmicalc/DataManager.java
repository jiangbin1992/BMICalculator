package com.expresstemplate.bmicalc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    Context context;

    public DataManager(Context context) {
        this.context = context;

    }

    public void InsertWord(String bmi, String height, String weight) {

        SqliteHelper sqliteHelper = new SqliteHelper(context);
        SQLiteDatabase db1 = sqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bmi", bmi);
        values.put("height", height);
        values.put("weight", weight);
        db1.insert("tblData", null, values);
        db1.close();
    }

    public List<Word> getAllHistoryData() {
        SqliteHelper sqliteHelper = new SqliteHelper(context);
        SQLiteDatabase db1 = sqliteHelper.getWritableDatabase();
        Cursor cur = db1.rawQuery("select * from tblData ORDER BY id DESC;", null);
        List<Word> item_data = new ArrayList<Word>();
        if (cur.getCount() != 0) {
            if (cur.moveToFirst()) {
                do {
                    Word obj = new Word();
                    obj.id = cur.getString(cur.getColumnIndex("id"));
                    obj.bmi = cur.getString(cur.getColumnIndex("bmi"));
                    obj.height = cur.getString(cur.getColumnIndex("height"));
                    obj.weight = cur.getString(cur.getColumnIndex("weight"));
                    item_data.add(obj);
                } while (cur.moveToNext());
            }
        }
        return item_data;
    }

    public List<Word> getDeleteHistoryData() {
        List<Word> item_data = new ArrayList<Word>();
        SqliteHelper sqliteHelper = new SqliteHelper(context);
        SQLiteDatabase db1 = sqliteHelper.getReadableDatabase();
        String deleteQuery = "delete from tblData";
        db1.execSQL(deleteQuery);
        db1.close();
        return item_data;
    }
}
