package com.contactsmanager.quanlydanhba.features.contacts.show_list_contac;

import android.content.Context;
import android.content.DialogInterface;
import android.service.autofill.FieldClassification;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import com.contactsmanager.quanlydanhba.R;
import com.contactsmanager.quanlydanhba.database.ContactQueryImplementation;
import com.contactsmanager.quanlydanhba.database.QueryContract;
import com.contactsmanager.quanlydanhba.database.QueryResponse;
import com.contactsmanager.quanlydanhba.features.contacts.ContactCrudListener;
import com.contactsmanager.quanlydanhba.features.contacts.update_contact.ContactUpdateDialogFragment;
import com.contactsmanager.quanlydanhba.model.UserContact;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.contactsmanager.quanlydanhba.utils.Constants.UPDATE_CONTACT;

public class ContactListAdapter extends RecyclerView.Adapter<ContactViewHolder> implements Filterable {

    private Context context;
    private List<UserContact> userContactList;
    private ContactCrudListener listener;
    private List<UserContact> userContactListFull;

    public ContactListAdapter(Context context, List<UserContact> userContactList, ContactCrudListener listener) {
        this.context = context;
        this.userContactList = userContactList;
        this.listener = listener;

        userContactListFull = userContactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contacts, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        int[] androidColors = holder.itemView.getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];

        final UserContact userContact = userContactList.get(position);

        holder.tv_name.setText(userContact.getName());
        holder.tv_phoneNumber.setText(userContact.getPhoneNumber());

        holder.iv_avata.setTextColor(randomAndroidColor);
        holder.iv_avata.setStrokeColor(randomAndroidColor);
        holder.iv_avata.setText(userContact.getName().substring(0,2));


        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
            public void onClick(View v) {
                ContactUpdateDialogFragment dialogFragment = ContactUpdateDialogFragment.newInstance(userContact, "Update Contact", new ContactCrudListener() {
                    @Override
                    public void onContactListUpdate(boolean isUpdate) {
                        listener.onContactListUpdate(isUpdate);
                    }
                });
                dialogFragment.show(((MainActivity)context).getSupportFragmentManager(), UPDATE_CONTACT);
            }
        });

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog(userContact.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return userContactList.size();
    }

    private void showConfirmationDialog(final int contactId) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("Are you sure, You wanted to delete this Contact?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        QueryContract.ContactQuery query = new ContactQueryImplementation();
                        query.deleteContact(contactId, new QueryResponse<Boolean>() {
                            @Override
                            public void onSuccess(Boolean data) {
                                listener.onContactListUpdate(data);
                                Toast.makeText(context, "Contact is deleted successfully", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(String message) {
                                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<UserContact> userContactList1 = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    userContactList1.addAll(userContactListFull);
                }
                if (constraint != null && constraint.toString().length() > 0){
                    for (UserContact user : userContactListFull) {
                        if (user.getName().trim().toLowerCase().contains(constraint.toString().trim().toLowerCase())) {
                            userContactList1.add(user);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = userContactList1;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                userContactList.clear();
                userContactList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

}
