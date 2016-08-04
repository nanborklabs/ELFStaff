package com.elfstaff.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.elfstaff.Fragments.NotificationPagerFragments.AcceptNotiFragment;
import com.elfstaff.Fragments.NotificationPagerFragments.PendingNotiFragment;
import com.elfstaff.Fragments.NotificationPagerFragments.RequestNotifFragment;

/**
 * Created by nandhu on 4/8/16.
 */
public class NotificationPagerAdapter extends FragmentStatePagerAdapter {

    public NotificationPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.d("PAGER ADPTER", "NotificationPagerAdapter: ");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Request";
            case 1:
                return "Accept";
            case 2:
                return  "pending";

        }
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment mFragment=null;
        switch (position){
            case 0:
                mFragment=new RequestNotifFragment();

                break;
            case 1:
                mFragment=new AcceptNotiFragment();
                break;
            case 2:
                mFragment=new PendingNotiFragment();
                break;
            default:
                mFragment=null;


        }
        return mFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
