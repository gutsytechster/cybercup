package com.example.cypher.projinselo;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cypher.projinselo.model.DB_connection;
import com.example.cypher.projinselo.model.use_pres;

public class Activity2 extends AppCompatActivity {

    public static TextView med_details;
    public static TextView salt_details;
    public static AutoCompleteTextView state;
    public static String state_name;
    public static String med_name;
    public String med_code;
    public static String salt;
    ArrayAdapter<String> adapter;
    public String[]
            stat_nm={"Delhi","Mumbai","Kolkata","Gujrat","Chennai"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        med_details=findViewById(R.id.editText);
        salt_details=findViewById(R.id.salt_detail);
        state = (AutoCompleteTextView)findViewById(R.id.stateName);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stat_nm);
        state.setAdapter(adapter);

        state.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv=(TextView)view;
                Toast.makeText(Activity2.this,tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onlocate (View view)
    {
        state_name=state.getText().toString();
        med_name=med_details.getText().toString();
        salt=salt_details.getText().toString();

        alter_database alterDatabase = new alter_database(med_name,state_name,salt);

        Intent intent = new Intent(this,Activity3.class);
        startActivity(intent);
    }

    public void map (View view)
    {
        Intent intent = new Intent(this,MapsActivity.class);

        startActivity(intent);
    }

    public void info (View view)
    {
        DB_connection db=new DB_connection();
        med_code=db.getMedCode(med_name);

        use_pres up=new use_pres(med_code);
        up.execute();

        Intent intent = new Intent(this,ScrollingActivity.class);
        startActivity(intent);
    }


}
