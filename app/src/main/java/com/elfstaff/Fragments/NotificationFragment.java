package com.elfstaff.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfstaff.Adapter.NotificationPagerAdapter;
import com.elfstaff.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 3/8/16.
 */
public class NotificationFragment extends android.support.v4.app.Fragment {
    public   View mView;
       @BindView(R.id.noti_pager) ViewPager mPager;
    @BindView(R.id.noti_tab) TabLayout mTabLayout;
    FragmentManager fm;

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
        fm=getChildFragmentManager();

        Log.d("Notification page", "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.notifiation_fragment,container,false);
        ButterKnife.bind(this,mView);

        if (mPager==null){
            Log.d("pager", "null: ");

        }
        else {
            Log.d("pager", "not null: ");
        }
        if (mTabLayout==null){
            Log.d("pager", "null: ");
        }

        if (fm!=null){
            mPager.setAdapter(new NotificationPagerAdapter(fm));

        }
        else {
            fm=getChildFragmentManager();
            mPager.setAdapter(new NotificationPagerAdapter(fm));
        }
//        mPager.setAdapter(new NotificationPagerAdapter(getChildFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);





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
