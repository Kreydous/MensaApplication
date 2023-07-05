package com.mensaapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mensaapplication.Adapters.MensaAdapter;
import com.mensaapplication.Adapters.PlatesAdapter;
import com.mensaapplication.Models.Food;
import com.mensaapplication.Models.Mensa;
import com.mensaapplication.ui.theme.RecyclerViewInterface;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity2 extends AppCompatActivity implements RecyclerViewInterface {

    List<Food> plates;
    TextView mensaNametxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        mensaNametxt = findViewById(R.id.mensaName);
        String mensaName = getIntent().getStringExtra("mensaName");
        System.out.println(mensaName);
        mensaNametxt.setText(mensaName);
        String url = "https://mensaappserver.onrender.com/platesFromMensa";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            Food[] foods = new Gson().fromJson(response, Food[].class);
                            plates = Arrays.asList(foods);
                            recyclerViewPlate();
                        } catch (Exception e) {
                            System.out.println("G");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("H");
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("mensaName",mensaName);
                return params;
            }
        };
        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        Volley.newRequestQueue(this).add(request);
    }

    private void recyclerViewPlate(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPlates);

        //setUpMensaModels();

        PlatesAdapter adapter = new PlatesAdapter(plates);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Button profile = findViewById(R.id.btnMensa);
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                changeActivity();
//            }
//        });

        PlatesAdapter.OnButtonClickListener buttonClickListener = new PlatesAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(int position) {
                // Azioni da eseguire quando viene cliccato un pulsante nella RecyclerView
                // Ad esempio, avvia un'altra Activity passando dati
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);

                RecyclerView recyclerView = findViewById(R.id.recyclerViewPlates);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity2.this, recyclerView, ViewCompat.getTransitionName(recyclerView));

                intent.putExtra("position", position);
                intent.putExtra("plateId",plates.get(position).getId());
                //profile.getText();
                startActivity(intent, options.toBundle());
            }
        };

        adapter.setOnButtonClickListener(buttonClickListener);
    }

    private void changeActivity(){
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);

        startActivity(intent);
    }
}