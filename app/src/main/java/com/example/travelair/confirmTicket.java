package com.example.travelair;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class confirmTicket extends AppCompatActivity {

    String flightName, From, To, date, CClass, Price, timeText;
    int noOfSeats;
    String seat1,seat2,seat3,seat4;
    TextView flightNameText, fromText, toText, seatNoText1, seatNoText2, seatNoText3, seatNoText4, dateText, cclassText, priceText, totalPriceText;
    Button confirmBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_ticket);

        db = new DBHelper(this);
        confirmBtn = (Button) findViewById(R.id.confirmBtn);

        flightName = getIntent().getStringExtra("flightName");
        From = getIntent().getStringExtra("From");
        To = getIntent().getStringExtra("To");
        date = getIntent().getStringExtra("Date");
        CClass = getIntent().getStringExtra("CClass");
        Price = getIntent().getStringExtra("Price");
        seat1 = getIntent().getStringExtra("seat1");
        seat2 = getIntent().getStringExtra("seat2");
        seat3 = getIntent().getStringExtra("seat3");
        seat4 = getIntent().getStringExtra("seat4");
        timeText = getIntent().getStringExtra("timeText");

        flightNameText = (TextView)findViewById(R.id.flightNameText1);
        fromText = (TextView)findViewById(R.id.fromText);
        toText = (TextView)findViewById(R.id.toText);
        seatNoText1 = (TextView)findViewById(R.id.seatNoText1);
        seatNoText2 = (TextView)findViewById(R.id.seatNoText2);
        seatNoText3 = (TextView)findViewById(R.id.seatNoText3);
        seatNoText4 = (TextView)findViewById(R.id.seatNoText4);
        dateText = (TextView)findViewById(R.id.dateText);
        cclassText = (TextView)findViewById(R.id.cclassText);
        priceText = (TextView)findViewById(R.id.priceText);
        totalPriceText = (TextView)findViewById(R.id.totalPriceText);
        flightNameText.setText(flightName);
        fromText.setText(From);
        toText.setText(To);
        if(date == null)
            dateText.setText(new SimpleDateFormat("d/M/yyyy", Locale.getDefault()).format(new Date()));
        else{
            dateText.setText(date);
        }
        cclassText.setText(CClass);
        seatNoText1.setText(seat1);
        seatNoText2.setText(seat2);
        seatNoText3.setText(seat3);
        seatNoText4.setText(seat4);
        int priceInt = Integer.parseInt(Price);
        noOfSeats = findNoOfSeats();
        priceText.setText("₹ " + Price + " x " + noOfSeats);
        int totalPrice = priceInt * noOfSeats;
        totalPrice += 50;
        totalPriceText.setText("₹ " + totalPrice);



        confirm();
    }

    public void confirm(){
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userID = SaveSharedPreference.getId(getApplicationContext());
                //Toast.makeText(confirmTicket.this, id, Toast.LENGTH_SHORT).show();
                Boolean isInserted = db.insertTrips(userID,flightName, From, To, "", timeText, seat1, seat2, seat3, seat4, CClass);
                if(isInserted) {
                    Snackbar.make(v, "Ticket Booked", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    startActivity(new Intent(getApplicationContext(), travel.class));
                }
                else{
                    Toast.makeText(confirmTicket.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public int findNoOfSeats(){
        int n=0;
        if(seat1 != null) n++;
        if(seat2 != null) n++;
        if(seat3 != null) n++;
        if(seat4 != null) n++;
        return n;
    }
}