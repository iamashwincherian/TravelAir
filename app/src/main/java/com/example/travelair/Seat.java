package com.example.travelair;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Seat extends AppCompatActivity implements View.OnClickListener {

    int noOfSeats = 1,selectedNoOfSeats = 0;
    TextView seatNoText, flightNameText,noOfSeatsText;
    Button seat[][] = new Button[8][6];
    Button backBtn,selectBtn,plusBtn,minusBtn;
    String seatNo = null, flightName, From, To, Date, CClass, Price, timeText;
    String selectedSeats[] = new String[4];
    Boolean allSeats[][] = new Boolean[8][6];
    RelativeLayout bottomNavRel,darkRel;

    Animation down_to_up,up_to_down,fadeout,fadein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        flightName = getIntent().getStringExtra("flightName");
        From = getIntent().getStringExtra("From");
        To = getIntent().getStringExtra("To");
        Date = getIntent().getStringExtra("Date");
        CClass = getIntent().getStringExtra("CClass");
        Price = getIntent().getStringExtra("Price");
        timeText = getIntent().getStringExtra("timeText");

        flightNameText = (TextView)findViewById(R.id.flightNameText1);
        flightNameText.setText(flightName);


        noOfSeatsText = (TextView)findViewById(R.id.noOfSeatsText);
        seatNoText = (TextView)findViewById(R.id.seatNoText1);
        backBtn = (Button) findViewById(R.id.backBtn);
        selectBtn = (Button) findViewById(R.id.selectBtn);
        bottomNavRel = (RelativeLayout) findViewById(R.id.bottomNavRel);
        darkRel = (RelativeLayout) findViewById(R.id.darkRel);
        plusBtn = (Button) findViewById(R.id.plusBtn);
        minusBtn = (Button) findViewById(R.id.minusBtn);

        down_to_up = AnimationUtils.loadAnimation(this,R.anim.down_to_up);
        up_to_down = AnimationUtils.loadAnimation(this,R.anim.up_to_down);
        fadeout = AnimationUtils.loadAnimation(this,R.anim.fadeout);
        fadein = AnimationUtils.loadAnimation(this,R.anim.fadein);

        bottomNavRel.setAnimation(down_to_up);
        darkRel.startAnimation(fadein);

        back();
        allSeatsEmpty();
        select();
        plus();
        minus();
    }

    public void back(){
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seatNo != null) {
                    Intent i = new Intent(getApplicationContext(), confirmTicket.class);
                    i.putExtra("flightName", flightName);
                    i.putExtra("From", From);
                    i.putExtra("To", To);
                    i.putExtra("Date", Date);
                    i.putExtra("CClass", CClass);
                    i.putExtra("seatNo", seatNo);
                    i.putExtra("Price", Price);
                    i.putExtra("seat1", selectedSeats[0]);
                    i.putExtra("seat2", selectedSeats[1]);
                    i.putExtra("seat3", selectedSeats[2]);
                    i.putExtra("seat4", selectedSeats[3]);
                    i.putExtra("timeText", timeText);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Seat.this, "Please select a seat to continue", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void select(){
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavRel.startAnimation(up_to_down);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        backBtn.setVisibility(View.VISIBLE);
                    }
                },700);
                darkRel.startAnimation(fadeout);
                backBtn.startAnimation(fadein);
                seatNoText.setText("Seats Remaining : " + noOfSeats);
                InitializingSeats();
            }
        });
    }

    public void plus(){
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noOfSeats != 4)
                noOfSeats++;
                noOfSeatsText.setText(""+ noOfSeats);
            }
        });
    }

    public void minus(){
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noOfSeats != 1)
                    noOfSeats--;
                noOfSeatsText.setText("" + noOfSeats);
            }
        });
    }


    ///////////////////////////////////////////////////////////////////////////////////////////


    public void InitializingSeats(){

        seat[0][0] = (Button) findViewById(R.id.seatA1);
        seat[0][1] = (Button) findViewById(R.id.seatA2);
        seat[0][2] = (Button) findViewById(R.id.seatA3);
        seat[0][3] = (Button) findViewById(R.id.seatA4);
        seat[0][4] = (Button) findViewById(R.id.seatA5);
        seat[0][5] = (Button) findViewById(R.id.seatA6);
        seat[1][0] = (Button) findViewById(R.id.seatB1);
        seat[1][1] = (Button) findViewById(R.id.seatB2);
        seat[1][2] = (Button) findViewById(R.id.seatB3);
        seat[1][3] = (Button) findViewById(R.id.seatB4);
        seat[1][4] = (Button) findViewById(R.id.seatB5);
        seat[1][5] = (Button) findViewById(R.id.seatB6);
        seat[2][0] = (Button) findViewById(R.id.seatC1);
        seat[2][1] = (Button) findViewById(R.id.seatC2);
        seat[2][2] = (Button) findViewById(R.id.seatC3);
        seat[2][3] = (Button) findViewById(R.id.seatC4);
        seat[2][4] = (Button) findViewById(R.id.seatC5);
        seat[2][5] = (Button) findViewById(R.id.seatC6);
        seat[3][0] = (Button) findViewById(R.id.seatD1);
        seat[3][1] = (Button) findViewById(R.id.seatD2);
        seat[3][2] = (Button) findViewById(R.id.seatD3);
        seat[3][3] = (Button) findViewById(R.id.seatD4);
        seat[3][4] = (Button) findViewById(R.id.seatD5);
        seat[3][5] = (Button) findViewById(R.id.seatD6);
        seat[4][0] = (Button) findViewById(R.id.seatE1);
        seat[4][1] = (Button) findViewById(R.id.seatE2);
        seat[4][2] = (Button) findViewById(R.id.seatE3);
        seat[4][3] = (Button) findViewById(R.id.seatE4);
        seat[4][4] = (Button) findViewById(R.id.seatE5);
        seat[4][5] = (Button) findViewById(R.id.seatE6);
        seat[5][0] = (Button) findViewById(R.id.seatF1);
        seat[5][1] = (Button) findViewById(R.id.seatF2);
        seat[5][2] = (Button) findViewById(R.id.seatF3);
        seat[5][3] = (Button) findViewById(R.id.seatF4);
        seat[5][4] = (Button) findViewById(R.id.seatF5);
        seat[5][5] = (Button) findViewById(R.id.seatF6);
        seat[6][0] = (Button) findViewById(R.id.seatG1);
        seat[6][1] = (Button) findViewById(R.id.seatG2);
        seat[6][2] = (Button) findViewById(R.id.seatG3);
        seat[6][3] = (Button) findViewById(R.id.seatG4);
        seat[6][4] = (Button) findViewById(R.id.seatG5);
        seat[6][5] = (Button) findViewById(R.id.seatG6);
        seat[7][0] = (Button) findViewById(R.id.seatH1);
        seat[7][1] = (Button) findViewById(R.id.seatH2);
        seat[7][2] = (Button) findViewById(R.id.seatH3);
        seat[7][3] = (Button) findViewById(R.id.seatH4);
        seat[7][4] = (Button) findViewById(R.id.seatH5);
        seat[7][5] = (Button) findViewById(R.id.seatH6);


            seat[0][0].setOnClickListener(this);
            seat[0][1].setOnClickListener(this);
            seat[0][2].setOnClickListener(this);
            seat[0][3].setOnClickListener(this);
            seat[0][4].setOnClickListener(this);
            seat[0][5].setOnClickListener(this);
            seat[1][0].setOnClickListener(this);
            seat[1][1].setOnClickListener(this);
            seat[1][2].setOnClickListener(this);
            seat[1][3].setOnClickListener(this);
            seat[1][4].setOnClickListener(this);
            seat[1][5].setOnClickListener(this);
            seat[2][0].setOnClickListener(this);
            seat[2][1].setOnClickListener(this);
            seat[2][2].setOnClickListener(this);
            seat[2][3].setOnClickListener(this);
            seat[2][4].setOnClickListener(this);
            seat[2][5].setOnClickListener(this);
            seat[3][0].setOnClickListener(this);
            seat[3][1].setOnClickListener(this);
            seat[3][2].setOnClickListener(this);
            seat[3][3].setOnClickListener(this);
            seat[3][4].setOnClickListener(this);
            seat[3][5].setOnClickListener(this);
            seat[4][0].setOnClickListener(this);
            seat[4][1].setOnClickListener(this);
            seat[4][2].setOnClickListener(this);
            seat[4][3].setOnClickListener(this);
            seat[4][4].setOnClickListener(this);
            seat[4][5].setOnClickListener(this);
            seat[5][0].setOnClickListener(this);
            seat[5][1].setOnClickListener(this);
            seat[5][2].setOnClickListener(this);
            seat[5][3].setOnClickListener(this);
            seat[5][4].setOnClickListener(this);
            seat[5][5].setOnClickListener(this);
            seat[6][0].setOnClickListener(this);
            seat[6][1].setOnClickListener(this);
            seat[6][2].setOnClickListener(this);
            seat[6][3].setOnClickListener(this);
            seat[6][4].setOnClickListener(this);
            seat[6][5].setOnClickListener(this);
            seat[7][0].setOnClickListener(this);
            seat[7][1].setOnClickListener(this);
            seat[7][2].setOnClickListener(this);
            seat[7][3].setOnClickListener(this);
            seat[7][4].setOnClickListener(this);
            seat[7][5].setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.seatA1:
                if (allSeats[0][0] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[0][0].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[0][0] = true;
                        seatNo = "A1";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[0][0].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("A1");
                    allSeats[0][0] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatA2:
                if (allSeats[0][1] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[0][1].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[0][1] = true;
                        seatNo = "A2";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[0][1].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("A2");
                    allSeats[0][1] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatA3:

                if (allSeats[0][2] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[0][2].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[0][2] = true;
                        seatNo = "A3";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[0][2].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("A3");
                    allSeats[0][2] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatA4:
                if (allSeats[0][3] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[0][3].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[0][3] = true;
                        seatNo = "A4";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[0][3].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("A4");
                    allSeats[0][3] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatA5:
                if (allSeats[0][4] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[0][4].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[0][4] = true;
                        seatNo = "A5";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[0][4].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("A5");
                    allSeats[0][4] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatA6:
                if (allSeats[0][5] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[0][5].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[0][5] = true;
                        seatNo = "A6";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[0][5].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("A6");
                    allSeats[0][5] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatB1:
                if (allSeats[1][0] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[1][0].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[1][0] = true;
                        seatNo = "B1";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[1][0].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("B1");
                    allSeats[1][0] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatB2:
                if (allSeats[1][1] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[1][1].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[1][1] = true;
                        seatNo = "B2";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[1][1].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("B2");
                    allSeats[1][1] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatB3:
                if (allSeats[1][2] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[1][2].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[1][2] = true;
                        seatNo = "B3";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[1][2].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("B3");
                    allSeats[1][2] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatB4:
                if (allSeats[1][3] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[1][3].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[1][3] = true;
                        seatNo = "B4";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[1][3].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("B4");
                    allSeats[1][3] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatB5:
                if (allSeats[1][4] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[1][4].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[1][4] = true;
                        seatNo = "B5";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[1][4].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("B5");
                    allSeats[1][4] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatB6:
                if (allSeats[1][5] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[1][5].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[1][5] = true;
                        seatNo = "B6";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[1][5].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("B6");
                    allSeats[1][5] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatC1:
                if (allSeats[2][0] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[2][0].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[2][0] = true;
                        seatNo = "C1";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[2][0].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("C1");
                    allSeats[2][0] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatC2:
                if (allSeats[2][1] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[2][1].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[2][1] = true;
                        seatNo = "C2";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[2][1].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("C2");
                    allSeats[2][1] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatC3:
                if (allSeats[2][2] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[2][2].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[2][2] = true;
                        seatNo = "C3";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[2][2].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("C3");
                    allSeats[2][2] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatC4:
                if (allSeats[2][3] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[2][3].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[2][3] = true;
                        seatNo = "C4";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[2][3].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("C4");
                    allSeats[2][3] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatC5:
                if (allSeats[2][4] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[2][4].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[2][4] = true;
                        seatNo = "C5";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[2][4].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("C5");
                    allSeats[2][4] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatC6:
                if (allSeats[2][5] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[2][5].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[2][5] = true;
                        seatNo = "C6";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[2][5].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("C6");
                    allSeats[2][5] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatD1:
                if (allSeats[3][0] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[3][0].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[3][0] = true;
                        seatNo = "D1";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[3][0].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("D1");
                    allSeats[3][0] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatD2:
                if (allSeats[3][1] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[3][1].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[3][1] = true;
                        seatNo = "D2";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[3][1].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("D2");
                    allSeats[3][1] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatD3:
                if (allSeats[3][2] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[3][2].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[3][2] = true;
                        seatNo = "D3";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[3][2].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("D3");
                    allSeats[3][2] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatD4:
                if (allSeats[3][3] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[3][3].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[3][3] = true;
                        seatNo = "D4";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[3][3].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("D4");
                    allSeats[3][3] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatD5:
                if (allSeats[3][4] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[3][4].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[3][4] = true;
                        seatNo = "D5";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[3][4].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("D5");
                    allSeats[3][4] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatD6:
                if (allSeats[3][5] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[3][5].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[3][5] = true;
                        seatNo = "D6";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[3][5].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("D6");
                    allSeats[3][5] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatE1:
                if (allSeats[4][0] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[4][0].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[4][0] = true;
                        seatNo = "E1";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[4][0].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("E1");
                    allSeats[4][0] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatE2:
                if (allSeats[4][1] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[4][1].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[4][1] = true;
                        seatNo = "E2";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[4][1].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("E2");
                    allSeats[4][1] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatE3:
                if (allSeats[4][2] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[4][2].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[4][2] = true;
                        seatNo = "E3";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[4][2].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("E3");
                    allSeats[4][2] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatE4:
                if (allSeats[4][3] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[4][3].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[4][3] = true;
                        seatNo = "E4";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[4][3].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("E4");
                    allSeats[4][3] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatE5:
                if (allSeats[4][4] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[4][4].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[4][4] = true;
                        seatNo = "E5";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[4][4].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("E5");
                    allSeats[4][4] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatE6:
                if (allSeats[4][5] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[4][5].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[4][5] = true;
                        seatNo = "E6";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[4][5].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("E6");
                    allSeats[4][5] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatF1:
                if (allSeats[5][0] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[5][0].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[5][0] = true;
                        seatNo = "F1";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[5][0].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("F1");
                    allSeats[5][0] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatF2:
                if (allSeats[5][1] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[5][1].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[5][1] = true;
                        seatNo = "F2";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[5][1].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("F2");
                    allSeats[5][1] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatF3:
                if (allSeats[5][2] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[5][2].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[5][2] = true;
                        seatNo = "F3";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[5][2].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("F3");
                    allSeats[5][2] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatF4:
                if (allSeats[5][3] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[5][3].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[5][3] = true;
                        seatNo = "F4";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[5][3].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("F4");
                    allSeats[5][3] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatF5:
                if (allSeats[5][4] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[5][4].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[5][4] = true;
                        seatNo = "F5";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[5][4].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("F5");
                    allSeats[5][4] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatF6:
                if (allSeats[5][5] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[5][5].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[5][5] = true;
                        seatNo = "F6";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[5][5].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("F6");
                    allSeats[5][5] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatG1:
                if (allSeats[6][0] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[6][0].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[6][0] = true;
                        seatNo = "G1";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[6][0].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("G1");
                    allSeats[6][0] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatG2:
                if (allSeats[6][1] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[6][1].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[6][1] = true;
                        seatNo = "G2";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[6][1].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("G2");
                    allSeats[6][1] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatG3:
                if (allSeats[6][2] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[6][2].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[6][2] = true;
                        seatNo = "G3";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[6][2].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("G3");
                    allSeats[6][2] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatG4:
                if (allSeats[6][3] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[6][3].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[6][3] = true;
                        seatNo = "G4";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[6][3].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("G4");
                    allSeats[6][3] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatG5:
                if (allSeats[6][4] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[6][4].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[6][4] = true;
                        seatNo = "G5";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[6][4].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("G5");
                    allSeats[6][4] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatG6:
                if (allSeats[6][5] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[6][5].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[6][5] = true;
                        seatNo = "G6";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[6][5].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("G6");
                    allSeats[6][5] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatH1:
                if (allSeats[7][0] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[7][0].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[7][0] = true;
                        seatNo = "H1";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[7][0].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("H1");
                    allSeats[7][0] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatH2:
                if (allSeats[7][1] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[7][1].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[7][1] = true;
                        seatNo = "H2";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[7][1].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("H2");
                    allSeats[7][1] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatH3:
                if (allSeats[7][2] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[7][2].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[7][2] = true;
                        seatNo = "H3";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[7][2].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("H3");
                    allSeats[7][2] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatH4:
                if (allSeats[7][3] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[7][3].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[7][3] = true;
                        seatNo = "H4";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[7][3].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("H4");
                    allSeats[7][3] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatH5:
                if (allSeats[7][4] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[7][4].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[7][4] = true;
                        seatNo = "H5";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[7][4].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("H5");
                    allSeats[7][4] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

            case R.id.seatH6:
                if (allSeats[7][5] == false) {
                    if(selectedNoOfSeats < noOfSeats) {
                        seat[7][5].setBackgroundResource(R.drawable.seat_filled);
                        allSeats[7][5] = true;
                        seatNo = "H6";
                        selectedSeats[selectedNoOfSeats] = seatNo;
                        selectedNoOfSeats++;
                        seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                    }
                }
                else{
                    seat[7][5].setBackgroundResource(R.drawable.seat);
                    selectedNoOfSeats--;
                    removeSeat("H6");
                    allSeats[7][5] = false;
                    seatNoText.setText("Seats Remaining : " + (noOfSeats - selectedNoOfSeats));
                }
                break;

        }
    }

    public void removeSeat(String seat){
        for(int i =0;i<4;i++){
            if(selectedSeats[i] == seat){
                selectedSeats[i] = selectedSeats[selectedNoOfSeats];
                selectedSeats[selectedNoOfSeats] = null;
            }

        }
    }

    public void clearSeat(){
        for(int i=0;i<8;i++){
            for(int j=0;j<6;j++){
                seat[i][j].setBackgroundResource(R.drawable.seat);
            }
        }
    }

    public void allSeatsEmpty(){
        for(int i=0;i<8;i++){
            for(int j=0;j<6;j++){
                allSeats[i][j] = false;
            }
        }
    }
}
