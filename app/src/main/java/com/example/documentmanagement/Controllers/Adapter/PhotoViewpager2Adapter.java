package com.example.documentmanagement.Controllers.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.documentmanagement.R;
import com.example.documentmanagement.model.PhoTo;

import java.util.List;

public class PhotoViewpager2Adapter extends RecyclerView.Adapter<PhotoViewpager2Adapter.PhotoViewHolder>{


    private List<PhoTo> mListPhoto;

    public PhotoViewpager2Adapter(List<PhoTo> mListPhoto) {
        this.mListPhoto = mListPhoto;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        PhoTo   photo = mListPhoto.get(position);
        if(photo == null)
        {
            return;
        }
        else
            holder.imagePhoto.setImageResource(photo.getResourceId());
    }

    @Override
    public int getItemCount() {
        if(mListPhoto != null)
        {
            return mListPhoto.size();
        }
        else

           return 0;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagePhoto;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePhoto = itemView.findViewById(R.id.imgPhoto);
        }
    }
}
