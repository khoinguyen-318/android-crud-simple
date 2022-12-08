package com.example.baithi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="QLSV1.db";
    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table =
                String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER, %s FLOAT)"
                        , FieldColumn.TABLE, FieldColumn.MA_SV, FieldColumn.HO_TEN,FieldColumn.GIOI_TINH, FieldColumn.DIEM);
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_table = String.format("DROP TABLE IF EXISTS %s", FieldColumn.TABLE);
        db.execSQL(drop_table);
        onCreate(db);
    }
}
