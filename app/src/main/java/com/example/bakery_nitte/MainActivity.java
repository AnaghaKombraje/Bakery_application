package com.example.bakery_nitte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText usn,admin;
    Button usnb,adminb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usn=findViewById(R.id.usn);
        admin=findViewById(R.id.admin);
        usnb=findViewById(R.id.usnb);
        adminb=findViewById(R.id.adminb);
        ArrayList<String> l=new ArrayList<>();
        l.add("4nm");
        l.add("4NM");
        l.add("19");
        l.add("20");
        l.add("21");
        usnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=usn.getText().toString();
                if(l.contains(s1.substring(0,3))&& l.contains(s1.substring(3,5))&&s1.length()==10&&  Integer.parseInt(s1.substring(7,10))>0 && Integer.parseInt(s1.substring(7,10))<240)
                {
                    Intent i=new Intent(MainActivity.this,cake.class);
                    startActivity(i);
                }
                else
                {

                    Toast.makeText(MainActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                }
            }
        });
        adminb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s2=admin.getText().toString();
                if(s2.equals("nitte"))
                {
                    Intent i2=new Intent(MainActivity.this,admin_cake.class);
                    startActivity(i2);
                }
                else
                    Toast.makeText(MainActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}