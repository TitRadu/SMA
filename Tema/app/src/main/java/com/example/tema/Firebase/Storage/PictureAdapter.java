package com.example.tema.Firebase.Storage;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tema.R;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder>{
    public List<Uri> uriList;
    private Context context;

    public PictureAdapter(List<Uri> uriList)
    {
        this.uriList = uriList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.row_upload_photos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.pictureUploadedView.getContext()).load(uriList.get(position)).apply(new RequestOptions().centerCrop()).into(holder.pictureUploadedView);

    }

    @Override
    public int getItemCount() {
        return uriList.size();
    }

    private void removeItemAtPosition(int position) {
        uriList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, uriList.size());

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView closeImageView;
        public ImageView pictureUploadedView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            closeImageView = itemView.findViewById(R.id.iv_row_upload_close);
            pictureUploadedView = itemView.findViewById(R.id.iv_row_upload_image);

            closeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        removeItemAtPosition(position);
                    }
                }
            });

        }

    }

}
