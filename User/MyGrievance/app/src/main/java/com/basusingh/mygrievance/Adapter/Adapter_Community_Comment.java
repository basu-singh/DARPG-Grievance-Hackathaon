package com.basusingh.mygrievance.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basusingh.mygrievance.Helper.CommunityCommentItems;
import com.basusingh.mygrievance.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Community_Comment extends RecyclerView.Adapter<Adapter_Community_Comment.ViewHolder> {

    private Context mContext;
    private List<CommunityCommentItems> mList;

    public Adapter_Community_Comment(Context context, List<CommunityCommentItems> list){
        this.mContext = context;
        this.mList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView comment, user, timestamp;
        public ViewHolder(View v){
            super(v);
            comment = v.findViewById(R.id.comment);
            user = v.findViewById(R.id.user);
            timestamp = v.findViewById(R.id.timestamp);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position){
        View v =  LayoutInflater.from(mContext)
                .inflate(R.layout.item_community_comment, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position){
        CommunityCommentItems o = mList.get(position);
        holder.comment.setText(o.getComment());
        holder.user.setText(o.getUser());
        holder.timestamp.setText(o.getTimestamp());
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }
}
