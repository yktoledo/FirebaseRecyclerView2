package com.yendry.firebaserecyclerview2;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.yendry.firebaserecyclerview2.mFireBase.FireBaseClient;

public class MainActivity extends AppCompatActivity {


    final static String DB_URL = "https://fir-recyclerview2-e8f2b.firebaseio.com/";
    EditText nameEditText, urlEditText;
    Button saveBtn;
    RecyclerView rv;
    public static FireBaseClient fireBaseClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv= (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        fireBaseClient = new FireBaseClient(this, DB_URL, rv);
        fireBaseClient.refreshData();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }








    private void displayDialog(){
        final Dialog d=new Dialog(this);
        d.setTitle("Save Online");
        d.setContentView(R.layout.dialoglayout);

        nameEditText = (EditText) d.findViewById(R.id.nameEditText);
        urlEditText = (EditText) d.findViewById(R.id.urlEditText);
        saveBtn = (Button) d.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireBaseClient.saveOnline(nameEditText.getText().toString(), urlEditText.getText().toString());
                nameEditText.setText("");
                urlEditText.setText("");
                d.cancel();
            }
        });
        d.show();
    }
}
