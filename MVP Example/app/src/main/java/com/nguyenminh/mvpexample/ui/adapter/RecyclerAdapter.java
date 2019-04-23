package com.nguyenminh.mvpexample.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nguyenminh.mvpexample.R;
import com.nguyenminh.mvpexample.model.entity.ItemImage;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context context;
    private List<ItemImage> itemImages;

    public RecyclerAdapter(Context context, List<ItemImage> itemImages) {
        this.context = context;
        this.itemImages = itemImages;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ItemImage itemImage = itemImages.get(position);

        Glide.with(holder.itemView).load(itemImage.getUrl()).into(holder.iv_image);
        holder.tv_name.setText(itemImage.getName());
    }

    @Override
    public int getItemCount() {
        return itemImages.size();
    }

}

class RecyclerViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_image)
    CircleImageView iv_image;

    @BindView(R.id.tv_name)
    TextView tv_name;

    RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}