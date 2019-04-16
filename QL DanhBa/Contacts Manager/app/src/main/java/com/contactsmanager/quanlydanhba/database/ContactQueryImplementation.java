package com.contactsmanager.quanlydanhba.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.contactsmanager.quanlydanhba.model.UserContact;

import java.util.ArrayList;
import java.util.List;

import static com.contactsmanager.quanlydanhba.utils.Constants.TABLE_CONTACT;
import static com.contactsmanager.quanlydanhba.utils.Constants.USER_ID;
import static com.contactsmanager.quanlydanhba.utils.Constants.USER_NAME;
import static com.contactsmanager.quanlydanhba.utils.Constants.USER_PHONE_NUMBER;


public class ContactQueryImplementation implements QueryContract.ContactQuery  {

    private DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
    @Override
    public void createContact(UserContact userContact, QueryResponse<Boolean> response) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesForUser(userContact);

        try {
            long id = sqLiteDatabase.insertOrThrow(TABLE_CONTACT, null, contentValues);
            if(id>0) {
                response.onSuccess(true);
                userContact.setId((int) id);
            }
            else
                response.onFailure("Failed to create contact. Unknown Reason!");
        } catch (SQLiteException e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }

    private ContentValues getContentValuesForUser(UserContact userContact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, userContact.getName());
        contentValues.put(USER_PHONE_NUMBER, userContact.getPhoneNumber());
        return contentValues;
    }

    @Override
    public void readAllContact(QueryResponse<List<UserContact>> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        List<UserContact> userContactList = new ArrayList<>();

        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(TABLE_CONTACT, null, null, null, null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                do {
                    UserContact userContact = getUserFromCursor(cursor);
                    userContactList.add(userContact);
                } while (cursor.moveToNext());

                response.onSuccess(userContactList);
            } else
                response.onFailure("There are no user in database");

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
            if(cursor!=null)
                cursor.close();
        }
    }

    private UserContact getUserFromCursor(Cursor cursor) {

        int id = cursor.getInt(cursor.getColumnIndex(USER_ID));
        String name = cursor.getString(cursor.getColumnIndex(USER_NAME));
        String phoneNumber = cursor.getString(cursor.getColumnIndex(USER_PHONE_NUMBER));

        return new UserContact(id, name, phoneNumber);
    }

    @Override
    public void updateContact(UserContact userContact, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesForUser(userContact);

        try {
            long rowCount = sqLiteDatabase.update(TABLE_CONTACT, contentValues,
                    USER_ID + " =? ", new String[]{String.valueOf(userContact.getId())});
            if(rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("No data is updated at all");
        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void deleteContact(int contactId, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            long rowCount = sqLiteDatabase.delete(TABLE_CONTACT, USER_ID + " =? ",
                    new String[]{String.valueOf(contactId)});

            if(rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("Failed to delete contact. Unknown reason");
        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void deleteAllContact(QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            long rowCount = sqLiteDatabase.delete(TABLE_CONTACT, null,null);

            if(rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("Failed to delete contact. Unknown reason");
        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }
}