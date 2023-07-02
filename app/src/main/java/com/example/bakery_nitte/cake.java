package com.example.bakery_nitte;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class cake extends AppCompatActivity {
    SQLiteDatabase db;
    Button b;
    String y="";
    ArrayList<String> arr;
    ArrayAdapter<String> adp;
    ListView lv;
    int sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake);
        Intent i1=getIntent();
        lv=findViewById(R.id.lv);
        db=openOrCreateDatabase("Bakery",MODE_PRIVATE,null);
        b=findViewById(R.id.b1);
        arr=new ArrayList<>();
        adp=new ArrayAdapter<String>(cake.this, android.R.layout.simple_list_item_1,arr);
        lv.setAdapter(adp);
       // db.execSQL("CREATE TABLE if not exists Cake(name varchar(20) primary key,prince int)");

                String sb="";
                @SuppressLint("Recycle") Cursor c = db.rawQuery("select * from Cake",null);
                if(c.moveToFirst())
                {
                    do {

                        int column=c.getColumnCount();
                        for(int idx=0;idx<column;idx++)
                        {
                            sb=sb+c.getString(idx);
                            if(idx<column-1)
                            {
                                sb=sb+" : ";
                            }

                        }
                        arr.add(sb);
                        adp.notifyDataSetChanged();
                        sb="";
                    }while(c.moveToNext());
                }
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int index=i;

                AlertDialog.Builder ad=new AlertDialog.Builder(cake.this);
                ad.setTitle("SELECT");
                ad.setMessage("Do you want to add the cake?");
                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String s=arr.get(index);
                        Toast.makeText(cake.this, ""+s, Toast.LENGTH_SHORT).show();
//                        arr.remove(index);
                        int x=Integer.parseInt(s.substring(Math.max(s.length()-3,0)));
                        sum=sum+x;
                        y=y+s+"\n";
                        adp.notifyDataSetChanged();
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog a=ad.create();
//                a.show();
                return false;
            }
        });
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder ad=new AlertDialog.Builder(cake.this);
                        ad.setTitle("Your bill");
                        ad.setMessage("Items selected : \n\n"+y+"\n"+"Total cost : "+sum);
                        ad.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog a=ad.create();
//                        a.show();
                    }
                });
            }

}