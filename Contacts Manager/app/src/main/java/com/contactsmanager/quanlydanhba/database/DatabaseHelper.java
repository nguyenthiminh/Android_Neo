package com.contactsmanager.quanlydanhba.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.contactsmanager.quanlydanhba.utils.MyApp;

import static com.contactsmanager.quanlydanhba.utils.Constants.TABLE_CONTACT;
import static com.contactsmanager.quanlydanhba.utils.Constants.USER_ID;
import static com.contactsmanager.quanlydanhba.utils.Constants.USER_NAME;
import static com.contactsmanager.quanlydanhba.utils.Constants.USER_PHONE_NUMBER;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contact-db";
    private static final int DATABASE_VERSION = 1;

    private static DatabaseHelper databaseHelper;

    private DatabaseHelper() {
        super(MyApp.context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance() {

        if (databaseHelper == null) {
            synchronized (DatabaseHelper.class){
                if (databaseHelper == null)
                    databaseHelper = new DatabaseHelper();
            }
        }

        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_CONTACT + "("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_NAME + " TEXT NOT NULL, "
                + USER_PHONE_NUMBER + " TEXT NOT NULL"
                + ")";

        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);

        onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        db.execSQL("PRAGMA foreign_keys=ON;");
    }
}
