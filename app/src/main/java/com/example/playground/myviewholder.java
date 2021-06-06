package com.example.playground;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myviewholder extends RecyclerView.ViewHolder {

    ImageView Rimage;
    TextView Rlocation,Rgroundname;
    View v;

    public myviewholder(@NonNull View itemView) {
        super(itemView);
        Rimage=itemView.findViewById(R.id.my_image);
        Rlocation=itemView.findViewById(R.id.location_name);
        Rgroundname=itemView.findViewById(R.id.ground_name);
        v=itemView;
    }
}
