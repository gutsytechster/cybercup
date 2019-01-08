package com.example.cypher.projinselo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cypher.projinselo.model.DB_connection;

public class MainActivity extends AppCompatActivity {

    public AutoCompleteTextView user_med;
    public Button search;
    public static String med_name;
    public ArrayAdapter<String> adapter;
    public String[] name={"Dolo 650","Topp","Pandol","Bupap","Tencon","Orbivan CF","Allzital","Marten-tab","Advil",
            "Filoricet with Codeine","Aleve","Anaprox","Anolor","Nupenta","Thermal 650","Pirox DT","Auxipar 650"
            ,"Doloswift DT","Rox DT","Oxicam DT","Gravol","Draminate","Advent 625","Clavford 625","Mpx-CV 625",
            "Fioricet","Replax","Imitrex","Treximet","Mucinex","Benadryl Syrup","Cof-ryl Syrup","Franklor Expectorant",
            "Benz Pearls","Benz 100mg","Corex Syrup","Coffwin 4mg/10mg Syrup","ASCODEX C 4mg/10 mg Syrup",
            "BRONOLAX 4mg/10mg Syrup","Zofran","Ondisolv","Phenergan","Zofran ODT"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_med=(AutoCompleteTextView)findViewById(R.id.user_med);
        search=findViewById(R.id.search);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name);
        user_med.setAdapter(adapter);

        user_med.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)view;
                Toast.makeText(MainActivity.this,tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    public void cou (View view)
    {
        Intent intent = new Intent(this,cough.class);

        startActivity(intent);
    }


    public void mig (View view)
    {
        Intent intent = new Intent(this,migraine.class);

        startActivity(intent);
    }

    public void fev (View view)
    {
        Intent intent = new Intent(this,fever.class);

        startActivity(intent);
    }


    public void vom (View view)
    {
        Intent intent = new Intent(this,vomiting.class);

        startActivity(intent);
    }


    public void faqActivity (View view)
    {
        Intent intent = new Intent(this,faqActivity.class);

        startActivity(intent);
    }


    public void onSearch (View view)
    {
        med_name = user_med.getText().toString();
        DB_connection myconn = new DB_connection();
        myconn.execute(med_name);

        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }


}

