package com.example.bakery_nitte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class admin_cake extends AppCompatActivity {
EditText name,price;
Button ins,delete;
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cake);
        Intent i1=getIntent();
        name=findViewById(R.id.name);
        price=findViewById(R.id.price);
        ins=findViewById(R.id.ins);
        delete=findViewById(R.id.delete);
        db=openOrCreateDatabase("Bakery",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE if not exists Cake(name varchar(20) primary key,prince int)");

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                int p=Integer.parseInt(price.getText().toString());
                db.execSQL("insert into Cake values('"+n+"',"+p+")");
                Toast.makeText(admin_cake.this, "Insert successful!", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                db.execSQL("delete from Cake where name='"+n+"'");
                Toast.makeText(admin_cake.this, "Delete successful!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}