package com.contactsmanager.quanlydanhba.features.contacts.update_contact;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
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

import static com.contactsmanager.quanlydanhba.utils.Constants.TITLE;

public class ContactUpdateDialogFragment extends DialogFragment {

    private static ContactCrudListener contactCrudListener;
    private EditText edt_name, edt_phoneNumber;

    private Button btn_update, btn_cancel;

    private static UserContact userContact;

    public ContactUpdateDialogFragment() {
    }

    public static ContactUpdateDialogFragment newInstance(UserContact con, String title, ContactCrudListener listener) {
        userContact = con;
        contactCrudListener = listener;
        ContactUpdateDialogFragment markUpdateDialogFragment = new ContactUpdateDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        markUpdateDialogFragment.setArguments(args);

        markUpdateDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        return markUpdateDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_update_dialog, container, false);
        String title = getArguments().getString(TITLE);
        getDialog().setTitle(title);

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



        btn_update = view.findViewById(R.id.btn_update);
        btn_cancel = view.findViewById(R.id.btn_cancel);


        edt_name.setText(userContact.getName());
        edt_phoneNumber.setText(String.valueOf(userContact.getPhoneNumber()));

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_name.getText().toString().trim();

                String phoneNumber = edt_phoneNumber.getText().toString().trim();

                userContact.setName(name);
                userContact.setPhoneNumber(phoneNumber);

                QueryContract.ContactQuery markQuery = new ContactQueryImplementation();
                markQuery.updateContact(userContact, new QueryResponse<Boolean>() {
                    @Override
                    public void onSuccess(Boolean data) {
                        getDialog().dismiss();
                        contactCrudListener.onContactListUpdate(data);
                        Toast.makeText(getContext(), "Contact updated successfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(String message) {
                        contactCrudListener.onContactListUpdate(false);
                        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                    }
                });

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
        }
    }

}
