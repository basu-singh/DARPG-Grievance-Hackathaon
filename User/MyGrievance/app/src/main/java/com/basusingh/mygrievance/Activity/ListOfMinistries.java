package com.basusingh.mygrievance.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.basusingh.mygrievance.Adapter.Adapter_List_Ministries;
import com.basusingh.mygrievance.Helper.ListOfMinistriesItems;
import com.basusingh.mygrievance.Utils.RecyclerTouchListener;
import com.basusingh.mygrievance.Utils.RecyclerViewItemDivider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.basusingh.mygrievance.R;

import java.util.ArrayList;
import java.util.List;

public class ListOfMinistries extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayout error_layout;
    TextView error_text;
    List<ListOfMinistriesItems> mList;
    Adapter_List_Ministries mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_ministries);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
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

        loadData();

        LinearLayout btn_done = findViewById(R.id.done_btn);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData(){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        error_layout.setVisibility(View.GONE);

        ListOfMinistriesItems o;
        o = new ListOfMinistriesItems();
        o.setMinistry_name("Ministry of Administrative Reforms and PG");
        o.setOfficer_name("Prisca Mathew");
        o.setOfficer_designation("Deputy Secretary");
        o.setAddress("");
        o.setPhone("01123401429");
        o.setEmail("pmathew.edu[at]nic[dot]in");
        mList.add(o);

        o = new ListOfMinistriesItems();
        o.setMinistry_name("Ministry of Agriculture and Cooperation");
        o.setOfficer_name("Amitabh Gautam");
        o.setOfficer_designation("Joint Secretary PG");
        o.setAddress("Room No. 350, Krishi Bhavan New Delhi");
        o.setPhone("01123382454");
        o.setEmail("amitabh.gautam[at]gov[dot]in");
        mList.add(o);

        o = new ListOfMinistriesItems();
        o.setMinistry_name("Ministry of Animal Husbandry, Dairying");
        o.setOfficer_name("Shri G.N. Singh");
        o.setOfficer_designation("Joint Secretary GC");
        o.setAddress("");
        o.setPhone("01123389620");
        o.setEmail("gn.singh13[at]nic[dot]in");
        mList.add(o);

        o = new ListOfMinistriesItems();
        o.setMinistry_name("Ministry of Atomic Energy");
        o.setOfficer_name("Shri Sanjay Kumar");
        o.setOfficer_designation("Joint Secretary AA");
        o.setAddress("");
        o.setPhone("02222840309");
        o.setEmail("jsaa[at]dae.gov[dot]in");
        mList.add(o);

        o = new ListOfMinistriesItems();
        o.setMinistry_name("Ministry of Ayush");
        o.setOfficer_name("Shri S.K. Ahuja");
        o.setOfficer_designation("Director");
        o.setAddress("");
        o.setPhone("01124651644");
        o.setEmail("sk.ahuja07[at]nic[dot]in");
        mList.add(o);

        progressBar.setVisibility(View.GONE);
        error_layout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        mAdapter = new Adapter_List_Ministries(getApplicationContext(), mList);
        recyclerView.setAdapter(mAdapter);


    }


    public void addRecyclerTouchListener(){
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
