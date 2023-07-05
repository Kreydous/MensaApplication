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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mensaapplication.Adapters.MensaAdapter;
import com.mensaapplication.Models.Mensa;
import com.mensaapplication.ui.theme.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    List<Mensa> mensaModel;
    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = "https://mensaappserver.onrender.com/mensas";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
//                            String datetime = response.getString("datetime");
//                            String date = datetime.split("T")[0];
                            System.out.println(response);
                            Mensa[] mensas = new Gson().fromJson(response,Mensa[].class);
                            mensaModel = Arrays.asList(mensas);
                            recyclerViewMensa();
                            //System.out.println(mensas);
                        } catch (Exception e) {
                            System.out.println("G");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("H");
            }
        });
        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        Volley.newRequestQueue(this).add(request);
    }

    private void recyclerViewMensa(){
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        //setUpMensaModels();

        MensaAdapter adapter = new MensaAdapter(mensaModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button profile = findViewById(R.id.btnMensa);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });

        MensaAdapter.OnButtonClickListener buttonClickListener = new MensaAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(int position) {
                // Azioni da eseguire quando viene cliccato un pulsante nella RecyclerView
                // Ad esempio, avvia un'altra Activity passando dati
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, recyclerView, ViewCompat.getTransitionName(recyclerView));

                intent.putExtra("position", position);
                intent.putExtra("mensaName",mensaModel.get(position).getName());
                profile.getText();
                startActivity(intent, options.toBundle());
            }
        };

        adapter.setOnButtonClickListener(buttonClickListener);
    }

    private void changeActivity(){
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
    }

    public void setUpMensaModels(){

        /*String[] mensaNames = getResources().getStringArray(R.array.Mensa_names);
        for (int i = 0; i < mensaNames.length; i++){
            mensaModel.add(new MensaModel(mensaNames[i]));
        }*/

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        //intent.putExtra("NAME", mensaModels.get(position).getMensaName());
        startActivity(intent);
    }
}