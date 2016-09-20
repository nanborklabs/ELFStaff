package com.elfstaff;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.elfstaff.Fragments.HomeFragment;
import com.elfstaff.Network.ElfRequestQueue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by nandhu on 18/8/16.
 */
public class RegisterFragment extends Fragment{



    private static final String URL="http://www.hijazboutique.com/elf_ws.svc/StaffRegistration";

    private static final String TAG = "REGISTER";

    private String[] mStates=
            {"Tamil Nadu","Karnataka",
                    "Andhra Pradhesh","Kerala"
                    ,"Maharastra","Telungana","Goa"
                    ,"Himachel Pradesh","Assam"};

    //Views in this  page
    @BindView(R.id.name_text)
    TextInputEditText mNameTextBox;

    @BindView(R.id.pas_register)
    TextInputEditText mPasswordBox;

    @BindView(R.id.re_pass_register)
    TextInputEditText mRePasswordbox;

    @BindView(R.id.next_button)
    Button mNextButton;

    @BindView(R.id.submit_register) Button mRegisterButton;

    @BindView(R.id.register_city)
    AutoCompleteTextView mCityBox;

    @BindView(R.id.register_state)
    AutoCompleteTextView mStateBox;

    @BindView(R.id.register_email_text)
    TextInputEditText eMailBox;

    @BindView(R.id.register_phone_number)
    TextInputEditText mPhoneBox;

    @BindView(R.id.register_school)
    AutoCompleteTextView mSchoolBox;

    @BindView(R.id.board_register)
    AutoCompleteTextView mBoardBox;

//    The request Queue for this page
    ElfRequestQueue mRequestQueue;





    RegistrationSuccess mCallback;

    private static RegisterFragment mFragment = null;
    public String getClassId(String mClass){
        switch (mClass){
            case "10":
                return "10";
            case  "11":
                return "11";
            case "12":
                return "12";
            default:
                return "0";
        }
    }


    public String getBoardId(String mBoard){
        switch (mBoard){
            case "CBSE":
                return "";

            case "Samacheer":
                return "";

            case "ICSE":
                return "";
            default:
                return "0";
        }
    }

    public String getStateId(String mStateName){
        switch (mStateName){
            case "Tamil Nadu":
                return "1";
            case "Karnataka":
                return "2";
            case "Kerala":
                return "3";
            case "Maharastra":
                return "";
            case "Telungana":
                return "";
            case "Himachel Pradesh":
                return "";
            case "Assam":
                return "";
            case "Meghalaya":
                return "";
            case "Bihar":
                return "";
            case "Andhra Pradesh":
                return "";
            default:
                return "";
        }
    }


    @Override
    public void onAttach(Context context) {
        mCallback = (RegistrationSuccess)getActivity();
        super.onAttach(context);
    }

    private View mView;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       mView=inflater.inflate(R.layout.register_fragment,container,false);
        ButterKnife.bind(this,mView);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                final String mPass =  mPasswordBox.getText().toString();
                final String mReneter_pass=mRePasswordbox.getText().toString();
               if (mPass.equals(mReneter_pass)){
                   //same password if (Utils.isAndroid5()){
                       final View myView = getView().findViewById(R.id.ll_secondpage);
                       if (myView==null){
                           Log.d(TAG, "my view is null");
                       }

                       // get the center for the clipping circle
                       int cx = myView.getMeasuredWidth() / 2;
                       int cy = myView.getMeasuredHeight() / 2;

                       // get the final radius for the clipping circle
                       int finalRadius = Math.max(myView.getWidth(), myView.getHeight()) / 2;

                       // create the animator for this view (the start radius is zero)
                   Animator anim =
                           null;
                   if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                       anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
                   }
                   assert  anim!=null;

                   // make the view visible and start the animation
                       getView().findViewById(R.id.rl_first_page).setVisibility(View.INVISIBLE);
                       myView.setVisibility(View.VISIBLE);
                       anim.setDuration(600);
                       anim.setInterpolator(new AccelerateInterpolator(0.5f));
                       anim.start();

               }
                else{
                   //not same password
                   Toast.makeText(getContext(),"Please Enter Correct Paswword",Toast.LENGTH_SHORT).show();

               }
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String  mName = mNameTextBox.getText().toString();
                final String mPass =  mPasswordBox.getText().toString();
                final String mState =  mStateBox.getText().toString();

                final String schoolname=mSchoolBox.getText().toString();
                final String city=mCityBox.getText().toString();
                final String board=mBoardBox.getText().toString();
                final String mPhone = mPhoneBox.getText().toString();
                final String email = eMailBox.getText().toString();


                register(mName,mPass,mState,board,city,schoolname,mPhone,email);
            }
        });
        return mView;
    }


    /*
    * this method constrcuts Request and Makes Request to server*/
    private void register(String mName, String mPass, String mState, String board, String city, String schoolname, String mPhone, String email) {
        final JSONObject mObject=new JSONObject();


//        todo: do something about getting class name
        try{
            mObject.put("FirstName",mName);
            mObject.put("LastName","Null");
            mObject.put("EmailAddress",email);
            mObject.put("Password",mPass);
            mObject.put("InstitutionId","1");
            mObject.put("BoardId","1");
            mObject.put("ClassId","1");

            mObject.put("CityId","1");
            mObject.put("DistrictId","1");
            mObject.put("StateId","1");
            mObject.put("PhoneNumber",mPhone);
//          todo:spinner
//            mObject.put("")
        }
        catch (Exception e){
            Log.d(TAG, "register: JSOn object Exception");
        }
        final JsonArrayRequest mReq=new JsonArrayRequest(Request.Method.POST, URL, mObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    final JSONObject mResponse = response.getJSONObject(0);
                    final String mStatuscode = mResponse.getString("StatusCode");
                    if (mStatuscode.equals("9999")){

//                        email already exists
                        Toast.makeText(getContext(),"E-Mail Already Exists",Toast.LENGTH_SHORT).show();

                        mPhoneBox.setText("");
                        eMailBox.setText("");

                        getView().findViewById(R.id.rl_first_page).setVisibility(View.VISIBLE);
                        getView().findViewById(R.id.ll_secondpage).setVisibility(View.INVISIBLE);

                    }else {
                        if (mCallback !=null){
                            mCallback.RegisteredUser(true);
                        }else {
                            throw new NullPointerException("CALL back must be implemented");
                        }


                    }
                }
                catch (Exception e ){
                    Log.d(TAG, "Exception in On Response "+e.getLocalizedMessage());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.toString());
                Toast.makeText(getContext(), "Try Again", Toast.LENGTH_SHORT).show();
                getView().findViewById(R.id.rl_first_page).setVisibility(View.VISIBLE);
                getView().findViewById(R.id.ll_secondpage).setVisibility(View.INVISIBLE);

            }
        });
        mRequestQueue.addToElfREquestQue(mReq);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestQueue=ElfRequestQueue.getInstance(getContext());
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public static  RegisterFragment newInstance() {
        if (mFragment == null){
            mFragment = new RegisterFragment();
            return mFragment;
        }
        return mFragment;

    }

    public  interface RegistrationSuccess{
        void RegisteredUser(boolean ok);
    }
}
