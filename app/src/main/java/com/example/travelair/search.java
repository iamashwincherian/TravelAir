package com.example.travelair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class search extends AppCompatActivity {

    RelativeLayout flight1,flight2,flight3,flight4,flight5;
    Button backbtn;
    String From,To,Date,CClass,Time;
    TextView flight_name1,flight_name2,flight_name3,flight_name4,flight_name5;
    TextView fromText1,fromText21,fromText31,fromText41,fromText51, toText1,toText21,toText31,toText41,toText51;
    TextView price1,price2,price3,price4,price5;
    TextView timeText1,timeText2,timeText3,timeText4,timeText5;

    String p1 = "5420" ,p2 = "7430",p3 = "6880",p4 = "9100",p5 = "6800";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        From = getIntent().getStringExtra("From");
        To = getIntent().getStringExtra("To");
        Date = getIntent().getStringExtra("Date");
        CClass = getIntent().getStringExtra("CClass");

        timeText1 = (TextView) findViewById(R.id.timeText1);
        timeText2 = (TextView) findViewById(R.id.timeText2);
        timeText3 = (TextView) findViewById(R.id.timeText3);
        timeText4 = (TextView) findViewById(R.id.timeText4);
        timeText5 = (TextView) findViewById(R.id.timeText5);

        flight1 = (RelativeLayout) findViewById(R.id.flight1);
        flight2 = (RelativeLayout) findViewById(R.id.flight2);
        flight3 = (RelativeLayout) findViewById(R.id.flight3);
        flight4 = (RelativeLayout) findViewById(R.id.flight4);
        flight5 = (RelativeLayout) findViewById(R.id.flight5);

        flight_name1 = (TextView) findViewById(R.id.flightNameText1);
        flight_name2 = (TextView) findViewById(R.id.flight_name2);
        flight_name3 = (TextView) findViewById(R.id.flight_name3);
        flight_name4 = (TextView) findViewById(R.id.flight_name4);
        flight_name5 = (TextView) findViewById(R.id.flight_name5);

        price1 = (TextView) findViewById(R.id.price1);
        price2 = (TextView) findViewById(R.id.price2);
        price3 = (TextView) findViewById(R.id.price3);
        price4 = (TextView) findViewById(R.id.price4);
        price5 = (TextView) findViewById(R.id.price5);

        price1.setText("₹ " + p1);
        price2.setText("₹ " + p2);
        price3.setText("₹ " + p3);
        price4.setText("₹ " + p4);
        price5.setText("₹ " + p5);

        fromText1 = (TextView) findViewById(R.id.fromText);
        fromText21 = (TextView) findViewById(R.id.fromText21);
        fromText31 = (TextView) findViewById(R.id.fromText31);
        fromText41 = (TextView) findViewById(R.id.fromText41);
        fromText51 = (TextView) findViewById(R.id.fromText51);
        fromText1.setText(From);
        fromText21.setText(From);
        fromText31.setText(From);
        fromText41.setText(From);
        fromText51.setText(From);

        toText1 = (TextView) findViewById(R.id.toText);
        toText21 = (TextView) findViewById(R.id.toText21);
        toText31 = (TextView) findViewById(R.id.toText31);
        toText41 = (TextView) findViewById(R.id.toText41);
        toText51 = (TextView) findViewById(R.id.toText51);
        toText1.setText(To);
        toText21.setText(To);
        toText31.setText(To);
        toText41.setText(To);
        toText51.setText(To);

        backbtn = (Button)findViewById(R.id.backBtn);
        back();

        flight();
    }

    public void back(){
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void flight(){
        flight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Seat.class);
                i.putExtra("flightName", flight_name1.getText().toString());
                i.putExtra("From", From);
                i.putExtra("To", To);
                i.putExtra("Date", Date);
                i.putExtra("CClass", CClass);
                i.putExtra("Price", p1);
                i.putExtra("timeText", timeText1.getText().toString());
                startActivity(i);
            }
        });

        flight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Seat.class);
                i.putExtra("flightName", flight_name2.getText().toString());
                i.putExtra("From", From);
                i.putExtra("To", To);
                i.putExtra("Date", Date);
                i.putExtra("CClass", CClass);
                i.putExtra("Price", p2);
                i.putExtra("timeText", timeText2.getText().toString());
                startActivity(i);
            }
        });

        flight3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Seat.class);
                i.putExtra("flightName", flight_name3.getText().toString());
                i.putExtra("From", From);
                i.putExtra("To", To);
                i.putExtra("Date", Date);
                i.putExtra("CClass", CClass);
                i.putExtra("Price", p3);
                i.putExtra("timeText", timeText3.getText().toString());
                startActivity(i);
            }
        });

        flight4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Seat.class);
                i.putExtra("flightName", flight_name4.getText().toString());
                i.putExtra("From", From);
                i.putExtra("To", To);
                i.putExtra("Date", Date);
                i.putExtra("CClass", CClass);
                i.putExtra("Price", p4);
                i.putExtra("timeText", timeText4.getText().toString());
                startActivity(i);
            }
        });

        flight5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Seat.class);
                i.putExtra("flightName", flight_name5.getText().toString());
                i.putExtra("From", From);
                i.putExtra("To", To);
                i.putExtra("Date", Date);
                i.putExtra("CClass", CClass);
                i.putExtra("Price", p5);
                i.putExtra("timeText", timeText5.getText().toString());
                startActivity(i);
            }
        });
    }
}
