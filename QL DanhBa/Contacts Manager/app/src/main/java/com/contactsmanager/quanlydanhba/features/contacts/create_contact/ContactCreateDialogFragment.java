package com.contactsmanager.quanlydanhba.features.contacts.create_contact;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.contactsmanager.quanlydanhba.R;
import com.contactsmanager.quanlydanhba.database.ContactQueryImplementation;
import com.contactsmanager.quanlydanhba.database.QueryContract;
import com.contactsmanager.quanlydanhba.database.QueryResponse;
import com.contactsmanager.quanlydanhba.features.contacts.ContactCrudListener;
import com.contactsmanager.quanlydanhba.model.UserContact;
import com.contactsmanager.quanlydanhba.utils.Constants;

public class ContactCreateDialogFragment extends DialogFragment {

    private EditText edt_name, edt_phoneNumber;
    private Button btn_create, btn_cancel;
    private static ContactCrudListener contactCrudListener;
    private int id;

    public ContactCreateDialogFragment() {
    }

    public static ContactCreateDialogFragment newInstance(String title, ContactCrudListener listener){
        contactCrudListener = listener;
        ContactCreateDialogFragment contactCreateDialogFragment = new ContactCreateDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        contactCreateDialogFragment.setArguments(args);

        contactCreateDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        return contactCreateDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_create_dailog, container, false);

        edt_name = view.findViewById(R.id.edt_name);
        edt_phoneNumber = view.findViewById(R.id.edt_phoneNumber);

        edt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edt_name.length() == 0){
                    edt_name.setError("Not null");
                }
                else {
                    edt_name.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edt_phoneNumber.length() == 0){
                    edt_phoneNumber.setError("Not null");
                }
                else {
                    edt_phoneNumber.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btn_create = view.findViewById(R.id.btn_create);
        btn_cancel = view.findViewById(R.id.btn_cancel);

        String title = getArguments().getString(Constants.TITLE);
        id = getArguments().getInt("id");
        getDialog().setTitle(title);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_name.getText().toString().trim();
                String phoneNumber = edt_phoneNumber.getText().toString().trim();

                if(name.length() == 0 || phoneNumber.length() == 0){
                    edt_name.setError("Not null");
                    edt_phoneNumber.setError("Not null");
                }
                else {

                    final UserContact userContact = new UserContact(-1, name, phoneNumber);

                    QueryContract.ContactQuery query = new ContactQueryImplementation();
                    query.createContact(userContact, new QueryResponse<Boolean>() {
                        @Override
                        public void onSuccess(Boolean data) {
                            getDialog().dismiss();
                            contactCrudListener.onContactListUpdate(true);
                            Toast.makeText(getContext(), "Contact created successfully", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(String message) {
                            contactCrudListener.onContactListUpdate(false);
                            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                            Log.d("erorr", message.toString());
                        }
                    });
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            //noinspection ConstantConditions
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.DialogAnimation);
        }
    }
}
