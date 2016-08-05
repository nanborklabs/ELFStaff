package com.elfstaff.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.elfstaff.Adapter.ReportPagerMarkAdapter;
import com.elfstaff.R;
import com.elfstaff.model.ReportModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 5/8/16.
 *
 */
public class ReportPagerFragment extends Fragment {


    @BindView(R.id.report_pager_list)
    RecyclerView mlist;
    @BindView(R.id.week_radio_group)
    RadioGroup mRadioGroup;


    public View mView;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      mView=inflater.inflate(R.layout.report_fragment_pager_layout,container,false);
        ButterKnife.bind(this,mView);
        mlist.setLayoutManager(new LinearLayoutManager(getContext()));
//        get the data based on radio button click and send to adapter
//        this is the common page for all the lessons and also "ALL" page
//        get the data from
        mlist.setAdapter(new ReportPagerMarkAdapter(getContext(),generateAdapterData()));

        RadioButton button=(RadioButton) mRadioGroup.findViewById(R.id.radio_button_week);
//        button.setSelected(true);
        mRadioGroup.setActivated(true);
//        button.setActivated(true);
        button.setChecked(true);
        return mView;


    }

    private List<ReportModel> generateAdapterData() {

        //   have a variable here to identify subject clicked..radio button
        //  make connection get response

        //build objects
//        return to adpater
//        while making connection invalidate view to sow loading icon
        List<ReportModel> mReportList=new ArrayList<>();
        mReportList.add(new ReportModel(0,false,22,10));
        mReportList.add(new ReportModel(1,false,48,8));
        mReportList.add(new ReportModel(2,false,54,12));
        mReportList.add(new ReportModel(3,false,12,5));
        mReportList.add(new ReportModel(4,false,32,13));
        return mReportList;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        return super.getLayoutInflater(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        get the radio button click and contact the specified url with id
//        there are various categories of marks

    }

    @Override
    public void onStart() {

        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {

        super.onDetach();
    }
}
