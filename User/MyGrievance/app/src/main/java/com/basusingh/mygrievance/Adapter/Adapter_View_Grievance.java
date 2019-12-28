package com.basusingh.mygrievance.Adapter;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.basusingh.mygrievance.DatabasePreference.MyGrievance.MyGrievanceItems;
import com.basusingh.mygrievance.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class Adapter_View_Grievance extends RecyclerView.Adapter<Adapter_View_Grievance.ViewHolder> {

    private Context mContext;
    private List<MyGrievanceItems> mList;

    public Adapter_View_Grievance(Context context, List<MyGrievanceItems> list){
        this.mContext = context;
        this.mList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView subject, message, ministry, timestamp;
        ImageView image1;
        CardView imageLayout;

        public ViewHolder(View v){
            super(v);
            subject = v.findViewById(R.id.subject);
            message = v.findViewById(R.id.message);
            ministry = v.findViewById(R.id.ministry);
            timestamp = v.findViewById(R.id.timestamp);
            image1 = v.findViewById(R.id.image1);
            imageLayout = v.findViewById(R.id.imageLayout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position){
        View v =  LayoutInflater.from(mContext)
                .inflate(R.layout.item_view_grievance, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        MyGrievanceItems o = mList.get(position);
        holder.subject.setText(o.getSubject());
        holder.message.setText(o.getMessage());
        holder.ministry.setText(o.getMinistry());
        holder.timestamp.setText(o.getTimestamp());
        if(!o.getImage1().equalsIgnoreCase("null")){
            try {
                ContextWrapper cw = new ContextWrapper(mContext);
                File path1 = cw.getDir("Images", Context.MODE_PRIVATE);
                File f = new File(path1, o.getImage1());
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                holder.image1.setImageBitmap(b);
                holder.imageLayout.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if(!o.getImage2().equalsIgnoreCase("null")){
            try {
                ContextWrapper cw = new ContextWrapper(mContext);
                File path1 = cw.getDir("Images", Context.MODE_PRIVATE);
                File f = new File(path1, o.getImage2());
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                holder.image1.setImageBitmap(b);
                holder.imageLayout.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if(!o.getImage3().equalsIgnoreCase("null")){
            try {
                ContextWrapper cw = new ContextWrapper(mContext);
                File path1 = cw.getDir("Images", Context.MODE_PRIVATE);
                File f = new File(path1, o.getImage3());
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                holder.image1.setImageBitmap(b);
                holder.imageLayout.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            holder.imageLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }
}
