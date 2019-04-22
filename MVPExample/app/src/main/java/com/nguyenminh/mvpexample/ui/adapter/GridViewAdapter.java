package com.nguyenminh.mvpexample.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nguyenminh.mvpexample.R;
import com.nguyenminh.mvpexample.model.entity.ItemImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class GridViewAdapter extends BaseAdapter {
    private List<ItemImage> itemImages;

    public GridViewAdapter(List<ItemImage> itemImages) {
        this.itemImages = itemImages;
    }


    @Override
    public int getCount() {
        if (itemImages == null) {
            return 0;
        }
        return itemImages.size();
    }

    @Override
    public Object getItem(int position) {
        return itemImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolderGridView holder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.item_image, viewGroup, false);
        ItemImage itemImage = itemImages.get(position);
        holder = new ViewHolderGridView(view);
        Glide.with(viewGroup).load(itemImage.getUrl()).into(holder.iv_image);
        holder.tv_name.setText(itemImage.getName());
        return view;
    }
}
class ViewHolderGridView {
    @BindView(R.id.iv_image)
    CircleImageView iv_image;

    @BindView(R.id.tv_name) TextView tv_name;

    public ViewHolderGridView(View view) {
        ButterKnife.bind(this, view);
    }
}
