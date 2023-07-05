package com.mensaapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mensaapplication.ui.theme.RecyclerViewInterface;

import java.util.ArrayList;

public class M_RecyclerViewAdapter extends RecyclerView.Adapter<M_RecyclerViewAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<MensaModel> mensaModels;

    private OnButtonClickListener buttonClickListener;

    public M_RecyclerViewAdapter(Context context, ArrayList<MensaModel> mensaModels, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.mensaModels = mensaModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public M_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where you inflate the layout (giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent,false );
        return new M_RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull M_RecyclerViewAdapter.MyViewHolder holder, final int position) {
        // assigning values to the view we create in the recycler_view_row
        // based on the position of the recycler view
        holder.button.setText(mensaModels.get(position).getMensaName());

        final int buttonPosition = position; // Aggiungi questa riga

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonClickListener != null) {
                    buttonClickListener.onButtonClick(buttonPosition); // Usa buttonPosition invece di position
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        // the recycler view just want to know the number of items you want displayed
        return mensaModels.size();
    }

    public interface OnButtonClickListener {
        void onButtonClick(int position);
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.buttonClickListener = listener;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        Button button;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            button = itemView.findViewById(R.id.btnMensa);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }

    }

}

