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

public class ListViewAdapter extends BaseAdapter {
    private List<ItemImage> itemImages;

    public ListViewAdapter(List<ItemImage> itemImages) {
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
        ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.item_image, viewGroup, false);

        viewHolder = new ViewHolder(view);


        ItemImage itemImage = itemImages.get(position);

        Glide.with(viewGroup).load(itemImage.getUrl()).into(viewHolder.iv_image);
        viewHolder.tv_name.setText(itemImage.getName());

        return view;
    }

}

class ViewHolder {
    @BindView(R.id.iv_image) CircleImageView iv_image;

    @BindView(R.id.tv_name) TextView tv_name;

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}