package com.elfstaff.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elfstaff.Network.CustomRetryPolicy;
import com.elfstaff.Network.ElfRequestQueue;
import com.elfstaff.R;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nandhu on 3/8/16.
 */
public class ContactFragment extends android.support.v4.app.Fragment {
    private View mView;


    private static final String TAG = "About";
    private static final String  URL = "http://www.hijazboutique.com/elf_ws.svc/SaveUserFeedback";

    @BindView(R.id.feed_editText)
    EditText mEditext;


    ElfRequestQueue mRequestQueue;



    @BindView(R.id.submit_feedback)
    Button mSubmit;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue = ElfRequestQueue.getInstance(getContext());
    }

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
        mView=inflater.inflate(R.layout.contactfragment,container,false);
        ButterKnife.bind(this,mView);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mfeed = mEditext.getText().toString();
                sendFeed(mfeed);
            }
        });
        return mView;
    }

    private void sendFeed(String mfeed) {
        Log.d(TAG, "sendFeed: ");
        JSONObject object = null;
        try {
            object= new JSONObject();
            String Id = getParentId();
            //todo: get student id
            if (Id.equals("0") ){
                Log.d(TAG, "NO parentid ID");
            }
            object.put("UserId",Id);
            object.put("Feedback",mfeed);
            object.put("UserType","Parent");
            Log.d(TAG, "request body "+object.toString());
        }
        catch (Exception e ){
            Log.d(TAG, "Exception: ");
        }


        //make request
        JsonArrayRequest mRequest = new JsonArrayRequest(Request.Method.POST, URL, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d(TAG, "onResponse:"+ response.toString());
                //get the Response if success, feedback is sent
                try {


                    final JSONObject object1 = response.getJSONObject(0);
                    final String status = object1.getString("StatusCode");
                    Log.d(TAG, "onResponse: "+status);
                    if (status.equals("1000")) {
                        // successfully posted
                        Toast.makeText(getContext(),"Your Feedback has been Recoreded, Thank you" , Toast.LENGTH_SHORT).show();

                    }
                    else{
                        //
                        Toast.makeText(getContext(),"Try Again, feedback not sent" ,Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    Log.d(TAG, "onResponse: " + e.getLocalizedMessage());


                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"There is some problem with Network , Please Try again sometimes",Toast.LENGTH_SHORT).show();
            }
        });

        mRequest.setRetryPolicy(new CustomRetryPolicy());
        mRequestQueue.addToElfREquestQue(mRequest);

    }

    private String getParentId() {
        final SharedPreferences mPrefs  =  getContext().getSharedPreferences("ELF_S", Context.MODE_PRIVATE);
        final SharedPreferences.Editor mEditor = mPrefs.edit();
        Log.d(TAG, "getStaffID: "+mPrefs.getString("STAFF_ID","1"));
        return  mPrefs.getString("STAFF_ID","1");
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}


