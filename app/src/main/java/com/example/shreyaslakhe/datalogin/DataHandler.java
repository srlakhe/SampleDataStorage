package com.example.shreyaslakhe.datalogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHREYAS LAKHE on 29-06-2015.
 */
public class DataHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "users";

    private static final String TABLE_USER_INFO = "user_info";

    private static final String KEY_ID = "KEY_ID";
    private static final String FIRST_NAME = "FIRST_NAME";
    private static final String MIDDLE_NAME = "MIDDLE_NAME";
    private static final String LAST_NAME = "LAST_NAME";
    private static final String MOBILE = "MOBILE";
    private static final String ADDRESS = "ADDRESS";

    public DataHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_INFO_TABLE = "CREATE TABLE user_info (KEY_ID INTEGER PRIMARY KEY ASC" +
                " AUTOINCREMENT NOT NULL, FIRST_NAME TEXT NOT NULL, MIDDLE_NAME TEXT NOT NULL," +
                " LAST_NAME TEXT NOT NULL, MOBILE BIGINT NOT NULL, ADDRESS TEXT NOT NULL)";

        db.execSQL(CREATE_USER_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USER_INFO);
        onCreate(db);
    }

    public boolean checkIfInfoExists(String first_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER_INFO, new String[] {"KEY_ID"}, FIRST_NAME + "=?", new String[] {first_name},
                null,null,null,null);
        if(cursor.getCount() > 0) {
            db.close();
            cursor.close();
            return true;
        } else {
            db.close();
            cursor.close();
            return false;
        }
    }
    public void addInfo(Info info) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME, info.getFirst_name());
        values.put(MIDDLE_NAME, info.getMiddle_name());
        values.put(LAST_NAME, info.getLast_name());
        values.put(MOBILE, info.getMobile());
        values.put(ADDRESS, info.getAddress());

        db.insert(TABLE_USER_INFO, null, values);
        db.close();
    }

    public Info getInfo(String first_name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER_INFO, new String[]{ "KEY_ID, FIRST_NAME, MIDDLE_NAME," +
                "LAST_NAME, MOBILE, ADDRESS"}, FIRST_NAME +"=?", new String[] {first_name}, null
                ,null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        Info info = new Info(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2)
                        ,cursor.getString(3), cursor.getString(4), cursor.getString(5));
        cursor.close();
        db.close();
        return info;
    }

    public void deleteInfo(String first_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_INFO, FIRST_NAME + "=?", new String[]{first_name});
        db.close();
    }

    public List<String> getAllInfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER_INFO;
        Cursor cursor = db.rawQuery(query, null);
        List<String> myList = new ArrayList<String>();
        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                myList.add(name);
            } while(cursor.moveToNext());
        }
        return myList;
    }
}
