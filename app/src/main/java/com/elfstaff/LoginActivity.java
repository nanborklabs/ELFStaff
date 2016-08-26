package com.elfstaff;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.elfstaff.Fragments.ForgotPassword;
import com.elfstaff.Network.ElfRequestQueue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG="Login";
    private static final String PREFS="LOGIN";

    private static final String NET_TAG="Login_page";

    private static final String FORGOT_URL ="www.googel.com";
    public View mview;

    private ProgressDialog progress;


    private ElfRequestQueue queue;

    @BindView(R.id.uname_tl_login)
    TextInputLayout uname;
    @BindView(R.id.pasword_tl_login) TextInputLayout mPass;
    @BindView(R.id.login_register_button)
    Button mRegisterButton;
    @BindView(R.id.login_forgot_password_button) Button mForgotButton;
    @BindView(R.id.submit_button_login)
    Button mLogin_button;


    ElfRequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mRequestQueue  = ElfRequestQueue.getInstance(this);


        progress=new ProgressDialog(this);

        queue=ElfRequestQueue.getInstance(this);

        uname.setTranslationY(-uname.getHeight());
        mPass.setTranslationY(-mPass.getHeight());
        StartAnimations();



        mLogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress.show();
                String username=uname.getEditText().getText().toString();
                String password=mPass.getEditText().getText().toString();


                sendServer(username,password);
                progress.setTitle("Logging in");
                progress.setMessage("Loggin in ..!!");
                progress.setIndeterminate(true);

            }
        });

        //set click listenere for Register Fragment
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RegisterFragment mFragment=new RegisterFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_holder,mFragment)
                        .commit();
            }
        });


        //set click action for Forgot Password
        mForgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_holder,new ForgotPassword())
                        .commit();

            }
        });




    }

    private void sendServer(String username, final String password) {
        String url_string="http://www.hijazboutique.com/elf_ws.svc/CheckStaffLogin";
        final JSONObject object=new JSONObject();
        try {
            object.put("username",username);
            object.put("password",password);
        }
        catch (Exception e){
            Log.d(TAG, "sendServer: "+e.getLocalizedMessage());
        }

        JsonArrayRequest request=new JsonArrayRequest(Request.Method.POST, url_string, object, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    progress.hide();
                    JSONObject object1 = (JSONObject) response.get(0);
                    String LoginStatus=object1.getString("LoginStatus");
                    String Cityname=object1.getString("CityName");
                    String District=object1.getString("DistrictName");
                    String Firstname=object1.getString("FirstName");
                    String Lastname=object1.getString("LastName");
                    String Email=object1.getString("EmailAddress");
                    String PArent=object1.getString("ParentId");
                    String phone=object1.getString("PhoneNumber");
                    String BoardName=object1.getString("BoardName");
                    String BoardId=object1.getString("BoardId");
                    String StudentId=object1.getString("StudentId");
                    String schoolName=object1.getString("InstitutionName");
                    String Standard=object1.getString("ClassName");

                    Log.d(TAG, "onResponse: Standard "+Standard);
                    Log.d(TAG, "onResponse: Schoolname "+schoolName);
                    Log.d(TAG, "onResponse: Boardid  "+BoardId);
                    Log.d(TAG, "onResponse: BoardName "+BoardName);
                    Log.d(TAG, "onResponse: Status "+LoginStatus);
                    Log.d(TAG, "onResponse: StudentId "+StudentId);

                    if (LoginStatus.equals("success") ){
                        NExtactivity(Firstname,Lastname,Email,PArent,phone,Cityname);

//                        save details to shared Prefs
                        SharedPreferences preferences= getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("firstname",Firstname);
                        editor.putString("lastname",Lastname);
                        editor.putString("email",Email);
                        editor.putString("parentid",PArent);
                        editor.putString("phone",phone);
                        editor.putString("district",District);
                        editor.putString("cityname",Cityname);
                        editor.putString("boardid",BoardId);
                        editor.putString("boardname",BoardName);
                        editor.putString("studentid",StudentId);
                        editor.putString("standard",Standard);
                        editor.putString("schoolname",schoolName);
                        editor.apply();


                    }
                    else {
                        showError();
                    }


                } catch (Exception e) {

                    Log.d(TAG, "Exception in JSon object getting " +e.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.hide();
                ShowNetorkError();


                Log.d(TAG, "Response is Error"+error.getLocalizedMessage());
            }
        });
        request.setTag(NET_TAG);

        queue.addToElfREquestQue(request);
    }

    private void ShowNetorkError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Could Not connect");
        builder.setMessage("Please make sure you are connected to Internet");

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic

                        startActivity(new Intent(Settings.ACTION_SETTINGS));

                    }
                });


        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();


    }

    private void NExtactivity(String firstname, String lastname, String email, String PArent, String phone, String cityname) {
        Intent i =new Intent(this,ELFStaff.class);
        i.putExtra("firstname",firstname);
        i.putExtra("lastname",lastname);
        i.putExtra("email",PArent);
        i.putExtra("parent",phone);
        i.putExtra("phone",cityname);
        startActivity(i);
    }

    private void showError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Wrong Details");
        builder.setMessage("Please Enter Correct details");

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        uname.getEditText().setText("");
                        mPass.getEditText().setText("");
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();


    }

    private void startAct() {
        Intent i=new Intent(this,ELFStaff.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void StartAnimations() {

//       Animatiosn for Imagview


//        animations for Edite text componenets

       /* username_login.animate().translationY(0)
                .setDuration(1200)
                .setStartDelay(800)
                .setInterpolator(new AccelerateInterpolator())
                .start();
        mPassword.animate().translationY(0)
                .setDuration(1200)
                .setStartDelay(800)

                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
                */

    }

    @Override
    protected void onStop() {
        super.onStop();
        queue.cancelElfReques(NET_TAG);
    }
}
