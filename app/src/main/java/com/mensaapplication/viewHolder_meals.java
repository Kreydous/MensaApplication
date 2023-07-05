package com.mensaapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class viewHolder_meals extends RecyclerView.ViewHolder {
    ImageView RatingView, FilterView;
    TextView TypeView, MealNameView, PriceBedView, PriceStudView;
    public CardView cardView;
    public viewHolder_meals(@NonNull View itemView) {
        super(itemView);
        RatingView = itemView.findViewById(R.id.Rating);
        FilterView = itemView.findViewById(R.id.Filter);
        TypeView = itemView.findViewById(R.id.Type);
        MealNameView = itemView.findViewById(R.id.MealName);
        PriceBedView = itemView.findViewById(R.id.PriceBed);
        PriceStudView = itemView.findViewById(R.id.PriceStud);
        cardView = itemView.findViewById(R.id.meals_container);
    }
}
