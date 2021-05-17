package com.example.login_sample;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    //  storeUserData() stores each of these five inputs as tuples in SharedPreferences using SharedPreferences.Editor
    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("fName", user.firstName);
        spEditor.putString("lName", user.lastName);
        spEditor.putInt("dob", user.dob);
        spEditor.putString("username", user.email);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }

    //  getLoggedInUser() Retrieves the data last stored in SharedPreferences, and returns a User object with those criteria
    public User getLoggedInUser() {
        String fName = userLocalDatabase.getString("fName", "");
        String lName = userLocalDatabase.getString("lName", "");
        int dob = userLocalDatabase.getInt("dob", -1);
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password","");

        User storedUser = new User(fName, lName, dob, username, password);

        return storedUser;
    }

    //  setUserLoggedIn() sets a loggedIn boolean (replaced by loggedIn method)
    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    //  getUserLoggedIn() gets the loggedIn boolean
    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("loggedIn", false)){
            return true;
        }
        else{
            return false;
        }
    }

    //used to clear SharedPreferences.Editor
    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
