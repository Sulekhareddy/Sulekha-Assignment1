package example.code.assignment1;


import android.content.ContentValues;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class SqliteHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Login_db";


    public static final int DATABASE_VERSION = 1;


    public static final String TABLE_USERS = "users";

    public static final String KEY_ID = "id";



    public static final String KEY_EMAIL = "email";


    public static final String KEY_PASSWORD = "password";

    public static final String KEY_CONTENT = "Content";


    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }


    public void addUser(User user) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();


        values.put(KEY_EMAIL, user.getmEmail());


        values.put(KEY_PASSWORD, user.getmPassword());


        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.e("SQLLite", " authenticate " + user.getmEmail());
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.getmEmail()},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {

            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2));


            if (user.getmPassword().equalsIgnoreCase(user1.getmPassword())) {
                return user1;
            }
        }


        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {

            return true;
        }


        return false;
    }



    }
