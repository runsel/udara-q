package id.web.runsel.udaraq.extras;

import android.content.Context;
import android.content.SharedPreferences;

import id.web.runsel.udaraq.R;

public class AppPreference {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public AppPreference(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("appPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //Setting login status
    public void setLoginStatus(boolean status){
        editor.putBoolean("loginStatus", status);
        editor.commit();
    }
    public boolean getLoginStatus(){
        return sharedPreferences.getBoolean("loginStatus", false);
    }

    //Api Token
    public  void  setToken (String token) {
        editor.putString("token", token);
        editor.commit();
    }
    public String getToken() {
        return sharedPreferences.getString("token", "null");
    }

    public void setName(String name) {
        editor.putString("name", name);
        editor.commit();
    }
    public String getName() {
        return sharedPreferences.getString("name", "null");
    }

    public void setLat(String lat) {
        editor.putString("lat", lat);
        editor.commit();
    }

    public String getLat() {
        return sharedPreferences.getString("lat", "null");
    }

    public void setLon(String lon) {
        editor.putString("lon", lon);
        editor.commit();
    }

    public String getLon() {
        return sharedPreferences.getString("lon", "null");
    }

    public void clearData() {
        editor.clear();
        editor.commit();
    }
}
