package com.example.travelair;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class extraRegister extends AppCompatActivity {

    EditText editAge, editPhone;
    Button registerBtn2;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_register);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.appTheme));
        }

        db = new DBHelper(this);

        editAge = (EditText) findViewById(R.id.editAge2);
        editPhone = (EditText) findViewById(R.id.editPhone2);
        registerBtn2 = (Button) findViewById(R.id.registerBtn2);

        register();

    }

    public void register(){
        registerBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String age = editAge.getText().toString();
                String phone = editPhone.getText().toString();

                String name = getIntent().getStringExtra("Name");
                String email = getIntent().getStringExtra("Email");
                String id = getIntent().getStringExtra("Id");

                if(age.matches("") == false || phone.matches("") == false){
                    boolean isInserted = db.insertGoogleAcc(name,age,email,"",phone,id);
                    Toast.makeText(extraRegister.this, "age : " + age, Toast.LENGTH_SHORT).show();

                    if(isInserted){
                        SaveSharedPreference.set(getApplicationContext(), name, age, email, phone, id);
                        startActivity(new Intent(getApplicationContext(), travel.class));
                        finish();
                    }
                }
                else{
                    Snackbar.make(v, "Please fill the fields", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }



            }
        });
    }
}
