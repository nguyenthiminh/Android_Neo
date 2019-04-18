package com.contactsmanager.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.contactsmanager.myapplication.R;
import com.contactsmanager.myapplication.model.User;
import com.contactsmanager.myapplication.model.UserDetail;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private Context context;
    private List<User> userList;
    private Listener listener;

    public UserListAdapter(Context context, List<User> userList, Listener listener) {
        this.context = context;
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final User user = userList.get(position);

        Glide.with(holder.itemView).load(user.getAvatar()).into(holder.iv_avatar);
        holder.tv_login.setText(user.getLogin());
        holder.tv_url.setText(user.getUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickItemUser(user.getUrl(), user.getLogin());
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public interface Listener{
        void onClickItemUser(String url, String login);
    }
}
