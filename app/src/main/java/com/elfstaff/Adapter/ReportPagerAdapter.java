package com.elfstaff.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.elfstaff.Fragments.ReportPagerFragment;

/**
 * Created by nandhu on 5/8/16.
 *
 */
public class ReportPagerAdapter extends FragmentStatePagerAdapter {
    public ReportPagerAdapter(FragmentManager childFragmentManager) {
        super(childFragmentManager);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "ALL";
            case 1:
                return "L1";
            case 2:
                return "L2";
            case 3:
                return "L3";
            case 4:
                return "L4";

        }
    }

    @Override
    public Fragment getItem(int position) {
       Fragment mFragment=null;
        switch (position){
           case 0:
               mFragment=new ReportPagerFragment();
               break;
           case 1:
                mFragment=new ReportPagerFragment();
               break;
           case 2:
               mFragment=new ReportPagerFragment();
               break;
           case 3:
               mFragment=new ReportPagerFragment();
               break;
           case 4:mFragment=new ReportPagerFragment();
               break;
       }


        return mFragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
