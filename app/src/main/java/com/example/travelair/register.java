package com.example.travelair;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class register extends AppCompatActivity {

    int RC_SIGN_IN = 0;

    Button registerBtn, googleBtn2;
    EditText editName,editPass,editAge,editEmail,editPhone;
    DBHelper myDb;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.appTheme));
        }

        myDb = new DBHelper(this);
        registerBtn = (Button) findViewById(R.id.registerBtn2);
        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone2);
        editAge = (EditText) findViewById(R.id.editAge2);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPass = (EditText) findViewById(R.id.editPass);

        googleBtn2 = (Button)findViewById(R.id.googleLoginBtn);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        googleBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

        register();

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
            if(myDb.check(account.getId()) == true){
                Toast.makeText(this, "Account already exists!", Toast.LENGTH_SHORT).show();
                mGoogleSignInClient.signOut();
            }
            else{

                Intent i = new Intent(getApplicationContext(), extraRegister.class);
                i.putExtra("Name", account.getDisplayName());
                i.putExtra("Email", account.getEmail());
                i.putExtra("Id", account.getId());

                startActivity(i);
                finish();

            }
        } catch (ApiException e){
            Log.w("Google Sign In Error", "signInResult:failed code" + e.getStatusCode());
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart(){
        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            Intent i = new Intent(getApplicationContext(), extraRegister.class);
            i.putExtra("Name", account.getDisplayName());
            i.putExtra("Email", account.getEmail());
            i.putExtra("Id", account.getId());

            startActivity(i);
            finish();
        }
        super.onStart();
    }

    public void register(){
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String age = editAge.getText().toString();
                String email = editEmail.getText().toString();
                String pass = editPass.getText().toString();
                String phone = editPhone.getText().toString();

                if(name.isEmpty() != true || age.isEmpty() != true || email.isEmpty() != true || pass.isEmpty() != true || phone.isEmpty() != true) {
                    boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(), editEmail.getText().toString(), editPass.getText().toString(), editPhone.getText().toString());
                    if (isInserted == true) {
                        //Toast.makeText(getApplicationContext(),"DATA INSERTED",Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(), login.class);
                        startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "DATA NOT INSERTED", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(register.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
