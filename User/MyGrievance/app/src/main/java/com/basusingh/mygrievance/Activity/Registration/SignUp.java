package com.basusingh.mygrievance.Activity.Registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.basusingh.mygrievance.Activity.MainActivity;
import com.basusingh.mygrievance.Helper.Constant;
import com.basusingh.mygrievance.R;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    EditText name, email, district, mobile, housename, street, city, pincode,
    password, confirm_password;
    RadioGroup sex_group;
    ProgressDialog progressDialog;
    Spinner country, state;
    AnimationDrawable animationDrawable;
    CardView state_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        RelativeLayout btn_login = findViewById(R.id.login_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

        RelativeLayout btn_signup = findViewById(R.id.signup_btn);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        housename = findViewById(R.id.housename);
        street = findViewById(R.id.street);
        city = findViewById(R.id.city);
        pincode = findViewById(R.id.pincode);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        sex_group = findViewById(R.id.sex_group);

        country = findViewById(R.id.country);
        state = findViewById(R.id.state);
        state_layout = findViewById(R.id.state_layout);
        district = findViewById(R.id.district);

        progressDialog = new ProgressDialog(SignUp.this);

        ArrayAdapter<CharSequence> adapter_s = ArrayAdapter.createFromResource(this,
                R.array.india_state, android.R.layout.simple_spinner_item);
        adapter_s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(adapter_s);
        state.setVisibility(View.GONE);
        district.setVisibility(View.GONE);

        ArrayAdapter<CharSequence> adapter_c = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);
        adapter_c.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(adapter_c);
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] arrayList = getResources().getStringArray(R.array.countries);
                if(arrayList[position].equalsIgnoreCase("India")){
                    state.setVisibility(View.VISIBLE);
                    district.setVisibility(View.VISIBLE);
                    state_layout.setVisibility(View.VISIBLE);
                } else {
                    state.setVisibility(View.GONE);
                    district.setVisibility(View.GONE);
                    state_layout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        View bgLayout = findViewById(R.id.bg_layout);
        animationDrawable = (AnimationDrawable) bgLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
    }

    private void validateData(){
        if(name.getText().toString().isEmpty()){
            setErrorDialog("Error", "Please enter name");
            return;
        }
        if(email.getText().toString().isEmpty() || !isValidEmail(email.getText().toString())){
            setErrorDialog("Error", "Please enter valid email");
            return;
        }
        if(mobile.getText().toString().isEmpty() || mobile.getText().toString().length() < 10){
            setErrorDialog("Error", "Please enter valid mobile number");
            return;
        }
        if(housename.getText().toString().isEmpty()){
            setErrorDialog("Error", "Please enter house name");
            return;
        }
        if(street.getText().toString().isEmpty()){
            setErrorDialog("Error", "Please enter street");
            return;
        }
        if(city.getText().toString().isEmpty()){
            setErrorDialog("Error", "Please enter city");
            return;
        }
        if(pincode.getText().toString().isEmpty()){
            setErrorDialog("Error", "Please enter pincode");
            return;
        }
        if(password.getText().toString().isEmpty()){
            setErrorDialog("Error", "Please enter password");
            return;
        }
        if(confirm_password.getText().toString().isEmpty()){
            setErrorDialog("Error", "Please enter password");
            return;
        }
        if(!password.getText().toString().equals(confirm_password.getText().toString())){
            setErrorDialog("Error", "Password do not match");
            return;
        }

        if(sex_group.getCheckedRadioButtonId() == -1){
            setErrorDialog("Error", "Please select gender");
            return;
        }

        if(country.getSelectedItem() == null){
            setErrorDialog("Error", "Please select country");
            return;
        }
        if(country.getSelectedItem().toString().equalsIgnoreCase("India")){
            if(state.getSelectedItem() == null){
                setErrorDialog("Error", "Please select state");
                return;
            }
            if(district.getText().toString().isEmpty()){
                setErrorDialog("Error", "Please enter district");
                return;
            }
        }


        doSignup();
    }

    private void doSignup(){
        showProgressDialog("Please wait");
        hideKeyboard();
        //Dummy Signup

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cancelProgressDialog();
                finishSignUpAndStartApp();
            }
        }, 1500);
    }

    private void finishSignUpAndStartApp(){
        SharedPreferences sp = getSharedPreferences(Constant.AppData_SharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(Constant.IS_FIRST_TIME, false);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    private void setErrorDialog(String error, String message){
        final AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
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

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
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
