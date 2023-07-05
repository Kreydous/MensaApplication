package com.mensaapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mensaapplication.Models.Food;
import com.mensaapplication.R;

import java.util.List;

public class PlateInfoAdapter extends RecyclerView.Adapter<PlateInfoAdapter.ViewHolder>{
    Food plate;
    private OnButtonClickListener buttonClickListener;
    public PlateInfoAdapter(Food plate) {
        this.plate = plate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_holder,parent,false);
        return new ViewHolder(inflate);
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
        holder.reviewComment.setText(plate.getRatings().get(position).getComment());
        holder.reviewNumber.setText(Integer.toString(plate.getRatings().get(position).getRatingGiven()));
    }

    @Override
    public int getItemCount() {
        return plate.getRatings().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ConstraintLayout plateLayout;
        TextView reviewComment,reviewNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            plateLayout = itemView.findViewById(R.id.ReviewHolderLayout);
            reviewComment = itemView.findViewById(R.id.review_txt);
            reviewNumber = itemView.findViewById(R.id.reviewNumber);
        }
    }
}
