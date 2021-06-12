package com.example.absensi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Fragment_Awal extends Fragment {

    Pegawai peglogin;
    TextView txtnama,txtnip,txttgl,txtcin,txtcout,txtjabatan;
    Button btncin,btncout;

    public Fragment_Awal() {
        // Required empty public constructor
    }

    public Fragment_Awal(Pegawai pegawailogin) {
        peglogin = pegawailogin;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__awal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtnama = view.findViewById(R.id.txnama);
        txtnip = view.findViewById(R.id.txnip);
        txtjabatan = view.findViewById(R.id.txjabatan);
        txttgl = view.findViewById(R.id.txdate);
        txtcin = view.findViewById(R.id.txcin);
        txtcout = view.findViewById(R.id.txcout);
        btncin = view.findViewById(R.id.btn_checkin1);
        btncout = view.findViewById(R.id.btn_cheackout2);

        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        String datenow = df.format(now);
        txtnama.setText(peglogin.getNama());
        txtnip.setText(peglogin.getNik());
        txtjabatan.setText(peglogin.getJabatan());
        txttgl.setText(datenow);

        btncin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "cin", Toast.LENGTH_SHORT).show();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://absensiandroid-6f6d0-default-rtdb.asia-southeast1.firebasedatabase.app/data/123.json",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    System.out.println(jsonObject.get("cin"));
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
            }
        });

        btncout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "cout", Toast.LENGTH_SHORT).show();
            }
        });

    }
}