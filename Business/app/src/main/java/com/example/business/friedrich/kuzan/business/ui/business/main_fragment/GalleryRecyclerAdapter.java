package com.example.business.friedrich.kuzan.business.ui.business.main_fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.business.friedrich.kuzan.business.R;
import com.example.business.friedrich.kuzan.business.model.RotateImage;
import com.example.business.friedrich.kuzan.business.model.interface_for_data.GlobalConstants;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class GalleryRecyclerAdapter extends RecyclerView.Adapter<GalleryRecyclerAdapter.ViewHolder> {

    private ArrayList<Uri> mUris;

    public GalleryRecyclerAdapter(ArrayList<Uri> mUris) {
        this.mUris = mUris;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.layout_item_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mUris.get(position).getAuthority().equals(GlobalConstants.AUTHOR)) {
            Glide.with(holder.mImageView.getContext())
                .asDrawable()
                .load(mUris.get(position))
                .into(holder.mImageView);
        } else {
            Observable.just(new RotateImage(mUris.get(position)))
                .subscribeOn(Schedulers.io())
                .map(rotateImage -> rotateImage.simpleRotateBitmap())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        holder.mImageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        }
    }

    @Override
    public int getItemCount() {
        return mUris.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_item_gallery);
        }
    }
}
