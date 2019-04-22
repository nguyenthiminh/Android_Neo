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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listview, container, false);
        unbinder = ButterKnife.bind(this, v);
        initPresenter();
        return v;
    }

    private void initPresenter() {
        listViewPresenter = new ListViewPresenter(this);
        listViewPresenter.loadData();
    }
    @Override
    public void showList(List<ItemImage> itemImages) {
        adapter = new ListViewAdapter(itemImages);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
