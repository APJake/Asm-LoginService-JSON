package com.example.loginservicejsonassignment.mine;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.loginservicejsonassignment.HomeActivity;
import com.example.loginservicejsonassignment.MainActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class LoginService extends Service {

    private static final String USERS_JSON_FILE_NAME = "users.json";
    private static final String USERS_JSON_USERNAME = "username";
    private static final String USERS_JSON_PASSWORD = "password";

    String username,password;

    public LoginService() {
    }



    private void showAlert(String title, String message){

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        AlertDialog alert=builder.create();
//        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        alert.show();

//        new MaterialAlertDialogBuilder(this)
//                .setTitle(title)
//                .setMessage(message)
//                .setPositiveButton("Ok",null)
//                .show();
    }

    private boolean isValid(){
        String user_invalid="Invalid Username";
        String psw_invalid="Invalid Password";

        String reg="^(?=.*[0-9])"
                + "(?=.*[a-z])"
                + "(?=.*[@#$%^&*!+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern pattern=Pattern.compile(reg);

        if(username.equals("")){
            showAlert(user_invalid,"Username can't be empty");
        }else if(password.equals("")){
            showAlert(psw_invalid,"Password can't be empty");
        }else if(!pattern.matcher(password).matches()){
            showAlert(psw_invalid,"Password should contain 8 characters, at least one digit, at least special character");
        }else{
            return true;
        }
        return false;
    }

    private boolean checkUser(){
        try {
            JSONArray jsonArray = getJSONData();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj.getString(USERS_JSON_USERNAME).equals(username)) {
                    if (obj.getString(USERS_JSON_PASSWORD).equals(password)) {
                        return true;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public JSONArray getJSONData(){
        String jsonStr;
        JSONArray jsonArray=null;
        try{
            InputStream ips = getAssets().open(USERS_JSON_FILE_NAME);
            int size = ips.available();
            byte[] buffer = new byte[size];
            ips.read(buffer);
            ips.close();
            jsonStr=new String(buffer, StandardCharsets.UTF_8);

            jsonArray = new JSONArray(jsonStr);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle bundle=intent.getExtras();
        username=bundle.getString("username");
        password=bundle.getString("password");

        if(isValid()){
            if(checkUser()){
                Intent it = new Intent(this, HomeActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                it.putExtra("username",username);
                startActivity(it);
            }else{
                Toast.makeText(this, "Username or password incorrect", Toast.LENGTH_SHORT).show();
            }
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}

/*

[
  {
    "username": "amk",
    "password": "amk1234*"
  },
  {
    "username": "loki",
    "password": "lok!1234"
  },
  {
    "username": "cmt",
    "password": "$cmt1234"
  }
]

 */