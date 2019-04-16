package com.contactsmanager.quanlydanhba.database;

import com.contactsmanager.quanlydanhba.model.UserContact;

import java.util.List;

public class QueryContract {

    public interface ContactQuery {
        void createContact(UserContact userContact, QueryResponse<Boolean> response);
        void readAllContact(QueryResponse<List<UserContact>> response);
        void updateContact(UserContact userContact, QueryResponse<Boolean> response);
        void deleteContact(int contactId, QueryResponse<Boolean> response);
        void deleteAllContact(QueryResponse<Boolean> response);
    }

}