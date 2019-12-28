package com.basusingh.mygrievance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceManager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.basusingh.mygrievance.Activity.MainActivity;
import com.basusingh.mygrievance.Activity.Registration.AppInfoPage;
import com.basusingh.mygrievance.Helper.Constant;
import com.basusingh.mygrievance.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = getSharedPreferences(Constant.AppData_SharedPreference, MODE_PRIVATE);
        if(sp.getBoolean(Constant.IS_FIRST_TIME, true)){
            turnOnNotifications();
        } else {
            startMainActivity();
        }

    }

    private void turnOnNotifications(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("allNotification", true);
        editor.putBoolean("myGrievanceCommentNotification", true);
        editor.putBoolean("communityReplyNotification", true);
        editor.putBoolean("communityPostFollowReplyNotification", true);
        editor.putBoolean("otherNotification", true);
        editor.apply();

        startRegistrationPage();
    }
    private void startMainActivity(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
        //overridePendingTransition(R.anim.fadein,R.anim.fadeout);
    }

    private void startRegistrationPage(){
        startActivity(new Intent(getApplicationContext(), AppInfoPage.class));
        finish();
        //overridePendingTransition(R.anim.fadein,R.anim.fadeout);
    }
}
