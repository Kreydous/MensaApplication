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
/**

 The MensaAdapter class is a RecyclerView adapter used to display a list of Mensa objects.
 It binds the data to the ViewHolder and handles click events on the Mensa items.
 */
public class MensaAdapter extends RecyclerView.Adapter<MensaAdapter.ViewHolder> {
    // MensaAdapter is a RecyclerView adapter used to display a list of Mensa objects

    List<Mensa> mensas;// List of Mensa objects
    private OnButtonClickListener buttonClickListener;
    public MensaAdapter(List<Mensa> mensas) {
        this.mensas = mensas;
    }

    // onCreateViewHolder method: Inflates the RecyclerView item layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row,parent,false);
        return new ViewHolder(inflate);
    }

    // Interface for button click listener
    public interface OnButtonClickListener {
        void onButtonClick(int position);
    }


    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.buttonClickListener = listener;
    }

    // onBindViewHolder method: Binds data to the ViewHolder
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

    // getItemCount method: Returns the number of items in the RecyclerView
    @Override
    public int getItemCount() {
        return mensas.size();
    }

    // ViewHolder class for the RecyclerView items
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
