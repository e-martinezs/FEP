package com.onthego.onthego.utils;

import android.widget.ImageView;

import com.onthego.onthego.R;
import com.squareup.picasso.Picasso;

public class ImageLoader {

    public static void loadImage(String url, ImageView imageView){
        try {
            if (!url.isEmpty()) {
                Picasso.get().load(url).error(R.drawable.ic_image).into(imageView);
            } else {
                Picasso.get().load(R.drawable.ic_image).error(R.drawable.ic_image).into(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
