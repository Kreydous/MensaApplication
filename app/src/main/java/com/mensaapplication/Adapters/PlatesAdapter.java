package com.mensaapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mensaapplication.Models.Food;
import com.mensaapplication.Models.Mensa;
import com.mensaapplication.R;

import java.util.List;

public class PlatesAdapter extends RecyclerView.Adapter<PlatesAdapter.ViewHolder>{
    List<Food> plates;
    private OnButtonClickListener buttonClickListener;
    public PlatesAdapter(List<Food> plates) {
        this.plates = plates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_recyclerview_row,parent,false);
        return new PlatesAdapter.ViewHolder(inflate);
    }

    public interface OnButtonClickListener {
        void onButtonClick(int position);
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.buttonClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final int buttonPosition = position; // Aggiungi questa riga

        holder.plateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonClickListener != null) {
                    buttonClickListener.onButtonClick(buttonPosition); // Usa buttonPosition invece di position
                }
            }
        });
        holder.plateName.setText(plates.get(position).getName());
        holder.priceForStud.setText(plates.get(position).getPriceForStudents());
        holder.priceForNonStud.setText(plates.get(position).getPriceForNonStudents());
       // holder.mensaName.setText(plates.get(position).getMensaName());

    }

    @Override
    public int getItemCount() {
        return plates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ConstraintLayout plateLayout;
        ConstraintLayout mainLayout;
        TextView plateName,priceForStud,priceForNonStud,mensaName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            plateLayout = itemView.findViewById(R.id.plateLayout);
            plateName = itemView.findViewById(R.id.MealName);
            priceForStud = itemView.findViewById(R.id.PriceStud);
            priceForNonStud = itemView.findViewById(R.id.PriceBed);
            mainLayout = itemView.findViewById(R.id.platesLayout);
           // mensaName = itemView.findViewById(R.id.mensaName);
        }
    }

}
