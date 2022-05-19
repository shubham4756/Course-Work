package com.exmple.savecontactsandmessages;

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

    public static final String MESSAGE_TABLE_NAME = "message";
    public static final String MESSAGE_ID = "id";
    public static final String MESSAGE_COLUMN_BODY = "body";
    public static final String MESSAGE_COLUMN_ID = "personId";

    public DatabaseHandler(@Nullable Context context) {
        super(context,DATABASE_NAME,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table contact ( id INTEGER PRIMARY KEY AUTOINCREMENT, name text not null, phone text not null ,email text not null ,address text not null);");
        sqLiteDatabase.execSQL("Create table message ( id INTEGER PRIMARY KEY AUTOINCREMENT, body text not null, personId int not null, FOREIGN KEY (personId) REFERENCES contact(id)  ON DELETE CASCADE );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CONTACT_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MESSAGE_TABLE_NAME);
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

    public boolean insertMessage (String body, int personId) {
        boolean isSuccess = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(MESSAGE_COLUMN_BODY, body);
        content.put(MESSAGE_COLUMN_ID,personId);
        isSuccess = db.insert(MESSAGE_TABLE_NAME,null,content) > 0;
        db.close();
        return isSuccess;
    }

    public Cursor getMessageById(String messageId, String personId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("select * from message where id = " + messageId + " and personId = " + personId,null);
    }

    public Cursor getMessages(String personId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("select * from message where personId = " + personId,null);
    }

    public void updateData(int id, String body, String personId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update message set body = '" + body + "' where id = " + id  + " and personId = " + personId);
    }

    public void deleteMessage(String s, String personId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from message where id = " + s  + " and personId = " + personId);
    }

    public Cursor resentMessage(String s) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM message WHERE id = (SELECT MAX(id)  FROM message where personId = "+ s +");", null);
    }
}
