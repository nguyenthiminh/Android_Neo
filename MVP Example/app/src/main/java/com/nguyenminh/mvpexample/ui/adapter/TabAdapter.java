package com.nguyenminh.mvpexample.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nguyenminh.mvpexample.ui.uis.fragment.FragmentGridView;
import com.nguyenminh.mvpexample.ui.uis.fragment.FragmentListView;
import com.nguyenminh.mvpexample.ui.uis.fragment.FragmentRecyclerView;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new FragmentRecyclerView();
                break;
            case 1:
                fragment = new FragmentListView();
                break;
            case 2:
                fragment = new FragmentGridView();
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return null;
            case 1:
                return "ListView";
            case 2:
                return "GridView";
            default:
                return null;
        }

    }
}
