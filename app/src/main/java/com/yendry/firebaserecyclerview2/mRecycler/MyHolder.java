package com.yendry.firebaserecyclerview2.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yendry.firebaserecyclerview2.R;


/**
 * Created by User on 10/10/2016.
 */

public class MyHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    ImageView img;
    TextView chK;
    Button updateButton;

    public MyHolder(View itemView) {
        super(itemView);
        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        img = (ImageView) itemView.findViewById(R.id.movieImage);
        chK = (TextView) itemView.findViewById(R.id.chK);
        updateButton = (Button) itemView.findViewById(R.id.updateBtn);
    }
}
