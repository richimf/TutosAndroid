package com.example.ricardomontesinos.recyclerviewtuto;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ricardo.montesinos on 9/14/16.
 */
//1
public static class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //2
    private ImageView mItemImage;
    private TextView mItemDate;
    private TextView mItemDescription;
    private Photo mPhoto;

    //3
    private static final String PHOTO_KEY = "PHOTO";

    //4
    public PhotoHolder(View v) {
        super(v);

        mItemImage = (ImageView) v.findViewById(R.id.item_image);
        mItemDate = (TextView) v.findViewById(R.id.item_date);
        mItemDescription = (TextView) v.findViewById(R.id.item_description);
        v.setOnClickListener(this);
    }

    //5
    @Override
    public void onClick(View v) {
        Log.d("RecyclerView", "CLICK!");
    }
}