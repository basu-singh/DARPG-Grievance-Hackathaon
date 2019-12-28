package com.basusingh.mygrievance.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.basusingh.mygrievance.Adapter.Adapter_Help_Center;
import com.basusingh.mygrievance.Helper.HelpCenterItems;
import com.basusingh.mygrievance.R;
import com.basusingh.mygrievance.Utils.RecyclerViewItemDivider;

import java.util.ArrayList;
import java.util.List;

public class HelpCenter extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    List<HelpCenterItems> mList;
    Adapter_Help_Center mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);

        mList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerViewItemDivider(
                getApplicationContext()
        ));

        loadData();

        LinearLayout done_btn = findViewById(R.id.done_btn);
        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void loadData(){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        HelpCenterItems o;

        o = new HelpCenterItems();
        o.setTitle("How to submit a grievance?");
        o.setDescription("Dummy text");
        mList.add(o);

        o = new HelpCenterItems();
        o.setTitle("I have submiited a grievance. Now what?");
        o.setDescription("Dummy text");
        mList.add(o);

        o = new HelpCenterItems();
        o.setTitle("How long it takes to get the issue resolved?");
        o.setDescription("Dummy text");
        mList.add(o);

        o = new HelpCenterItems();
        o.setTitle("I'm not satisfied with the solution. Can I seek further help?");
        o.setDescription("Yes please. If you are not satisfied with the solution provided, you can reply back to the same message. If the case has been closed already, it will be reinitiated.");
        mList.add(o);

        o = new HelpCenterItems();
        o.setTitle("What is community in My Grievance app?");
        o.setDescription("Dummy text");
        mList.add(o);

        mAdapter = new Adapter_Help_Center(getApplicationContext(), mList);
        recyclerView.setAdapter(mAdapter);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
