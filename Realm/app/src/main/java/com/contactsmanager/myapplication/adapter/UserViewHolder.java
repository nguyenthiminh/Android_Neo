package com.contactsmanager.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.contactsmanager.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

class UserViewHolder extends RecyclerView.ViewHolder {
    CircleImageView iv_avatar;
    TextView tv_login, tv_url;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        iv_avatar = itemView.findViewById(R.id.iv_avatar);

        tv_login = itemView.findViewById(R.id.tv_login);
        tv_url = itemView.findViewById(R.id.tv_url);
    }
}
