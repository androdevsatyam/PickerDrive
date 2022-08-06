package com.hpweb.pickerdrive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.model.UserModel;

public class DB {
    Context context;
    DBHELPER dbhelper;
    SQLiteDatabase db;
    ContentValues cv;
    Cursor cursor;

    public DB(Context context) {
        this.context = context;
        dbhelper = new DBHELPER(context, "Logistic", null, 1);
    }


    public void setUser(UserModel user) {
        db = dbhelper.getWritableDatabase();

        cv = new ContentValues();
        if (user.getUser() != null)
            cv.put(dbhelper.ID, user.getEmail());
        else
            cv.put(dbhelper.ID, user.getEmail());

        cv.put(dbhelper.name, user.getName());
        cv.put(dbhelper.email, user.getEmail());
        cv.put(dbhelper.phone, user.getPhone());

        db.delete(dbhelper.tbl_user, null, null);

        db.insert(dbhelper.tbl_user, null, cv);
    }

    public UserModel getUser() {
        UserModel userModel = null;
        db = dbhelper.getReadableDatabase();
        String query = "SELECT * FROM " + dbhelper.tbl_user;
        cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                userModel = new UserModel(cursor.getString(cursor.getColumnIndex(dbhelper.ID)), cursor.getString(cursor.getColumnIndex(dbhelper.name)), cursor.getString(cursor.getColumnIndex(dbhelper.email)), cursor.getString(cursor.getColumnIndex(dbhelper.phone)));
            } else
                userModel = null;
            cursor.close();
        }
        db.close();
        return userModel;
    }

    public class DBHELPER extends SQLiteOpenHelper {

        String tbl_user = "UserInfo", name = "Name", ID = "ID", email = "Email", phone = "Mobile";
        String createuser = "CREATE TABLE IF NOT EXISTS " + tbl_user + " ( " + ID + " text, " + name + " text, " + email + " text, " + phone + " text );";


        public DBHELPER(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(createuser);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
