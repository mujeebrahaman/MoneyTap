package demo.android.moneytap.com.moneytap.utils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import demo.android.moneytap.com.moneytap.R;

public class GlideImageLoader {

    public static void loadProfileImage(final Context context, final String url, final ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .centerCrop()
                .placeholder(context.getResources().getDrawable(R.drawable.user_profile_icon))
                .error(context.getResources().getDrawable(R.drawable.user_profile_icon))
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    public static void loadProfileImage(final Context context, final Uri uri, final ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .asBitmap()
                .centerCrop()
                .placeholder(context.getResources().getDrawable(R.drawable.user_profile_icon))
                .error(context.getResources().getDrawable(R.drawable.user_profile_icon))
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
