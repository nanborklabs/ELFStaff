package com.elfstaff.Network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by nandhu on 18/8/16.
 */
public class ElfRequestQueue {
    private Context mContext;
    protected com.android.volley.RequestQueue mRequestQueue;


    private static ElfRequestQueue mRequestQ;

    public ElfRequestQueue(Context mContext) {
        this.mContext = mContext;
    }

//    get the single object here..with that singleton get getRequest queue

    public static synchronized ElfRequestQueue getInstance(Context context) {
        if (mRequestQ == null) {
            mRequestQ = new ElfRequestQueue(context);
        }
        return mRequestQ;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToElfREquestQue(Request<T> req) {
        getRequestQueue().add(req);
    }


    public void cancelElfReques(String netTag) {
        if (mRequestQueue!=null){
            getRequestQueue().cancelAll(netTag);
        }
    }

}
