package com.example.absensi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryAdapter  extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Absensiisi> listabsen;

    public HistoryAdapter(Context context, ArrayList<Absensiisi> list) {
        this.context = context;
        this.listabsen = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.isihistory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_checkin.setText(listabsen.get(position).getCin());
        holder.txt_checkout.setText(listabsen.get(position).getCout());
        holder.txt_tanggal_history.setText(listabsen.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return listabsen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_checkin, txt_checkout, txt_tanggal_history;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println(listabsen.get(2).getCin());
            txt_checkin = itemView.findViewById(R.id.txt_checkin);
            txt_checkout = itemView.findViewById(R.id.txt_checkout);
            txt_tanggal_history = itemView.findViewById(R.id.txt_tanggal_history);
        }

    }
}
