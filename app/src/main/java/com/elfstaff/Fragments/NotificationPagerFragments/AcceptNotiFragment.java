package com.elfstaff.Fragments.NotificationPagerFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfstaff.Adapter.AcceptNotificationAdapter;
import com.elfstaff.R;
import com.elfstaff.model.Student;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 4/8/16.
 */
public class AcceptNotiFragment extends android.support.v4.app.Fragment {


    @BindView(R.id.accept_list)
    RecyclerView mList;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View mView=inflater.inflate(R.layout.accept_notification,container,false);
        ButterKnife.bind(this,mView);
        mList.setLayoutManager(new LinearLayoutManager(getContext()));
        mList.setAdapter(new AcceptNotificationAdapter(getContext(),generateNotifications()));
        return mView;
    }

    private List<Student> generateNotifications() {
        List<Student> studentList =new ArrayList<>();
        studentList.add(new Student("Krisanan","10","Samacheer kalvi",1));
        studentList.add(new Student("Nandha","12","Samacheer kalvi",2));
        studentList.add(new Student("Bharat","10","Samacheer kalvi",3));
        studentList.add(new Student("kali","12","Samacheer kalvi",14));
        return studentList;


    }
}
