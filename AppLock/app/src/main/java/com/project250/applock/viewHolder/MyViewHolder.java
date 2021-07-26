package com.project250.applock.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project250.applock.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView appName;
    public ImageView appIcon, lockApp ;

    public MyViewHolder(@NonNull View itemView){
        super(itemView);

        appName = itemView.findViewById(R.id.App_name);
        appIcon = itemView.findViewById(R.id.App_icon);
        lockApp = itemView.findViewById(R.id.Lock_app);
    }
}
