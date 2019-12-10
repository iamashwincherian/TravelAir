package com.example.travelair;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class myTrips extends AppCompatActivity {

    Button myAccBtn,homeBtn;
    RelativeLayout rl;
    RecyclerView recyclerView;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);
        runFadeInAnimation();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.appTheme));
        }

        db = new DBHelper(this);

        rl = (RelativeLayout) findViewById(R.id.rl);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> flightName = new ArrayList<String>();
        List<String> flightFrom = new ArrayList<String>();
        List<String> flightTo = new ArrayList<String>();
        List<String> flightDate = new ArrayList<String>();
        List<String> flightTime = new ArrayList<String>();
        List<String> flightCclass = new ArrayList<String>();

        Cursor res=db.getTrip(SaveSharedPreference.getId(getApplicationContext()));
        while ((res.moveToNext()))
        {
            flightName.add(res.getString(1));
            flightFrom.add(res.getString(2));
            flightTo.add(res.getString(3));
            flightDate.add(res.getString(4));
            flightTime.add(res.getString(5));
            flightCclass.add(res.getString(10));
        }
        recyclerView.setAdapter(new myTripsAdapter((ArrayList) flightName,(ArrayList) flightFrom,(ArrayList) flightTo,(ArrayList) flightDate,(ArrayList) flightTime,(ArrayList) flightCclass));

        myAccBtn = (Button) findViewById(R.id.myAccBtn);
        homeBtn = (Button) findViewById(R.id.homeBtn);
        myAccountIntent();
        homeIntent();
    }

    public void myAccountIntent(){
        myAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), myAccount.class));
                finish();
            }
        });
    }

    public void homeIntent(){
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), travel.class));
                finish();
            }
        });
    }

    private void runFadeInAnimation() {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.fadein);
        a.reset();
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        rl.clearAnimation();
        rl.startAnimation(a);
    }
}
