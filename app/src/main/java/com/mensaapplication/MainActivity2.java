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

/**

 The MainActivity2 class is an activity that displays a list of food plates for a specific mensa.
 It communicates with a server to retrieve the food plates data and displays it in a RecyclerView.
 It also handles click events on the food plates to start another activity and pass data to it.
 */

public class MainActivity2 extends AppCompatActivity implements RecyclerViewInterface,View.OnClickListener {

    List<Food> plates;
    TextView mensaNametxt;

    ImageView imgVegan,imgVegetarian,imgChicken,imgPork,imgFish,imgLamm,imgBeef,imgWild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        mensaNametxt = findViewById(R.id.mensaName);
        // Get the mensa name from the intent
        String mensaName = getIntent().getStringExtra("mensaName");
        System.out.println(mensaName);
        mensaNametxt.setText(mensaName);

        // Send a request to the server to retrieve the food plates data

        String url = "https://mensaappserver.onrender.com/platesFromMensa";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            // Parse the response JSON into an array of Food objects

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
                // Set the parameters for the POST request

                Map<String,String> params = new HashMap<String, String>();
                params.put("mensaName",mensaName);
                return params;
            }
        };
        // Set a retry policy for the request

        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        // Add the request to the Volley request queue

        Volley.newRequestQueue(this).add(request);
    }

    /**
     * Sets up the RecyclerView to display the food plates.
     * Initializes the PlatesAdapter with the list of food plates,
     * sets the adapter on the RecyclerView, and sets the layout manager.
     * It also handles click events on the food plates to start another activity and pass data to it.
     */

    private void recyclerViewPlate(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPlates);


        PlatesAdapter adapter = new PlatesAdapter(plates);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PlatesAdapter.OnButtonClickListener buttonClickListener = new PlatesAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(int position) {
                // Perform actions when a button is clicked in the RecyclerView
                // For example, start another activity and pass data to it
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);

                RecyclerView recyclerView = findViewById(R.id.recyclerViewPlates);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity2.this, recyclerView, ViewCompat.getTransitionName(recyclerView));

                intent.putExtra("position", position);
                intent.putExtra("plateId",plates.get(position).getId());
                //profile.getText();
                startActivity(intent, options.toBundle());
            }
        };
        // Set the OnClickListener on the PlatesAdapter

        adapter.setOnButtonClickListener(buttonClickListener);
    }

    /**
     * Starts the MainActivity1 activity.
     * Called when the profile button is clicked.
     */
    private void changeActivity(){
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
    }

    /**
     * Handles the click event when an item in the RecyclerView is clicked.
     *
     * @param position The position of the clicked item.
     */
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}