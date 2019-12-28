package com.basusingh.mygrievanceadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.basusingh.mygrievanceadmin.Adapter.AdapterViewGrievance;
import com.basusingh.mygrievanceadmin.Helper.ViewGrievanceItems;
import com.basusingh.mygrievanceadmin.R;
import com.basusingh.mygrievanceadmin.Utils.NetworkSingleton;
import com.basusingh.mygrievanceadmin.Utils.RecyclerTouchListener;
import com.basusingh.mygrievanceadmin.Utils.RecyclerViewItemDivider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewAllGrievance extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    LinearLayout error_layout;
    TextView error_text1, error_text2;
    List<ViewGrievanceItems> mList;
    AdapterViewGrievance mAdapter;

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

        error_text1 = findViewById(R.id.error_text_1);
        error_text2 = findViewById(R.id.error_text_2);
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
        progressBar.setVisibility(View.GONE);
        loadList();
    }

    private void loadList(){
        showProgressBar();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.data.gov.in/restricted/cpgrams?api-key=579b464db66ec23bdd000001a1730fc1f4124a2e6d49d7d2c0239d5f&format=json&offset=0&limit=10",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showErrorDialog("Oops, we got a problem!", "There seems to be an issue with the internet connection. Please try again later.");
                    }
                }) {
        };

        NetworkSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void parseData(String data){
        try{
            JSONObject obj = new JSONObject(data);
            JSONArray records = obj.getJSONArray("records");
            for(int i = 0; i<records.length(); i++){
                JSONObject a = records.getJSONObject(i);
                ViewGrievanceItems o = new ViewGrievanceItems();
                o.setClosing_date(a.getString("closing_date"));
                o.setComments(a.getString("comments"));
                o.setCountry_name(a.getString("country_name"));
                o.setDiarydate(a.getString("diarydate"));
                o.setDistname(a.getString("distname"));
                o.setMinistry_department(a.getString("ministry_department"));
                o.setRating(a.getString("rating"));
                o.setRegistration_no(a.getString("registration_no"));
                o.setSourceName(a.getString("SourceName"));
                o.setState_name(a.getString("state_name"));
                o.setSubject_content(a.getString("subject_content"));
                o.setRatingdate(a.getString("ratingdate"));
                mList.add(o);
            }
        } catch (Exception e){
            e.printStackTrace();
            showErrorDialog("Oops, we got a problem!", "There seems to be an issue with the internet connection. Please try again later.");
        }

        mAdapter = new AdapterViewGrievance(getApplicationContext(), mList);
        recyclerView.setAdapter(mAdapter);
        hideProgressBar();
        error_layout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }


    public void showErrorDialog(String title, String message){
        hideProgressBar();
        recyclerView.setVisibility(View.GONE);
        error_layout.setVisibility(View.VISIBLE);
        error_text1.setText(title);
        error_text2.setText(message);
    }

    @Override
    public void onStop(){
        mList.clear();
        super.onStop();
    }

    private void showProgressBar(){
        error_layout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
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
