package com.basusingh.mygrievance.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.basusingh.mygrievance.DatabasePreference.MyGrievance.MyGrievanceDatabaseClient;
import com.basusingh.mygrievance.DatabasePreference.MyGrievance.MyGrievanceItems;
import com.basusingh.mygrievance.R;
import com.basusingh.mygrievance.Utils.ImageCompressionUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CreateGrievance extends AppCompatActivity {

    ImageView image1, image2, image3, btn_add_image;
    AppCompatEditText subject, message;
    AppCompatSpinner ministry;
    boolean image1Added = false, image2Added = false, image3Added = false;
    RelativeLayout image1Layout, image2Layout, image3Layout;
    FrameLayout image1delete, image2delete, image3delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_grievance);

        LinearLayout send_btn = findViewById(R.id.send_btn);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });

        LinearLayout cancel_btn = findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        btn_add_image = findViewById(R.id.btn_add_image);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        ministry = findViewById(R.id.ministry);

        image1Layout = findViewById(R.id.image1Layout);
        image1delete = findViewById(R.id.image1Delete);
        image2Layout = findViewById(R.id.image2Layout);
        image2delete = findViewById(R.id.image2Delete);
        image3Layout = findViewById(R.id.image3Layout);
        image3delete = findViewById(R.id.image3Delete);

        ArrayAdapter<CharSequence> adapter_s = ArrayAdapter.createFromResource(this,
                R.array.ministries, android.R.layout.simple_spinner_item);
        adapter_s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ministry.setAdapter(adapter_s);

        setClickListener();
    }

    private void validateData(){
        if(subject.getText().toString().isEmpty() || subject.getText().toString().length()<10){
            showErrorDialog("Error", "Please enter valid subject");
            return;
        }
        if(message.getText().toString().isEmpty()){
            showErrorDialog("Error", "Please enter message");
            return;
        }
        showConfirmDialog();
    }

    private void showConfirmDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateGrievance.this);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                submitData();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setTitle("Are you sure you want to submit?");
        builder.setCancelable(false);
        builder.show();
    }

    private void showErrorDialog(String title, String message){
        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateGrievance.this);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.show();
    }

    private void submitData(){
        //Dummy Submit
        //Posting to local device only
        final ProgressDialog p = new ProgressDialog(this);
        p.setTitle("Submitting");
        p.setCancelable(false);
        p.show();

        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String timestamp = s.format(new Date());

        final MyGrievanceItems m = new MyGrievanceItems();
        m.setSubject(subject.getText().toString());
        m.setMessage(message.getText().toString());
        m.setMinistry(ministry.getSelectedItem().toString());
        m.setTimestamp(timestamp);
        final String sub_short = subject.getText().toString().substring(0, 8);


        final Bitmap bmap1, bmap2, bmap3;

        if(image1Added){
            m.setImage1(sub_short + "1.png");
            image1.buildDrawingCache();
            bmap1 = image1.getDrawingCache();
        } else {
            m.setImage1("null");
            bmap1 = null;
        }
        if(image2Added){
            m.setImage2(sub_short + "2.png");
            image2.buildDrawingCache();
            bmap2 = image2.getDrawingCache();
        } else {
            m.setImage2("null");
            bmap2 = null;
        }
        if(image3Added){
            m.setImage3(sub_short + "3.png");
            image3.buildDrawingCache();
            bmap3 = image3.getDrawingCache();
        } else {
            m.setImage3("null");
            bmap3 = null;
        }

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... p){
                if(bmap1 != null){
                    saveToInternalSorage(bmap1,sub_short + "1.png");
                }
                if(bmap2 != null){
                    saveToInternalSorage(bmap2,sub_short + "2.png");
                }
                if(bmap3 != null){
                    saveToInternalSorage(bmap3,sub_short + "3.png");
                }
                MyGrievanceDatabaseClient.getInstance(getApplicationContext()).getAppDatabase().MyGrievanceDao().insert(m);
                return null;
            }
            @Override
            protected void onPostExecute(Void a){
                p.dismiss();
                Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }.execute();

    }

    public String getStringImage(Bitmap bm){
        ByteArrayOutputStream ba=new ByteArrayOutputStream(  );
        bm.compress( Bitmap.CompressFormat.PNG,50,ba );
        byte[] by=ba.toByteArray();
        String encod= Base64.encodeToString( by, Base64.DEFAULT );
        return encod;
    }

    private void loadImageFromStorage(String path) {
        try {
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File path1 = cw.getDir("Images", Context.MODE_PRIVATE);
            File f = new File(path1, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private String saveToInternalSorage(Bitmap bitmapImage, String filename) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("Images", Context.MODE_PRIVATE);
        File mypath = new File(directory, filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }

    private void setClickListener(){
        image1delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image1.setImageResource(0);
                image1Added = false;
                image1Layout.setVisibility(View.GONE);
                if(btn_add_image.getVisibility() != View.VISIBLE){
                    btn_add_image.setVisibility(View.VISIBLE);
                }
            }
        });

        image2delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image2.setImageResource(0);
                image2Added = false;
                image2Layout.setVisibility(View.GONE);
                if(btn_add_image.getVisibility() != View.VISIBLE){
                    btn_add_image.setVisibility(View.VISIBLE);
                }
            }
        });

        image3delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image3.setImageResource(0);
                image3Added = false;
                image3Layout.setVisibility(View.GONE);
                if(btn_add_image.getVisibility() != View.VISIBLE){
                    btn_add_image.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image1Added && image2Added && image3Added){
                    btn_add_image.setVisibility(View.GONE);
                } else {
                    if(image1Added){
                        if (image2Added) {
                            openImageChooser(103);
                        } else {
                            openImageChooser(102);
                        }
                    } else {
                        openImageChooser(101);
                    }
                }
            }
        });
    }

    void openImageChooser(int val) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), val);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(
                        selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bmp = BitmapFactory.decodeStream(imageStream);
            Bitmap bitmap = getResizedBitmap(bmp, 800);
            if (requestCode == 101) {
                image1.setImageBitmap(bitmap);
                image1Layout.setVisibility(View.VISIBLE);
                image1Added = true;
                if(image2Added && image3Added){
                    btn_add_image.setVisibility(View.GONE);
                }
            } else if (requestCode == 102) {
                image2.setImageBitmap(bitmap);
                image2Layout.setVisibility(View.VISIBLE);
                image2Added = true;
                if(image1Added && image3Added){
                    btn_add_image.setVisibility(View.GONE);
                }
            } else if (requestCode == 103) {
                image3.setImageBitmap(bitmap);
                image3Layout.setVisibility(View.VISIBLE);
                image3Added = true;
                if(image1Added && image2Added){
                    btn_add_image.setVisibility(View.GONE);
                }
            }
        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        int ratio = 1;

        if(width > height){
            if(width>maxSize){
                ratio = width/maxSize;
                width = maxSize;
                height = height/ratio;
            }
        } else {
            if(height>maxSize){
                ratio = height/maxSize;
                height = maxSize;
                width = width/ratio;
            }
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public abstract class ImageCompressionAsyncTask extends AsyncTask<String, Void, byte[]> {

        @Override
        protected byte[] doInBackground(String... strings) {
            if(strings.length == 0 || strings[0] == null)
                return null;
            return ImageCompressionUtils.compressImage(strings[0]);
        }

        protected abstract void onPostExecute(byte[] imageBytes) ;
    }

}