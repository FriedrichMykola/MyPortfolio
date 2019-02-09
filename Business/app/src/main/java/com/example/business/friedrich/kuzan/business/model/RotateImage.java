package com.example.business.friedrich.kuzan.business.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Log;

import static com.example.business.friedrich.kuzan.business.model.interface_for_data.GlobalConstants.HEIGHT;
import static com.example.business.friedrich.kuzan.business.model.interface_for_data.GlobalConstants.WIDTH;

public class RotateImage {

    protected Uri mUri;

    public RotateImage(Uri uri) {
        this.mUri = uri;
    }

    public Bitmap simpleRotateBitmap() {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(mUri.getLastPathSegment(), options);

        options.inSampleSize = calculateInSampleSize(options);

        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(mUri.getLastPathSegment(), options);

        return rotateImageIfRequired(bitmap);
    }

    private int calculateInSampleSize(BitmapFactory.Options options) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > HEIGHT || width > WIDTH) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= HEIGHT
                && (halfWidth / inSampleSize) >= WIDTH) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private Bitmap rotateImageIfRequired(Bitmap bitmap) {
        ExifInterface exifInterface;

        try {
            exifInterface = new ExifInterface(mUri.getLastPathSegment());

            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return rotateImage(bitmap, 90);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return rotateImage(bitmap, 180);
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return rotateImage(bitmap, 270);
                default:
                    return bitmap;
            }

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return bitmap;
    }

    private Bitmap rotateImage(Bitmap bitmap, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return rotatedImg;
    }

}
