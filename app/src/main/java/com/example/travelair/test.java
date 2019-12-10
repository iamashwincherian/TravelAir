package com.example.travelair;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class test extends AppCompatActivity {
    ListView list;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        list=(ListView)findViewById(R.id.list);
        db=new DBHelper(this);

        ArrayList<String> thelist=new ArrayList<>();
        Cursor res=db.getAllData();

        while ((res.moveToNext()))
        {
            thelist.add(res.getString(0)+"\n"+res.getString(1)+"\n"+res.getString(2)+"\n"+res.getString(3)+"\n"+res.getString(4)+"\n"+" accID : "+res.getString(6));
            ListAdapter listAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,thelist);
            list.setAdapter(listAdapter);
        }
    }
}
