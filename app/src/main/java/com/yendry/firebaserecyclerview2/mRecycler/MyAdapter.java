package com.yendry.firebaserecyclerview2.mRecycler;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.yendry.firebaserecyclerview2.MainActivity;
import com.yendry.firebaserecyclerview2.R;
import com.yendry.firebaserecyclerview2.mData.Movie;
import com.yendry.firebaserecyclerview2.mFireBase.FireBaseClient;
import com.yendry.firebaserecyclerview2.mPicasso.PicassoClient;

import java.util.ArrayList;

/**
 * Created by User on 10/10/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Movie> movies;

    public MyAdapter(Context c, ArrayList<Movie> movies) {
        this.c = c;
        this.movies = movies;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, parent, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
            holder.nameTxt.setText(movies.get(position).getName());
        PicassoClient.downloadImage(c, movies.get(position).getUrl(),holder.img );

        TextView del = holder.chK;
        Button updateButton = holder.updateButton;

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMovie(movies.get(position).getKey(), position);
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String k = movies.get(position).getKey();
                MainActivity.fireBaseClient.deleteOnline(k);
            }
        });

    }

    private void updateMovie(final String key, int pos) {
        final Dialog d=new Dialog(c);
        d.setTitle("Save Online");
        d.setContentView(R.layout.dialoglayout);

       final EditText nameEditText = (EditText) d.findViewById(R.id.nameEditText);
       final EditText urlEditText = (EditText) d.findViewById(R.id.urlEditText);
       Button saveBtn = (Button) d.findViewById(R.id.saveBtn);
        nameEditText.setText(movies.get(pos).getName());
        urlEditText.setText(movies.get(pos).getUrl());
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fireBaseClient.updateOnline(key, nameEditText.getText().toString(),urlEditText.getText().toString() );
                nameEditText.setText("");
                urlEditText.setText("");
                d.cancel();
            }
        });
        d.show();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}
