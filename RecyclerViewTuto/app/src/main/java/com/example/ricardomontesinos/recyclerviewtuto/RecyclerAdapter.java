package com.example.ricardomontesinos.recyclerviewtuto;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PhotoHolder> {

    // Your adapter now knows where to look for data
    // a variable to hold your photos:
    private ArrayList<Photo> mPhotos;

    public RecyclerAdapter(ArrayList<Photo> photos) {
        mPhotos = photos;
    }

    @Override
    public RecyclerAdapter.PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.PhotoHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }
}
