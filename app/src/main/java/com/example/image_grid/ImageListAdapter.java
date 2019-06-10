package com.example.image_grid;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.image_grid.model.RegistrationResponse;

import java.util.List;


public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {
    private Context applicationContext;
    private List<RegistrationResponse> registrationResponses;

    public ImageListAdapter(Context applicationContext) {
        this.applicationContext = applicationContext;
    }


    public void setData(List<RegistrationResponse> registrationResponses) {
        this.registrationResponses = registrationResponses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Glide.with(applicationContext)
                .load(registrationResponses.get(position).getUrl().getSmall())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);

                        return false;
                    }
                })
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        if (registrationResponses != null && registrationResponses.size() > 0) {
            return registrationResponses.size();
        } else {
            return 0;
        }
    }


    /**
     * View Holder for CONFIG LIST
     */

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);
            imageView = itemView.findViewById(R.id.imageView);
            progressBar = itemView.findViewById(R.id.progress_circular);


        }
    }


}
