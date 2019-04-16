package com.contactsmanager.quanlydanhba.features.contacts.show_list_contac;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.contactsmanager.quanlydanhba.R;
import com.github.abdularis.civ.AvatarImageView;

class ContactViewHolder extends RecyclerView.ViewHolder {
    TextView tv_name, tv_phoneNumber;
    AvatarImageView iv_avata;
    ImageView iv_edit, iv_delete;
    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.tv_name);
        tv_phoneNumber = itemView.findViewById(R.id.tv_phoneNumber);

        iv_avata = itemView.findViewById(R.id.iv_avata);
        iv_edit = itemView.findViewById(R.id.iv_edit);
        iv_delete = itemView.findViewById(R.id.iv_delete);
    }
}
