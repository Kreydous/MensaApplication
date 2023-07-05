package com.mensaapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter_meals extends RecyclerView.Adapter<viewHolder_meals> {

    private interface_mealsRecyclerView interface_mealsRecyclerView;
    Context context;
    List<meals> meals;

    public adapter_meals(Context context, List<com.mensaapplication.meals> meals, interface_mealsRecyclerView interface_mealsRecyclerView) {
        this.context = context;
        this.meals = meals;
        this.interface_mealsRecyclerView = interface_mealsRecyclerView;
    }

    @NonNull
    @Override
    public viewHolder_meals onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder_meals(LayoutInflater.from(context).inflate(R.layout.meal_recyclerview_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder_meals holder, int position) {
        holder.TypeView.setText(meals.get(position).getType());
        holder.MealNameView.setText(meals.get(position).getMealName());
        holder.PriceBedView.setText(meals.get(position).getPriceBedienstete());
        holder.PriceStudView.setText(meals.get(position).getPriceStudent());
        holder.FilterView.setImageResource(meals.get(position).getFilter());
        holder.RatingView.setImageResource(meals.get(position).getRating());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interface_mealsRecyclerView.onRecyclerviewClick(meals.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}
