package com.example.travelair;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class login extends AppCompatActivity {

    int RC_SIGN_IN = 0;

    Button loginBtn, testBtn, googleBtn;
    EditText editUsername, editPassword;
    DBHelper db;
    TextView regBtn;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        regBtn = (TextView) findViewById(R.id.regBtn);
        testBtn = (Button) findViewById(R.id.testBtn);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        db = new DBHelper(this);

        login();
        register();
        test();

        googleBtn = (Button)findViewById(R.id.googleLoginBtn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

    }

    private void googleSignIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if(db.check(account.getId()) == false){
                Toast.makeText(this, "Account doesn't exists!", Toast.LENGTH_SHORT).show();
                mGoogleSignInClient.signOut();
            }
            else {
                String email = account.getEmail();
                Cursor res = db.setUserData(email);

                while ((res.moveToNext())) {
                    SaveSharedPreference.set(getApplicationContext(), res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(0));
                }

                startActivity(new Intent(getApplicationContext(), travel.class));
                finish();
            }
        } catch (ApiException e){
            Log.w("Google Sign In Error", "signInResult:failed code" + e.getStatusCode());
            Toast.makeText(this, "Failed" + e.getStatusCode(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart(){
        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            startActivity(new Intent(getApplicationContext(), travel.class));
        }
        super.onStart();
    }

    public void login(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editUsername.getText().toString();
                String pass = editPassword.getText().toString();

                boolean checked= db.auth(email,pass);

                if(checked==true)
                {
                    Cursor res = db.setUserData(email);

                    while ((res.moveToNext())) {
                        SaveSharedPreference.set(getApplicationContext(), res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(0));
                    }

                    Intent i=new Intent(getApplicationContext(),travel.class);
                    startActivity(i);
                    finish();

                    Toast.makeText(login.this, "Logged In", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(login.this, "Log In Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void register(){
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), register.class);
                startActivity(i);
            }
        });
    }

    public void test(){
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), test.class);
                startActivity(i);
            }
        });
    }
}
