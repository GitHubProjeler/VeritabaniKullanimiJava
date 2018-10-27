package com.example.fatih.veritabanikullanimijava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText name, surname, age;
    Button insert, show;
    RequestQueue requestQueue;
    String insertUrl = "http://ios-android.trkaynak.com/insert.php";
    public static TextView data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= (EditText) findViewById(R.id.nameText);
        surname= (EditText) findViewById(R.id.surnameText);
        age= (EditText) findViewById(R.id.ageText);
        insert = (Button) findViewById(R.id.insert);
        show = (Button) findViewById(R.id.show);
        data = (TextView) findViewById(R.id.fechteddata);

        requestQueue = Volley.newRequestQueue(this);



/////////gösterme butonu
show.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        fetchData process = new fetchData();
        process.execute();
    }
});


////////ekleme butonu
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"Hata :"+response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters  = new HashMap<String, String>();
                        parameters.put("name",name.getText().toString());
                        parameters.put("surname",surname.getText().toString());
                        parameters.put("age",age.getText().toString());

                        return parameters;

                    }
                };
                requestQueue.add(request);

                if (!equals(request)){
                    Toast.makeText(getApplicationContext(),"Eklendi",Toast.LENGTH_LONG).show();
                    name.getText().clear();
                    surname.getText().clear();
                    age.getText().clear();
                }

            }

        });


    }

    }
