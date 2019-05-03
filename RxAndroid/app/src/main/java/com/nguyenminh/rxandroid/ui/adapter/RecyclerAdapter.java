package com.nguyenminh.rxandroid.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nguyenminh.rxandroid.R;
import com.nguyenminh.rxandroid.model.entity.Person;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context context;
    private List<Person> personList;

    public RecyclerAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Person person = personList.get(position);

        Glide.with(holder.itemView).load(person.getAvatar()).into(holder.iv_image);
        holder.tv_name.setText(person.getLogin());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

}
