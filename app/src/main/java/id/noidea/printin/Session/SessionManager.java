package id.noidea.printin.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by daniellindp on 09/02/2018.
 */

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "printPref";
    private static final String IS_LOGIN = "IsLoggedIn";

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String id, String name, String role, String photo, String token){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString("id", id);
        editor.putString("name", name);
        editor.putString("role", role);
        editor.putString("photo", photo);
        editor.putString("token", token);
        editor.commit();
    }

    public String getId() {
        return pref.getString("id", null);
    }
    public String getName() {
        return pref.getString("name", null);
    }
    public String getRole() {
        return pref.getString("role", null);
    }
    public String getPhoto() {
        return pref.getString("photo", null);
    }
    public String getToken() {
        return pref.getString("token", null);
    }

//    public void checkLogin(){
//        if( ! this.isLoggedIn() ){
//            Intent i = new Intent(_context, LoginActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            _context.startActivity(i);
//        }
//    }
//
//    public void logoutUser(){
//        editor.clear();
//        editor.commit();
//
//        Intent i = new Intent(_context, LoginActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        _context.startActivity(i);
//    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
