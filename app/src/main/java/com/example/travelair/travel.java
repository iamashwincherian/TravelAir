package com.example.travelair;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.Calendar;

public class travel extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RelativeLayout relAcc;
    Button searchBtn,myAccBtn, myTripsBtn;
    String From,To,Date,CClass;
    private  TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Spinner spinnerFrom, spinnerTo, spinnerClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        runFadeInAnimation();

        relAcc = (RelativeLayout) findViewById(R.id.relAcc);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        myAccBtn = (Button) findViewById(R.id.myAccBtn);
        myTripsBtn = (Button) findViewById(R.id.myTripsBtn);
        searching();
        myAccount();
        myTrips();

      //
     // SPINNER FROM
    //

        spinnerFrom = findViewById(R.id.spinnerFrom);
        ArrayAdapter<CharSequence> adapterFrom = ArrayAdapter.createFromResource(this, R.array.places, R.layout.spinner_from_layout);
        adapterFrom.setDropDownViewResource(R.layout.spinner_from_items);
        spinnerFrom.setAdapter(adapterFrom);
        spinnerFrom.setOnItemSelectedListener(this);

      //
     // SPINNER TO
    //

        spinnerTo = findViewById(R.id.spinnerTo);
        ArrayAdapter<CharSequence> adapterTo = ArrayAdapter.createFromResource(this, R.array.places, R.layout.spinner_to_layout);
        adapterTo.setDropDownViewResource(R.layout.spinner_to_items);
        spinnerTo.setAdapter(adapterTo);
        spinnerTo.setOnItemSelectedListener(this);
        spinnerTo.setSelection(adapterTo.getPosition("BOM"));

      //
     // SPINNER CLASS
    //

        spinnerClass = findViewById(R.id.spinnerClass);
        ArrayAdapter<CharSequence> adapterClass = ArrayAdapter.createFromResource(this, R.array.Class, R.layout.spinner_class_layout);
        adapterClass.setDropDownViewResource(R.layout.spinner_class_items);
        spinnerClass.setAdapter(adapterClass);
        spinnerClass.setOnItemSelectedListener(this);
        spinnerClass.setSelection(adapterClass.getPosition("Economy"));

      //
     // DATE
    //

        mDisplayDate = (TextView) findViewById(R.id.textView10);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(travel.this, android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Date = dayOfMonth + "/" + month + "/" + year;
                mDisplayDate.setText(Date);
            }
        };



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void searching(){
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                From = spinnerFrom.getSelectedItem().toString();
                To = spinnerTo.getSelectedItem().toString();
                CClass = spinnerClass.getSelectedItem().toString();
                
                if(To != From) {
                    Intent i = new Intent(getApplicationContext(), search.class);
                    i.putExtra("To", To);
                    i.putExtra("From", From);
                    i.putExtra("Date", Date);
                    i.putExtra("CClass", CClass);
                    startActivity(i);
                }
                else{
                    Snackbar.make(v, "Please select a valid destination", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }

    public void myAccount(){
        myAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), myAccount.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void myTrips(){
        myTripsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), myTrips.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void runFadeInAnimation() {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.fadein);
        a.reset();
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl2);
        rl.clearAnimation();
        rl.startAnimation(a);
    }
}
