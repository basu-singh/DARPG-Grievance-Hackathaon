package com.basusingh.mygrievance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.basusingh.mygrievance.Adapter.Adapter_View_Grievance;
import com.basusingh.mygrievance.DatabasePreference.MyGrievance.MyGrievanceDatabaseClient;
import com.basusingh.mygrievance.DatabasePreference.MyGrievance.MyGrievanceItems;
import com.basusingh.mygrievance.R;
import com.basusingh.mygrievance.Utils.RecyclerTouchListener;
import com.basusingh.mygrievance.Utils.RecyclerViewItemDivider;

import java.util.ArrayList;
import java.util.List;

public class ViewAllGrievance extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayout error_layout;
    List<MyGrievanceItems> mList;
    Adapter_View_Grievance mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_grievance);

        LinearLayout done_btn = findViewById(R.id.done_btn);
        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mList = new ArrayList<>();
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
    }

    @Override
    public void onStart(){
        super.onStart();

        error_layout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        loadList();
    }

    @Override
    public void onStop(){
        mList.clear();
        super.onStop();
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    private void loadList(){
        showProgressBar();
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... p){
                mList = MyGrievanceDatabaseClient.getInstance(getApplicationContext()).getAppDatabase().MyGrievanceDao().getAll();
                return null;
            }
            @Override
            protected void onPostExecute(Void p){
                hideProgressBar();
                if(mList.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                    error_layout.setVisibility(View.VISIBLE);
                } else {
                    error_layout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    mAdapter = new Adapter_View_Grievance(getApplicationContext(), mList);
                    recyclerView.setAdapter(mAdapter);
                }
            }
        }.execute();
    }

    public void addRecyclerTouchListener(){
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                MyGrievanceItems o = mList.get(position);
                startActivity(new Intent(getApplicationContext(), ViewGrievance.class).putExtra("data", o));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
