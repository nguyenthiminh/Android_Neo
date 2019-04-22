package com.nguyenminh.mvpexample.ui.uis.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.nguyenminh.mvpexample.R;
import com.nguyenminh.mvpexample.model.entity.ItemImage;
import com.nguyenminh.mvpexample.presenter.GridViewPresenter;
import com.nguyenminh.mvpexample.ui.adapter.GridViewAdapter;
import com.nguyenminh.mvpexample.ui.viewvm.MainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentGridView extends Fragment implements MainView {
    @BindView(R.id.gridView)
    GridView gridView;

    private GridViewAdapter adapter;
    private GridViewPresenter gridViewPresenter;
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gridview, container, false);
        unbinder = ButterKnife.bind(this, v);
        initPresenter();
        return v;
    }

    private void initPresenter() {
        gridViewPresenter = new GridViewPresenter(this);
        gridViewPresenter.loadData();
    }

    @Override
    public void showList(List<ItemImage> itemImages) {
        adapter = new GridViewAdapter(itemImages);
        gridView.setNumColumns(2);
        gridView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
