package com.example.travelair;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.wajahatkarim3.easyflipview.EasyFlipView;

public class myAccount extends AppCompatActivity {

    RelativeLayout proCard;
    Button homeBtn, logoutBtn, editBtn,myTripsBtn;
    TextView nameText, ageText, phoneText, emailText;
    ImageView proPic;
    EasyFlipView myEasyFlipView;

    GoogleSignInClient mGoogleSignInClient;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        runFadeInAnimation();

        db = new DBHelper(this);

        proCard = (RelativeLayout) findViewById(R.id.proCard);
        myTripsBtn = (Button) findViewById(R.id.myTripsBtn);
        homeBtn = (Button) findViewById(R.id.homeBtn);
        logoutBtn = (Button) findViewById(R.id.logoutBtn);
        editBtn = (Button) findViewById(R.id.editBtn);
        nameText = (TextView) findViewById(R.id.nameText);
        ageText = (TextView) findViewById(R.id.ageText);
        phoneText = (TextView) findViewById(R.id.phoneText);
        emailText = (TextView) findViewById(R.id.emailText);

        proPic = (ImageView) findViewById(R.id.proPic);
        myEasyFlipView = (EasyFlipView) findViewById(R.id.myEasyFlipView);


        //setUserData();

        home();
        myTripsIntent();
        logout();
        setUserData();

        // GOOGLE SIGN IN

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            Glide.with(this).load(personPhoto).apply(RequestOptions.circleCropTransform()).into(proPic);
            removeEditProfile(acct.getId());
            //nameText.setText(personName);
            //emailText.setText(personEmail);
        }


    }

    public void setUserData(){
        nameText.setText(SaveSharedPreference.getName(getApplicationContext()));
        ageText.setText("Age : " + SaveSharedPreference.getAge(getApplicationContext()));
        phoneText.setText(SaveSharedPreference.getPhone(getApplicationContext()));
        emailText.setText(SaveSharedPreference.getEmail(getApplicationContext()));
    }

    public void home(){
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), travel.class);
                startActivity(i);
                finish();
            }
        });
    }

    public boolean removeEditProfile(String id){
        boolean isInserted = db.check(id);
        if(isInserted){
           editBtn.setVisibility(View.GONE);
        }
        return true;
    }

    public void myTripsIntent(){
        myTripsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), myTrips.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void logout(){
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut();
                SaveSharedPreference.logout(getApplicationContext());
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();
            }
        });
    }

    public void edit(){
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myEasyFlipView.flipTheView();
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
