package com.exmple.savecontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ContactDatabase.db";
    public static final String CONTACT_TABLE_NAME = "contact";
    public static final String CONTACT_COLUMN_ID = "id";
    public static final String CONTACT_COLUMN_NAME = "name";
    public static final String CONTACT_COLUMN_PHONE = "phone";
    public static final String CONTACT_COLUMN_EMAIL = "email";
    public static final String CONTACT_COLUMN_ADDRESS = "address";

    public DatabaseHandler(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table contact ( id INTEGER PRIMARY KEY AUTOINCREMENT, name text not null, phone text not null ,email text not null ,address text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CONTACT_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData (String name, String phone, String email, String address) {
        boolean isSuccess = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(CONTACT_COLUMN_NAME, name);
        content.put(CONTACT_COLUMN_PHONE,phone);
        content.put(CONTACT_COLUMN_EMAIL, email);
        content.put(CONTACT_COLUMN_ADDRESS,address);
        isSuccess = db.insert(CONTACT_TABLE_NAME,null,content) > 0;
        db.close();
        return isSuccess;
    }

    public Cursor getData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("select * from contact",null);
    }

    public Cursor getDataById(String s) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String str = "select * from contact where id = " + s;
        return sqLiteDatabase.rawQuery(str ,null);
    }

    public void updateData(int id, String name, String phone, String email, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update contact set name = '" + name + "', phone = '" + phone + "', email = '" + email + "', address = '" + address + "' where id = " + id);
    }

    public void deleteData(String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from contact where id = " + s);
    }
}
