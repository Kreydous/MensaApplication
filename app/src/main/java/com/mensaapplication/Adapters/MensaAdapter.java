package com.mensaapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mensaapplication.M_RecyclerViewAdapter;
import com.mensaapplication.Models.Mensa;
import com.mensaapplication.R;

import java.util.List;

public class MensaAdapter extends RecyclerView.Adapter<MensaAdapter.ViewHolder> {
    List<Mensa> mensas;
    private OnButtonClickListener buttonClickListener;
    public MensaAdapter(List<Mensa> mensas) {
        this.mensas = mensas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row,parent,false);
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
        holder.mensaBtn.setText(mensas.get(position).getName());
        //holder.mensaAddr.setText(mensas.get(position).getLocation());

        final int buttonPosition = position; // Aggiungi questa riga

        holder.mensaBtn.setOnClickListener(new View.OnClickListener() {
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
        return mensas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        Button mensaBtn;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mensaBtn = itemView.findViewById(R.id.btnMensa);
            mainLayout = itemView.findViewById(R.id.mensaView);
        }
    }
}
