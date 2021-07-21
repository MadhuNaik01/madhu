package com.example.program_21;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AgendaProvider extends ContentProvider {

    SQLiteDatabase db;
    MeetingDb dbHelper;

    static final String AUTHORITY = "com.example.vtucontentprovider";
    static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/agenda");

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.vtucontentprovider", "agenda",1);
    }

    @Override
    public boolean onCreate() {

        dbHelper = new MeetingDb(getContext(),MeetingDb.DATABASE_NAME+".db",null,1);
        if(db != null) {
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        String sqlstr = "select * from agenda where agenda_date = ?";
        db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        cursor = db.rawQuery(sqlstr, selectionArgs);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        db = dbHelper.getWritableDatabase();
        long id = db.insert(MeetingDb.TABLE_NAME,null, values);

        if(id>0)
        {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(_uri,null);
            db.close();
            return  _uri;
        }
        else
        {
            Toast.makeText(getContext(),"ROW INSERT FAILED", Toast.LENGTH_SHORT).show();
            db.close();
            return  null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
