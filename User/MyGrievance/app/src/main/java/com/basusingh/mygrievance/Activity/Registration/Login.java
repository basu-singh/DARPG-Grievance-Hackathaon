package com.basusingh.mygrievance.Activity.Registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.basusingh.mygrievance.Activity.MainActivity;
import com.basusingh.mygrievance.Helper.Constant;
import com.basusingh.mygrievance.R;

public class Login extends AppCompatActivity {

    EditText email_phone, password;
    ProgressDialog progressDialog;
    AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        RelativeLayout btn_login = findViewById(R.id.login_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });

        RelativeLayout btn_signup = findViewById(R.id.signup_btn);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
                finish();
            }
        });

        email_phone = findViewById(R.id.email_phone);
        password = findViewById(R.id.password);
        progressDialog = new ProgressDialog(Login.this);


        View bgLayout = findViewById(R.id.bg_layout);
        animationDrawable = (AnimationDrawable) bgLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);

    }

    private void validateData(){
        if(email_phone.getText().toString().isEmpty()){
            setErrorDialog("Error", "Please enter email or phone");
            return;
        }
        if(password.getText().toString().isEmpty()){
            setErrorDialog("Error", "Please enter password");
            return;
        }
        doLogin();
    }

    private void doLogin(){
        showProgressDialog("Please wait");
        hideKeyboard();
        //Dummy Login

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cancelProgressDialog();
                finishLoginAndStartApp();
            }
        }, 1500);
    }

    private void finishLoginAndStartApp(){
        SharedPreferences sp = getSharedPreferences(Constant.AppData_SharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(Constant.IS_FIRST_TIME, false);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    private void setErrorDialog(String error, String message){
        final AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setTitle(error);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.show();
    }

    public void showProgressDialog(String message){
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

    }

    public void cancelProgressDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    private void hideKeyboard(){
        try{
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        animationDrawable.start();
    }


    @Override
    public void onStop(){
        super.onStop();
        animationDrawable.stop();
    }
}
