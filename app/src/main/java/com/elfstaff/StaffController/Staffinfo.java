package com.elfstaff.StaffController;

import com.elfstaff.model.Staff;

/**
 * Created by DS on 9/20/2016.
 */
public class Staffinfo {

    private static Staffinfo mStaffInfo = null;
    private  static Staff mStaff;
    public Staffinfo(){
        mStaff = new Staff();
    }
    public static Staffinfo getmStaffInfo() {
        if (mStaffInfo == null){
            mStaffInfo = new Staffinfo();
            return mStaffInfo;
        }
        return mStaffInfo;

    }

    public static void setmStaffInfo(Staffinfo mStaffInfo) {
        Staffinfo.mStaffInfo = mStaffInfo;
    }


    public void addPhone(String phone) {
        mStaff.setPhone(phone);
    }

    public void addEmail(String email) {
        mStaff.setEmail(email);

    }

    public void adduser(String user) {
        mStaff.setName(user);

    }

    public String getPhone(){
        return mStaff.getPhone();
    }
    public String getname(){
        return mStaff.getEmail();
    }
    public String getEmail(){
        return mStaff.getEmail();
    }
}
