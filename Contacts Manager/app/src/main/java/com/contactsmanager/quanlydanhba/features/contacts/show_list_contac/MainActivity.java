package com.contactsmanager.quanlydanhba.features.contacts.show_list_contac;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.contactsmanager.quanlydanhba.R;
import com.contactsmanager.quanlydanhba.database.ContactQueryImplementation;
import com.contactsmanager.quanlydanhba.database.QueryContract;
import com.contactsmanager.quanlydanhba.database.QueryResponse;
import com.contactsmanager.quanlydanhba.features.contacts.create_contact.ContactCreateDialogFragment;
import com.contactsmanager.quanlydanhba.features.contacts.ContactCrudListener;
import com.contactsmanager.quanlydanhba.model.UserContact;
import com.contactsmanager.quanlydanhba.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactCrudListener {
    private ContactListAdapter adapter;
    private RecyclerView rcv_contact;
    private List<UserContact> userContactList = new ArrayList<>();
    private ImageView iv_add, iv_deleteAll;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        init();
    }

    private void init() {
        iv_add = findViewById(R.id.iv_add);
        rcv_contact = findViewById(R.id.rcv_contacts);
        iv_deleteAll = findViewById(R.id.iv_deleteAll);

        adapter = new ContactListAdapter(mContext,userContactList, this);
        rcv_contact.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rcv_contact.setAdapter(adapter);

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactCreateDialogFragment dialogFragment = ContactCreateDialogFragment.newInstance("Create Mark", MainActivity.this);
                dialogFragment.show(getSupportFragmentManager(), Constants.CREATE_CONTACT);
            }
        });

        iv_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
        showListContact();
    }

    private void showListContact() {
        QueryContract.ContactQuery query = new ContactQueryImplementation();
        query.readAllContact(new QueryResponse<List<UserContact>>() {
            @Override
            public void onSuccess(List<UserContact> data) {
                rcv_contact.setVisibility(View.VISIBLE);
//                tv_noDataFound.setVisibility(View.GONE);

                userContactList.clear();
                userContactList.addAll(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String message) {
                rcv_contact.setVisibility(View.GONE);
//                tv_noDataFound.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onContactListUpdate(boolean isUpdate) {
        if(isUpdate){
            showListContact();
        }
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setMessage("Are you sure, You wanted to delete all Contacts?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        QueryContract.ContactQuery query = new ContactQueryImplementation();
                        query.deleteAllContact(new QueryResponse<Boolean>() {
                            @Override
                            public void onSuccess(Boolean data) {
                                Toast.makeText(mContext, "Contacts is deleted successfully", Toast.LENGTH_SHORT).show();
                                showListContact();
                            }

                            @Override
                            public void onFailure(String message) {
                                Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
