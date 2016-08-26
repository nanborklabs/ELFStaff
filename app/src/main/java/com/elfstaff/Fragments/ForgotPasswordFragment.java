package com.elfstaff.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
 * Created by nandhu on 26/8/16.
 */
public class ForgotPasswordFragment extends Fragment {



    private static final String TAG = "FORGOT";
    private static final String CHECK_URL = "http://www.hijazboutique.com/elf_ws.svc/CheckUserDetails";
    private static final String SUBMIT_URL = "http://www.hijazboutique.com/elf_ws.svc/UpdatePassword";

    private static final String PREFS="ELF_PARENT";


    @BindView(R.id.fp_email_ted)
    TextInputLayout mEamilBox;
    @BindView(R.id.fp_phone_ted) TextInputLayout mPhoneBox;
    @BindView(R.id.fp_check_button)
    Button mCheckButton;
    private View mView;
    @BindView(R.id.fp_pass_ted) TextInputLayout mPasswordBox;
    @BindView(R.id.fp_rpass_ted) TextInputLayout mRePassBox;
    @BindView(R.id.fp_new_password_button) Button mSubmitButton;

    ElfRequestQueue mRequestQue;

    ChangePassword mCallback;


    //user id
    String mParentId;

    private static  ForgotPasswordFragment mFragment = null;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQue = ElfRequestQueue.getInstance(getContext());





    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.forgot,container,false);
          /*this page has 2 root layouts
        * 1 contains fields to get Email and phone number
        * 2 contains fields to set Password */
        ButterKnife.bind(this,mView);

        //first set the Email check page visible

        mView.findViewById(R.id.forgot_password).setVisibility(View.VISIBLE);

        // send email and phone details to server

        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mEmail = mEamilBox.getEditText().getText().toString();
                final String mPhone = mPhoneBox.getEditText().getText().toString();

                //validate user name and password for this user
                validateUser(mEmail,mPhone);
            }
        });

        //button which sets new password
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user has reset the password save it in database associated with this user
                final String mPass =mPasswordBox.getEditText().getText().toString();
                final String mRePass = mRePassBox.getEditText().getText().toString();
                if (mPass.equals(mRePass)){
                    submitNewPassword(mPass,mRePass);
                }
                else {
                    //passwords do not match
                    Toast.makeText(getContext(),"Mis Match password", Toast.LENGTH_SHORT).show();

                }

            }
        });

        return mView;
    }

    private void submitNewPassword(String mPass, String mRePass) {
        final JSONObject mObject = new JSONObject();

        try {
//            // TODO: 22-08-2016  dynamic user id


            mObject.put("UserId", "2");
            mObject.put("password", mPass);
            mObject.put("UserType","parent");
        } catch (Exception e) {
            Log.d(TAG, "submitNewPassword: Exception ");

        }

        JsonArrayRequest mRequest = new JsonArrayRequest(Request.Method.POST, SUBMIT_URL, mObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (PasswordResetSucess(response)){
                    showloginPage();
                }
                else {
                    Toast.makeText(getContext(),"Trying again after some",Toast.LENGTH_SHORT).show();
                }



                //
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: ");
            }
        });

        //send Request
        mRequest.setRetryPolicy(new CustomRetryPolicy());
        mRequestQue.addToElfREquestQue(mRequest);

    }

    private void showloginPage() {
        if (mCallback!=null){
            mCallback.PassworChanged(true);
        }
    }

    private boolean PasswordResetSucess(JSONArray response) {
        try {

            JSONObject obj = response.getJSONObject(0);
            String status= obj.getString("StatusCode");
            if (status.equals("1000")){
                return true;
            }
            else {
                return false;
            }


        }
        catch (Exception  e){
            return false;
        }
    }


    private void validateUser(String mEmail, String mPhone) {
        //make objects get Repsonse , if success show reset password page
        final JSONObject mObject = new JSONObject();
        try {

            mObject.put("Email", mEmail);
            mObject.put("PhoneNumber", mPhone);
            mObject.put("UserType","parent");
        } catch (Exception e) {
            Log.d(TAG, "validateUser: ");
        }

        JsonArrayRequest mRequest = new JsonArrayRequest(Request.Method.POST, CHECK_URL, mObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                // if credentials are good show reset page
                // else  make toast

                if (success(response)){
                    //switch the visiblity of layout
                    mView.findViewById(R.id.forgot_password).setVisibility(View.INVISIBLE);
                    mView.findViewById(R.id.new_password).setVisibility(View.VISIBLE);

                }
                else{
                    Toast.makeText(getContext(),"Wrong Credentials , Please Try again",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Network Down , Please Try again",Toast.LENGTH_SHORT).show();
            }
        });

        mRequest.setRetryPolicy(new CustomRetryPolicy());
        mRequestQue.addToElfREquestQue(mRequest);
    }

    private boolean success(JSONArray response) {
        try {

            JSONObject object= response.getJSONObject(0);
            String status = object.getString("StatusCode");
            //mParentId  = object.getString("parentID");
            Log.d(TAG, "Status code "+status);
            if (status.equals("1000")){
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e ){
            Log.d(TAG, "exception in getting object values from ");
            return false;
        }



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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {
        mCallback = (ChangePassword) context;

        super.onAttach(context);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public Fragment newInstance() {
        if (mFragment == null){
            mFragment = new ForgotPasswordFragment();
            return mFragment;
        }
        return mFragment;
    }

    public interface ChangePassword{
        void PassworChanged(boolean changed);
    }
}
