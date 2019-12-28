package com.basusingh.mygrievanceadmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basusingh.mygrievanceadmin.Helper.ViewGrievanceItems;
import com.basusingh.mygrievanceadmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterViewGrievance extends RecyclerView.Adapter<AdapterViewGrievance.ViewHolder> {

    private Context mContext;
    private List<ViewGrievanceItems> mList;

    public AdapterViewGrievance(Context context, List<ViewGrievanceItems> list){
        this.mContext = context;
        this.mList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView registration_no, ministry_department, location, subject_content, rating;

        public ViewHolder(View v){
            super(v);
            registration_no = v.findViewById(R.id.registration_no);
            ministry_department = v.findViewById(R.id.ministry);
            location = v.findViewById(R.id.location);
            subject_content = v.findViewById(R.id.subject);
            rating = v.findViewById(R.id.rating);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position){
        View v =  LayoutInflater.from(mContext)
                .inflate(R.layout.item_all_grievance, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position){
        ViewGrievanceItems o = mList.get(position);
        holder.registration_no.setText(o.getRegistration_no());
        holder.ministry_department.setText(o.getMinistry_department());
        holder.location.setText(o.getDistname() + ", " + o.getState_name() + ", " + o.getCountry_name());
        holder.subject_content.setText(o.getSubject_content());
        holder.rating.setText(o.getRating());
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }
}
