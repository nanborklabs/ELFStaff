package com.elfstaff.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.elfstaff.Adapter.ReportPagerAdapter;
import com.elfstaff.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 3/8/16.
 */
public class ReportFragment extends android.support.v4.app.Fragment {
    private  View mView;
    @BindView(R.id.radio_gro)
    RadioGroup mSubject_selected;
    @BindView(R.id.report_tab)
    TabLayout mtabLayout;
    @BindView(R.id.report_pager)
    ViewPager mPager;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        return super.getLayoutInflater(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.report_fragment,container,false);
        ButterKnife.bind(this,mView);


        mPager.setAdapter(new ReportPagerAdapter(getChildFragmentManager()));
        return mView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
