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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gridViewPresenter = new GridViewPresenter();
        gridViewPresenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gridview, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        gridViewPresenter.loadData();
    }

    private void init() {
        adapter = new GridViewAdapter(gridViewPresenter.itemImages);
        gridView.setNumColumns(2);
        gridView.setAdapter(adapter);
    }

    @Override
    public void showList(List<ItemImage> itemImages) {
       if(!(itemImages == null && itemImages.isEmpty())){
           gridViewPresenter.itemImages.clear();
           gridViewPresenter.itemImages.addAll(itemImages);
           adapter.notifyDataSetChanged();
       }
       else {

       }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
