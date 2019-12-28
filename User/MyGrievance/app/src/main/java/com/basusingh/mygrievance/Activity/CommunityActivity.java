package com.basusingh.mygrievance.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.basusingh.mygrievance.Adapter.Adapter_Community;
import com.basusingh.mygrievance.Helper.CommunityItems;
import com.basusingh.mygrievance.Utils.RecyclerTouchListener;
import com.basusingh.mygrievance.Utils.RecyclerViewItemDivider;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.basusingh.mygrievance.R;

import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends AppCompatActivity {

    TextView error_text;
    LinearLayout error_layout;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    List<CommunityItems> mList;
    Adapter_Community mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mList = new ArrayList<>();
        error_text = findViewById(R.id.error_text);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        error_layout = findViewById(R.id.error_layout);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerViewItemDivider(
                getApplicationContext()
        ));

        addRecyclerTouchListener();

        recyclerView.setVisibility(View.GONE);
        error_layout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        showPostOption();

    }

    private void showPostOption(){
        LayoutInflater factory = LayoutInflater.from(this);
        final View DialogView = factory.inflate(R.layout.community_post_option_layout, null);
        final AlertDialog Dialog = new AlertDialog.Builder(this).create();
        Dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if(recyclerView.getVisibility() == View.GONE){
                    progressBar.setVisibility(View.GONE);
                    error_text.setText("Please select an option from option menu.");
                    error_layout.setVisibility(View.VISIBLE);
                }
            }
        });
        Dialog.setView(DialogView);
        DialogView.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = DialogView.findViewById(R.id.radidoGroup);
                if(radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_LONG).show();
                } else {
                    switch (radioGroup.getCheckedRadioButtonId()){
                        case R.id.btn1:
                            doSearch("General", 1);
                            Dialog.dismiss();
                            break;
                        case R.id.btn2:
                            doSearch("Local", 2);
                            Dialog.dismiss();
                            break;
                        case R.id.btn3:
                            doSearch("Cleanliness", 3);
                            Dialog.dismiss();
                            break;
                        case R.id.btn4:
                            doSearch("Education", 4);
                            Dialog.dismiss();
                            break;
                        case R.id.btn5:
                            doSearch("Healthcare", 5);
                            Dialog.dismiss();
                            break;
                        case R.id.btn6:
                            doSearch("Finance", 6);
                            Dialog.dismiss();
                            break;
                    }
                }
            }
        });

        DialogView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.cancel();
                if(recyclerView.getVisibility() == View.GONE){
                    progressBar.setVisibility(View.GONE);
                    error_text.setText("Please select an option from option menu.");
                    error_layout.setVisibility(View.VISIBLE);
                }
            }
        });

        Dialog.show();
    }

    private void doSearch(String option, int id){

        progressBar.setVisibility(View.VISIBLE);

        mList.clear();

        CommunityItems o;

        o = new CommunityItems();
        o.setId("1");
        o.setTitle("Planting activity in municipal school");
        o.setDescription("We are organising a plating activity in municipal school at Lajpat Nagar 4. Around 2000 trees will be planted along with a painting competition being organised for children. Need volunteer for same.");
        o.setTimestamp("23/11/2019 11:43");
        o.setUser("Animesh Singh");
        o.setUpvotes("500");
        o.setImage1("https://s3.ap-south-1.amazonaws.com/hansindia-bucket/1434_AKTP_High_School.jpg");
        o.setImage2("null");
        o.setImage3("null");
        mList.add(o);

        o = new CommunityItems();
        o.setId("2");
        o.setTitle("Starting a Cleanliness drive");
        o.setDescription("Hello, I'm organising a cleanliness drive on 26th January, 2020. We will doing a round cleaning of an entire neighbourhood and also distribute information posters");
        o.setTimestamp("28/12/2019 11:43");
        o.setUser("Swati Sanghi");
        o.setUpvotes("1280");
        o.setImage1("https://www.sentinelassam.com/wp-content/uploads/2019/03/Cleanliness-Drive-held-in-Naharlagun.jpg");
        o.setImage2("null");
        o.setImage3("null");
        mList.add(o);

        mAdapter = new Adapter_Community(getApplicationContext(), mList);
        recyclerView.setAdapter(mAdapter);

        progressBar.setVisibility(View.GONE);
        error_layout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public void addRecyclerTouchListener(){
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                CommunityItems o = mList.get(position);
                startActivity(new Intent(getApplicationContext(), CommunityPostView.class).putExtra("data", o));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_community, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_option:
                showPostOption();
                return true;
            case R.id.action_create:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
