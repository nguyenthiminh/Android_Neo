package com.nguyenminh.mvpexample.ui.uis.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenminh.mvpexample.R;
import com.nguyenminh.mvpexample.model.entity.ItemImage;
import com.nguyenminh.mvpexample.presenter.RecyclerViewPresenter;
import com.nguyenminh.mvpexample.ui.adapter.RecyclerAdapter;
import com.nguyenminh.mvpexample.ui.viewvm.MainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentRecyclerView extends Fragment implements MainView {
    @BindView(R.id.rcv)
    RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private RecyclerViewPresenter presenter;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RecyclerViewPresenter();
        presenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        presenter.loadData();
    }

    private void initView() {
        adapter = new RecyclerAdapter(getContext(), presenter.listImage);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showList(List<ItemImage> itemImages) {
        if (!(itemImages == null && itemImages.isEmpty() )){
            presenter.listImage.clear();
            presenter.listImage.addAll(itemImages);
            adapter.notifyDataSetChanged();
        } else {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
