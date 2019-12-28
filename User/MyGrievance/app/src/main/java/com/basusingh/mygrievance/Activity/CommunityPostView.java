package com.basusingh.mygrievance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.basusingh.mygrievance.Adapter.Adapter_Community_Comment;
import com.basusingh.mygrievance.Helper.CommunityCommentItems;
import com.basusingh.mygrievance.Helper.CommunityItems;
import com.basusingh.mygrievance.R;
import com.basusingh.mygrievance.Utils.AddToFirebaseAnalytics;
import com.basusingh.mygrievance.Utils.RecyclerViewItemDivider;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CommunityPostView extends AppCompatActivity {

    LinearLayout done_btn, upvotes_layout;
    TextView user, title, description, timestamp, upvotes, error_text;
    CardView image1Layout, image2Layout, image3Layout;
    ImageView image1, image2, image3;
    AppCompatEditText comment;
    FrameLayout send_btn;
    RecyclerView recyclerView;
    CommunityItems o;
    boolean upvoted = false;
    ProgressDialog progressDialog;
    List<CommunityCommentItems> mList;
    Adapter_Community_Comment mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_post_view);

        Intent i = getIntent();
        if(i != null){
            o = (CommunityItems) getIntent().getSerializableExtra("data");
        } else {
            Toast.makeText(getApplicationContext(), "Sorry, an error occurred!", Toast.LENGTH_LONG).show();
            new AddToFirebaseAnalytics(getApplicationContext()).addData("null", "Error occurred in CommunityPostView.java", "intent is null");
            finish();
            return;
        }

        mList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        done_btn = findViewById(R.id.done_btn);
        user = findViewById(R.id.user);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        timestamp = findViewById(R.id.timestamp);
        upvotes = findViewById(R.id.upvotes);
        error_text = findViewById(R.id.error_text);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image1Layout = findViewById(R.id.image1Layout);
        image2Layout = findViewById(R.id.image2Layout);
        image3Layout = findViewById(R.id.image3Layout);
        comment = findViewById(R.id.comment);
        upvotes_layout = findViewById(R.id.upvotesLayout);
        send_btn = findViewById(R.id.send_btn);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerViewItemDivider(
                getApplicationContext()
        ));

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        error_text.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        progressDialog.setTitle("Please wait");
        setUpView();
        loadComments();

        upvotes_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpvote();
            }
        });
    }

    private void doUpvote(){
        if(upvoted){
            Toast.makeText(getApplicationContext(), "Already upvoted this post", Toast.LENGTH_LONG).show();
        } else {
            int i = Integer.valueOf(upvotes.getText().toString());
            i++;
            upvotes.setText(String.valueOf(i));
            upvoted = true;
        }
    }

    private void setUpView(){
        user.setText(o.getUser());
        title.setText(o.getTitle());
        description.setText(o.getDescription());
        timestamp.setText(o.getTimestamp());
        upvotes.setText(o.getUpvotes());
        if(!o.getImage1().equalsIgnoreCase("null")){
            image1Layout.setVisibility(View.VISIBLE);
            Picasso.with(getApplicationContext()).load(o.getImage1()).into(image1);
        } else {
            image1Layout.setVisibility(View.GONE);
        }
        if(!o.getImage2().equalsIgnoreCase("null")){
            image2Layout.setVisibility(View.VISIBLE);
            Picasso.with(getApplicationContext()).load(o.getImage2()).into(image2);
        } else {
            image2Layout.setVisibility(View.GONE);
        }
        if(!o.getImage3().equalsIgnoreCase("null")){
            image3Layout.setVisibility(View.VISIBLE);
            Picasso.with(getApplicationContext()).load(o.getImage3()).into(image3);
        } else {
            image3Layout.setVisibility(View.GONE);
        }

    }

    private void loadComments(){
        progressDialog.show();
        CommunityCommentItems o;

        o = new CommunityCommentItems();
        o.setId("1");
        o.setUser("Rachit Singhal");
        o.setTimestamp("24/11/2019 16:45");
        o.setComment("Amazing, I want to join as well. Please let me know how can I help.");
        mList.add(o);

        o = new CommunityCommentItems();
        o.setId("2");
        o.setUser("Akansha M");
        o.setTimestamp("24/11/2019 19:45");
        o.setComment("That's a great initiative.");
        mList.add(o);

        o = new CommunityCommentItems();
        o.setId("3");
        o.setUser("Mudit Sinha");
        o.setTimestamp("25/11/2019 09:15");
        o.setComment("How can I volunteer for this?");
        mList.add(o);

        mAdapter = new Adapter_Community_Comment(getApplicationContext(), mList);
        recyclerView.setAdapter(mAdapter);
        progressDialog.dismiss();
        recyclerView.setVisibility(View.VISIBLE);
    }
}
