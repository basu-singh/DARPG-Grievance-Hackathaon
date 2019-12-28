package com.basusingh.mygrievanceadmin.Activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;

import com.basusingh.mygrievanceadmin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("");
            toolbar.setTitle("");
        }
        if(Build.VERSION.SDK_INT >= 21){
            setMargins(toolbar, 0, getStatusBarHeight(), 0, 0);
        }

        View bgLayout = findViewById(R.id.bg_layout);
        animationDrawable = (AnimationDrawable) bgLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);

        setClickListener();
    }

    private void setClickListener(){
        LinearLayout userLayout = findViewById(R.id.user_layout);
        LinearLayout viewLayout = findViewById(R.id.view_layout);
        LinearLayout ministriesLayout = findViewById(R.id.ministries_layout);
        LinearLayout communityLayout = findViewById(R.id.community_layout);
        LinearLayout helpLayout = findViewById(R.id.help_layout);

        userLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast("Under construction");
            }
        });

        viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewAllGrievance.class));
            }
        });

        ministriesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast("Under Construction");
            }
        });

        communityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast("Under Construction");
            }
        });

        helpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast("Under Construction");
            }
        });
    }

    private void makeToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    private int getStatusBarHeight(){
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
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
