package com.yendry.firebaserecyclerview2.mPicasso;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yendry.firebaserecyclerview2.R;

/**
 * Created by User on 10/10/2016.
 */

public class PicassoClient {


    public static void downloadImage(Context c, String url, ImageView img){

        if(url!=null && url.length()>0){
            Picasso.with(c).load(url).placeholder(R.drawable.place_holder).into(img);
        }else{
            Picasso.with(c).load(R.drawable.place_holder).into(img);
        }
    }
}
