package com.yendry.firebaserecyclerview2.mFireBase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.yendry.firebaserecyclerview2.mData.Movie;
import com.yendry.firebaserecyclerview2.mRecycler.MyAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 10/10/2016.
 */

public class FireBaseClient {

    Context c;
    String DB_URL;
    RecyclerView rv;
    MyAdapter adapter;
    Firebase fire;
    ArrayList<Movie> movies = new ArrayList<>();

    public FireBaseClient(Context c, String DB_URL, RecyclerView rv) {
        this.c = c;
        this.DB_URL = DB_URL;
        this.rv = rv;

        Firebase.setAndroidContext(c);

        fire = new Firebase(DB_URL);
    }
//WRITE DATA
    public void saveOnline(String name, String url){

        Movie m = new Movie();
        m.setName(name);
        m.setUrl(url);

        Firebase f = fire.child("Movie").push();
        String key = f.getKey();
        m.setKey(key);
        f.setValue(m);


    }
//DELETE DATA
    public void deleteOnline(String key){
        fire.child("Movie").child(key).removeValue();
    }
//UPDATE DATA
    public void updateOnline(String key,String name, String url){

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("url", url);
        fire.child("Movie").child(key).updateChildren(map);
    }


//READ DATA
    public void getUpdates(DataSnapshot dataSnapshot){

        movies.clear();

        for(DataSnapshot ds:dataSnapshot.getChildren()){

            String temp = ds.getValue().toString();
            Log.d("FB",temp);
            String attr[]=temp.split(",");
            String name[] = attr[0].split("=");
            String key[] = attr[1].split("=");
            String url[]=attr[2].split("=");
            //Log.d("FB",url[1]);
            Movie m = new Movie();
            m.setName(name[1]);
            m.setUrl(url[1].substring(0,url[1].length()-1));
            m.setKey(key[1]);
            movies.add(m);

        }
        if (movies.size()>0){
            adapter=new MyAdapter(c, movies);
            rv.setAdapter(adapter);
        }else{
            Toast.makeText(c, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    public void refreshData(){
        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }






}
