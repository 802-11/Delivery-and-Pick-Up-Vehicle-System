package com.example.lithamguzuli.jzcourier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AboutSA extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_about_s);
        //String Users=getIntent().getExtras().getParcelable("Users");
        Toast.makeText(this,"hey",Toast.LENGTH_LONG).show();
        //textView.append(Users);
    }
}
