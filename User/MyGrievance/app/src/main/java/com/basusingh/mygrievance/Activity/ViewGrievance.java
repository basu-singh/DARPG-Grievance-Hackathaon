package com.basusingh.mygrievance.Activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import com.basusingh.mygrievance.DatabasePreference.MyGrievance.MyGrievanceItems;
import com.basusingh.mygrievance.Utils.AddToFirebaseAnalytics;
import com.basusingh.mygrievance.Utils.BlurBuilder;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basusingh.mygrievance.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ViewGrievance extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbar;
    AppBarLayout appBarLayout;
    ImageView toolbar_bg, image1, image2, image3;
    TextView subject, message, ministry, timestamp;
    CardView image1Layout, image2Layout, image3Layout;
    MyGrievanceItems o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_grievance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(Build.VERSION.SDK_INT >= 21){
            setMargins(toolbar, 0, getStatusBarHeight(), 0, 0);
        }

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }

        Intent i = getIntent();
        if(i != null){
            o = (MyGrievanceItems) getIntent().getSerializableExtra("data");
        } else {
            Toast.makeText(getApplicationContext(), "Sorry, an error occurred!", Toast.LENGTH_LONG).show();
            new AddToFirebaseAnalytics(getApplicationContext()).addData("null", "Error occurred in ViewGrievance.java", "intent is null");
            finish();
            return;
        }

        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        timestamp = findViewById(R.id.timestamp);
        ministry = findViewById(R.id.ministry);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        toolbar_bg = findViewById(R.id.toolbar_bg);
        collapsingToolbar = findViewById(R.id.collapsingToolbar);
        appBarLayout = findViewById(R.id.appBarLayout);
        image1Layout = findViewById(R.id.image1Layout);
        image2Layout = findViewById(R.id.image2Layout);
        image3Layout = findViewById(R.id.image3Layout);

        setUpToolbar();
        setUpView();
    }

    private void setUpView(){
        subject.setText(o.getSubject());
        message.setText(o.getMessage());
        timestamp.setText(o.getTimestamp());
        ministry.setText(o.getMinistry());
        if(!o.getImage1().equalsIgnoreCase("null")){
            try {
                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File path1 = cw.getDir("Images", Context.MODE_PRIVATE);
                File f = new File(path1, o.getImage1());
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                image1.setImageBitmap(b);
                image1Layout.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            image1Layout.setVisibility(View.GONE);
        }
        if(!o.getImage2().equalsIgnoreCase("null")){
            try {
                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File path1 = cw.getDir("Images", Context.MODE_PRIVATE);
                File f = new File(path1, o.getImage2());
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                image2.setImageBitmap(b);
                image2Layout.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            image2Layout.setVisibility(View.GONE);
        }
        if(!o.getImage3().equalsIgnoreCase("null")){
            try {
                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File path1 = cw.getDir("Images", Context.MODE_PRIVATE);
                File f = new File(path1, o.getImage3());
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                image3.setImageBitmap(b);
                image3Layout.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            image3Layout.setVisibility(View.GONE);
        }
    }

    private void setUpToolbar(){
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.TextAppearance_MyApp_Title_Collapsed);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.TextAppearance_MyApp_Title_Expanded);
        collapsingToolbar.setTitle(" ");
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset <= 30) {
                    collapsingToolbar.setTitle(o.getSubject());
                    isShow = true;
                } else if (isShow) {
                    //carefull there must a space between double quote otherwise it dose't work
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
        if(o.getImage1().equalsIgnoreCase("null")){
            if(o.getImage2().equalsIgnoreCase("null")){
                if(o.getImage3().equalsIgnoreCase("null")){
                    setToolbarImageFlag();
                } else {
                    try{
                        Bitmap resultBmp = BlurBuilder.blur(this, getBitmapFromStorage(o.getImage3()), 10f);
                        toolbar_bg.setImageBitmap(resultBmp);
                    } catch (Exception e){
                        setToolbarImageFlag();
                    }
                }
            } else {
                try{
                    Bitmap resultBmp = BlurBuilder.blur(this, getBitmapFromStorage(o.getImage2()), 10f);
                    toolbar_bg.setImageBitmap(resultBmp);
                } catch (Exception e){
                    setToolbarImageFlag();
                }
            }
        } else {
            try{
                Bitmap resultBmp = BlurBuilder.blur(this, getBitmapFromStorage(o.getImage1()), 10f);
                toolbar_bg.setImageBitmap(resultBmp);
            } catch (Exception e){
                setToolbarImageFlag();
            }
        }
    }

    private void setToolbarImageFlag(){
        if(Build.VERSION.SDK_INT>=17){
            Bitmap resultBmp = BlurBuilder.blur(this, BitmapFactory.decodeResource(getResources(), R.drawable.india_flag_bg), 24f);
            toolbar_bg.setImageBitmap(resultBmp);
        } else {
            toolbar_bg.setImageDrawable(getResources().getDrawable(R.drawable.india_flag_bg));
            toolbar_bg.setAlpha(0.6f);
        }
    }

    private Bitmap getBitmapFromStorage(String path) {
        try {
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File path1 = cw.getDir("Images", Context.MODE_PRIVATE);
            File f = new File(path1, path);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            return b;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
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

}
