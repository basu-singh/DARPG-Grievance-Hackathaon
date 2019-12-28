package com.basusingh.mygrievance.Adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basusingh.mygrievance.Helper.CommunityItems;
import com.basusingh.mygrievance.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Community extends RecyclerView.Adapter<Adapter_Community.ViewHolder> {

    private Context mContext;
    private List<CommunityItems> mList;

    public Adapter_Community(Context context, List<CommunityItems> list){
        this.mContext = context;
        this.mList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, description, user, timestamp, upvotes;
        ImageView image;
        public ViewHolder(View v){
            super(v);
            title = v.findViewById(R.id.title);
            description = v.findViewById(R.id.description);
            image = v.findViewById(R.id.image);
            user = v.findViewById(R.id.user);
            timestamp = v.findViewById(R.id.timestamp);
            upvotes = v.findViewById(R.id.upvotes);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position){
        View v =  LayoutInflater.from(mContext)
                .inflate(R.layout.item_community, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position){
        CommunityItems o = mList.get(position);
        holder.title.setText(o.getTitle());
        holder.description.setText(o.getDescription());
        holder.user.setText(o.getUser());
        holder.timestamp.setText(o.getTimestamp());
        holder.upvotes.setText(o.getUpvotes());
        Picasso.with(mContext).load(o.getImage1()).into(holder.image);
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }
}
