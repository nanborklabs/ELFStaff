package com.elfstaff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.elfstaff.Fragments.ForgotPasswordFragment;
import com.elfstaff.Fragments.LoginFragment;
import com.elfstaff.Fragments.NewRegistration.BasicRegistration;
import com.elfstaff.Fragments.NewRegistration.Detailsregistration;
import com.elfstaff.Fragments.RelationshipFragment;

/**
 * Created by nandhu on 26/8/16.
 */
public class FirstActivity extends AppCompatActivity implements LoginFragment.Buttonclicked ,
        BasicRegistration.basicReg
        , ForgotPasswordFragment.ChangePassword ,RelationshipFragment.RelationshipPagehandler{


    private static final String TAG = "Login";
    private static final String PREFS = "ELF_PARENT";


    private static final String NET_TAG = "Login_page";

    private static final String FORGOT_URL = "www.googel.com";


//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);


        Log.d(TAG, "onCreate: First Activity");

        RegisterFragment mFragment  = RegisterFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.first_frag_holder,mFragment)
                .commit();


    }


    @Override
    protected void onStop() {
        super.onStop();


    }


    /*this is called in login   page to register or forgot password*/
    @Override
    public void ChangeFragment(int id) {
        Fragment mFragment = null;
        if (id == 0) {
            mFragment = new RegisterFragment().newInstance();
        } else if (id == 1) {
            mFragment = new ForgotPasswordFragment().newInstance();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.first_frag_holder, mFragment)
                .commit();
    }


    @Override
    public void PassworChanged(boolean changed) {


        if (changed){
            //passowrd have been changed , show login fragment

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.first_frag_holder, new LoginFragment())
                    .commit();


        }
    }

    @Override
    public void RelationshipAdded(boolean ok) {
        //show Home page // student id should have heve been saved
        final Intent i = new Intent(this,ELFStaff.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }





    @Override
    public void GotEmail(String email) {

        Detailsregistration mFragment = Detailsregistration.newInstance(email);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.first_frag_holder,mFragment )
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}


