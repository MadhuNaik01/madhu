package com.example.program_21;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class  MeetingDb extends SQLiteOpenHelper {

    public static String DATABASE_NAME="provideragenda";
    public static String TABLE_NAME="agenda";

    public MeetingDb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table agenda (agenda_date TEXT, agenda_time TEXT, agenda_content TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
