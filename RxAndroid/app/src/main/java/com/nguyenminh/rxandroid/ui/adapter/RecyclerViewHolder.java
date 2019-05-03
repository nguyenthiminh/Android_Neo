package com.nguyenminh.rxandroid.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nguyenminh.rxandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_image)
    CircleImageView iv_image;

    @BindView(R.id.tv_name)
    TextView tv_name;

    RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
