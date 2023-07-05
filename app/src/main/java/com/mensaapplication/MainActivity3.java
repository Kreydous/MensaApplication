package com.mensaapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.mensaapplication.Adapters.PlateInfoAdapter;
import com.mensaapplication.Models.Food;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity  implements View.OnClickListener {
    Food plate;
    ImageView star1,star2,star3,star4,star5;
    TextInputLayout txtComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);
        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        star4 = findViewById(R.id.star4);
        star5 = findViewById(R.id.star5);
        txtComment = findViewById(R.id.imageView35);
        star1.setOnClickListener(this);
        star2.setOnClickListener(this);
        star3.setOnClickListener(this);
        star4.setOnClickListener(this);
        star5.setOnClickListener(this);
        String plateId = getIntent().getExtras().get("plateId").toString();
        TextView txtMeal = findViewById(R.id.plateName);
        String url = "https://mensaappserver.onrender.com/plate";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            plate = new Gson().fromJson(response, Food.class);
                            txtMeal.setText(plate.getName());
                            recyclerViewPlateInfo();
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
                params.put("plateId",plateId);
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
//        Intent intent = getIntent();
//
//        String meal = intent.getStringExtra("MealName");
//        TextView MealName = findViewById(R.id.plateName);
//        MealName.setText(meal);
//        ImageView Filter = findViewById(R.id.imageView8);
//        int filter = intent.getIntExtra("Filter", 32);
//        Filter.setImageResource(filter);
//        ImageView Rating = findViewById(R.id.imageView10);
//        int rating = intent.getIntExtra("Rating", 32);
//        Rating.setImageResource(rating);
    }

    private void recyclerViewPlateInfo(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView3);

        //setUpMensaModels();

        PlateInfoAdapter adapter = new PlateInfoAdapter(plate);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int rating = 0;
        if(id == R.id.star1){
            rating = 1;
        }else if(id == R.id.star2){
            rating = 2;
        }else if(id == R.id.star3){
            rating = 3;
        }else if(id == R.id.star4){
            rating = 4;
        }else if(id == R.id.star5){
            rating = 5;
        }
        giveRating(rating);
    }

    private void giveRating(int rating) {
        String comment = "";
        if(!txtComment.getEditText().getText().toString().isEmpty()){
            comment = txtComment.getEditText().getText().toString();
        }
        String url = "https://mensaappserver.onrender.com/addRatingForPlate";
        String finalComment = comment;
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            CharSequence text = "Review was given sucsessfully";
                            int duration = Toast.LENGTH_SHORT;
                            Toast.makeText(MainActivity3.this, "Review was given sucsessfully", Toast.LENGTH_LONG).show();
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
                params.put("foodName",plate.getName());
                params.put("mensaName",plate.getMensaName());
                params.put("comment", finalComment);
                params.put("rating",Integer.toString(rating));
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
}