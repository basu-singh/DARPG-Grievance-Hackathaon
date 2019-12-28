package com.basusingh.mygrievance.Adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basusingh.mygrievance.Helper.HelpCenterItems;
import com.basusingh.mygrievance.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class Adapter_Help_Center extends RecyclerView.Adapter<Adapter_Help_Center.ViewHolder> {

    private Context mContext;
    private List<HelpCenterItems> mList;
    int shortAnimationDuration;

    public Adapter_Help_Center(Context context, List<HelpCenterItems> list){
        this.mContext = context;
        this.mList = list;
        shortAnimationDuration = mContext.getResources().getInteger(
                android.R.integer.config_shortAnimTime);;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, description;
        LinearLayout topLayout;
        public ViewHolder(View v){
            super(v);
            title = v.findViewById(R.id.title);
            description = v.findViewById(R.id.description);
            topLayout = v.findViewById(R.id.top_layout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position){
        View v =  LayoutInflater.from(mContext)
                .inflate(R.layout.item_help_center, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position){
        HelpCenterItems o = mList.get(position);
        holder.title.setText(o.getTitle());
        holder.description.setText(o.getDescription());
        holder.topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.description.getVisibility() == View.VISIBLE){
                    holder.description.animate()
                            .alpha(0f)
                            .setDuration(shortAnimationDuration)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    holder.description.setVisibility(View.GONE);
                                }
                            });
                } else {
                    holder.description.setAlpha(0f);
                    holder.description.setVisibility(View.VISIBLE);
                    holder.description.animate()
                            .alpha(1f)
                            .setDuration(shortAnimationDuration)
                            .setListener(null);
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }
}
