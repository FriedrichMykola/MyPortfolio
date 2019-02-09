package com.example.business.friedrich.kuzan.business.ui.business.body_design_activity.fragment_body_design.editor_gallery;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.application.MyApp;
import com.example.business.friedrich.kuzan.business.model.body_design.Gallery;
import com.example.business.friedrich.kuzan.business.model.business.Business;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.GlobalConstants;
import com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus.EBDeleteImage;
import com.google.android.material.button.MaterialButton;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Gallery mGallery;

    public GalleryAdapter(Gallery mGallery) {
        this.mGallery = mGallery;
    }

    public void setUri(Uri uri) {
        mGallery.getmUris().add(uri);
        notifyItemChanged(mGallery.getmUris().size() - 1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_rec_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*code is hidden*/
    }

    @Override
    public int getItemCount() {
        return mGallery.getmUris().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialButton mButtonDelete;
        private CircleImageView mImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mButtonDelete = itemView.findViewById(R.id.button_delete_item_gallery);
            mImage = itemView.findViewById(R.id.image_item);
        }
    }

}
