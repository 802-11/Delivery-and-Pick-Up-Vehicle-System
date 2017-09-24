package com.example.lithamguzuli.jzcourier;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.permission.READ_CONTACTS;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText namefields,numberfields,addressfileds;
    private EditText emailfields;
    private EditText pswdfields;
    private Button registerButtons;
    private FirebaseAuth mAuth;
    private ProgressDialog mprogress;
    FirebaseDatabase database;


    private FirebaseAuth.AuthStateListener mAuthListener;
    String TAG = "register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        mprogress=new ProgressDialog(this);

        namefields=(EditText)findViewById(R.id.nameFieldR);
        emailfields=(EditText)findViewById(R.id.emailFieldR);
        pswdfields=(EditText)findViewById(R.id.pswdFieldR);
        numberfields=(EditText)findViewById(R.id.numberFieldR);
        registerButtons=(Button)findViewById(R.id.RegisterButtonR);
        addressfileds=(EditText)findViewById(R.id.addressFieldR);
       // mDatabase=FirebaseDatabase.getInstance().getReference("Users");

        registerButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();
            }
        });



    }



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void startRegister(){
        String pswd,address,email,concact_number;
         final String name=namefields.getText().toString().trim();
        pswd=pswdfields.getText().toString().trim();
        address=addressfileds.getText().toString().trim();
        email=emailfields.getText().toString().trim();
        concact_number=numberfields.getText().toString().trim();
        //public Users( String name, String email, String address, String numbers, String pswd)
        final Users user=new Users(name,email,address,concact_number);
        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pswd)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(address)&&!TextUtils.isEmpty(concact_number)){

            //mprogress.setMessage("signing up.....");
            //mprogress.show();
            // Write a message to the database
            mAuth.createUserWithEmailAndPassword(email, pswd)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "failed",
                                        Toast.LENGTH_SHORT).show();
                            }else{

                                Toast.makeText(RegisterActivity.this, "successufly registerd.Welcome"+name,Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(RegisterActivity.this,HomeActivity.class);
                                // Write a message to the database

                                //DatabaseReference myRef = database.getReference("Users");
                                DatabaseReference node = FirebaseDatabase.getInstance().getReference().child("Users").push();
                                node.setValue(user);
                                startActivity(intent);















                            }

                            // ...
                        }
                    });

            Toast.makeText(this,"nazo ke",Toast.LENGTH_LONG).show();
            //.dismiss();
            //Users users=new Users();



        }
        else
        {
            Toast.makeText(this,"field is empty",Toast.LENGTH_LONG).show();
            //TODO
            //Specific cases for each parameter
        }
















    }
}
