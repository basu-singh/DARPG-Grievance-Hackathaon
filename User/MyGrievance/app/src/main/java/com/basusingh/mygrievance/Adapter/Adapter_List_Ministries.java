package com.basusingh.mygrievance.Adapter;

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

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.basusingh.mygrievance.Helper.ListOfMinistriesItems;
import com.basusingh.mygrievance.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class Adapter_List_Ministries extends RecyclerView.Adapter<Adapter_List_Ministries.ViewHolder> {

    private Context mContext;
    private List<ListOfMinistriesItems> mList;

    public Adapter_List_Ministries(Context context, List<ListOfMinistriesItems> list){
        this.mContext = context;
        this.mList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView ministry_name, officer_name, officer_designation, address, email, phone;
        ImageView image;
        LinearLayout address_layout;

        public ViewHolder(View v){
            super(v);
            ministry_name = v.findViewById(R.id.ministry_name);
            officer_designation = v.findViewById(R.id.officer_designation);
            officer_name = v.findViewById(R.id.officer_name);
            address = v.findViewById(R.id.address);
            email = v.findViewById(R.id.email);
            phone = v.findViewById(R.id.phone);
            image = v.findViewById(R.id.text_drawable);
            address_layout = v.findViewById(R.id.address_layout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position){
        View v =  LayoutInflater.from(mContext)
                .inflate(R.layout.item_list_of_ministries, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        ListOfMinistriesItems o = mList.get(position);
        holder.ministry_name.setText(o.getMinistry_name());
        holder.officer_name.setText(o.getOfficer_name());
        if(o.getAddress().isEmpty()){
            holder.address_layout.setVisibility(View.GONE);
        } else {
            holder.address.setText(o.getAddress());
        }
        holder.officer_designation.setText(o.getOfficer_designation());
        holder.email.setText(o.getEmail());
        holder.phone.setText(o.getPhone());

        String firstL = String.valueOf(o.getMinistry_name().charAt(12));
        TextDrawable drawable1 = TextDrawable.builder()
                .beginConfig()
                .fontSize(32)
                .bold()
                .endConfig()
                .buildRoundRect(firstL, ColorGenerator.MATERIAL.getRandomColor(), 50);
        holder.image.setImageDrawable(drawable1);
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }
}
