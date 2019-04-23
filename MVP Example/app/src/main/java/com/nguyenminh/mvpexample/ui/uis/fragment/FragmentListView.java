package com.nguyenminh.mvpexample.ui.uis.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nguyenminh.mvpexample.R;
import com.nguyenminh.mvpexample.model.entity.ItemImage;
import com.nguyenminh.mvpexample.presenter.ListViewPresenter;
import com.nguyenminh.mvpexample.ui.adapter.ListViewAdapter;
import com.nguyenminh.mvpexample.ui.viewvm.MainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentListView extends Fragment implements MainView {
    private ListViewAdapter adapter;
    private ListViewPresenter listViewPresenter;
    @BindView(R.id.listview)
    ListView listView;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listViewPresenter = new ListViewPresenter();
        listViewPresenter.attachView(this);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listview, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        listViewPresenter.loadData();
    }

    private void initView() {
        adapter = new ListViewAdapter(listViewPresenter.imageList);
        listView.setAdapter(adapter);
    }

    @Override
    public void showList(List<ItemImage> itemImages) {
        if(!(itemImages == null && itemImages.isEmpty())){
            listViewPresenter.imageList.clear();
            listViewPresenter.imageList.addAll(itemImages);
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
