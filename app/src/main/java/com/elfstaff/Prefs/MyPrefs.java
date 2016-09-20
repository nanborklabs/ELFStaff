package com.elfstaff.Prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nandhu on 26/8/16.
 */
public class MyPrefs {

    private final String PREFS = "ELF_STAFF";
    private Context mContext;
    private SharedPreferences sf;
    public MyPrefs(Context mContext) {
        this.mContext =mContext;
        sf = mContext.getSharedPreferences(PREFS,Context.MODE_PRIVATE);
    }


    public String getStaffId(){

        String Id = sf.getString("staffid","null");
        if (Id.equals("null")){
//           throw new NullPointerException("Student id is null");
            return "null";
        }else {
            return Id;

        }

    }

    public String getStandard(){
        String Id = sf.getString("standard","null");
        if (Id.equals("null")){
            return  "10";
        }else {
            return Id;

        }
    }

    public String getCityName(){
        String Id = sf.getString("cityname","null");
        if (Id.equals("null")){
//            throw new NullPointerException("lcoation id is null");
            return "null";
        }else {
            return Id;

        }
    }
    public String getSchoolName(){
        String Id = sf.getString("schoolname","null");
        if (Id.equals("null")){
            return  "School Name";
        }else {
            return Id;

        }
    }

    public String getBoardName(){
        String Id = sf.getString("boardname","null");
        if (Id.equals("null")){
            return "Null Board";
        }else {
            return Id;

        }


    }
    public String  getStudentName(){
        String Id = sf.getString("firstname","null");
        if (Id.equals("null")){
//            throw new NullPointerException("Student id is null");
            return "null";
        }else {
            return Id;

        }
    }

    public void setStudentId(){

    }

    public  boolean geRequestedAccepted(){
        return sf.getBoolean("RequestAccepted",false);
    }

    public void setRequestAccepted(boolean value){
        final SharedPreferences.Editor ed = sf.edit();
        ed.putBoolean("isStudentAccepted",value);
        ed.apply();
    }


    public boolean isStudentAcceptedRequested() {
        return sf.getBoolean("isStudentAccepted",false);
    }

    public boolean isFirstTime() {
          return sf.getBoolean("isFirstTime",true);
    }


   public void setIsFirstTime(boolean value){
        final SharedPreferences.Editor ed = sf.edit();
        ed.putBoolean("isFirstTime",value);
        ed.apply();
    }

}

