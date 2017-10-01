package com.example.lithamguzuli.jzcourier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private Button bf;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //String Users=getIntent().getExtras().getParcelable("Users");
        Toast.makeText(this,"hey",Toast.LENGTH_LONG).show();
        //textView.append(Users);
        bf=(Button)findViewById(R.id.creditbutton);
        bf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,ConfirmationActivity.class);
                startActivity(intent);
            }
        });
    }
}
