package com.example.lithamguzuli.jzcourier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeActivity extends AppCompatActivity {
    private ImageButton bopp;
    private ImageButton bfleet;
    private ImageButton bvmm;
    private ImageButton bsa;
    private ImageButton bao;
    private ImageButton bceo;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainstart);
        //String Users=getIntent().getExtras().getParcelable("Users");
        Toast.makeText(this,"hey",Toast.LENGTH_LONG).show();
        //textView.append(Users);
        bopp=(ImageButton)findViewById(R.id.OppOff);
        bopp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EmployeeActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        bfleet=(ImageButton)findViewById(R.id.FleAssMan);
        bfleet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EmployeeActivity.this,Maps2Activity.class);
                startActivity(intent);
            }
        });
        bvmm=(ImageButton)findViewById(R.id.VihManMan);
        bvmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EmployeeActivity.this,AboutVMM.class);
                startActivity(intent);
            }
        });
        bsa=(ImageButton)findViewById(R.id.SysAdd);
        bsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EmployeeActivity.this,AboutSA.class);
                startActivity(intent);
            }
        });
        bao=(ImageButton)findViewById(R.id.AccOff);
        bao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EmployeeActivity.this,AboutAO.class);
                startActivity(intent);
            }
        });
        bceo=(ImageButton)findViewById(R.id.TheCeo);
        bceo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EmployeeActivity.this,AboutCEO.class);
                startActivity(intent);
            }
        });
    }
}
