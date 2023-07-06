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


/**

 The MainActivity3 class is an activity that displays detailed information about a food plate.
 It retrieves the plate ID from the intent, sends a request to the server to get the plate data,
 and displays the plate information in a RecyclerView.
 It also allows the user to give a rating to the plate and submit a review comment.
 */
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

        // Get the plate ID from the intent
        String plateId = getIntent().getExtras().get("plateId").toString();
        TextView txtMeal = findViewById(R.id.plateName);
        String url = "https://mensaappserver.onrender.com/plate";
        // Send a request to the server to retrieve the plate data

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
                // Set the parameters for the POST request
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

    }

    /**
     * Sets up the RecyclerView to display the plate information.
     * Initializes the PlateInfoAdapter with the plate data,
     * sets the adapter on the RecyclerView, and sets the layout manager.
     */
    private void recyclerViewPlateInfo(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView3);

        PlateInfoAdapter adapter = new PlateInfoAdapter(plate);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /**
     * Handles the click event when a view is clicked.
     *
     * @param v The clicked view.
     */
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

    /**
     * Sends a request to the server to submit a rating and review comment for the plate.
     *
     * @param rating The rating given by the user.
     */
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