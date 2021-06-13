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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String datenow = df.format(now);
        txtnama.setText(peglogin.getNama());
        txtnip.setText(peglogin.getNik());
        txtjabatan.setText(peglogin.getJabatan());
        txttgl.setText(datenow);
        btncout.setEnabled(false);

        ambilabsennow(datenow);

        btncin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                String url = getResources().getString(R.string.apikirim)+peglogin.getNik()+"/"+datenow+".json";
//                System.out.println(url);
                StringRequest request = new StringRequest(Request.Method.PUT, url,
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respObj = new JSONObject(response);
                            ambilabsennow(datenow);
                            Toast.makeText(getActivity(), "Berhasil Check In", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // method to handle errors.
                        Toast.makeText(getActivity(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        JSONObject send = new JSONObject();
                        try {
                            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                            String jamnow = df.format(now);
                            send.put("cin",jamnow);
                            send.put("date",datenow);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String jsonString = send.toString();
                        return jsonString.getBytes();
                    }
                };
                queue.add(request);
            }
        });

        btncout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                String url = getResources().getString(R.string.apikirim)+peglogin.getNik()+"/"+datenow+".json";
                StringRequest request = new StringRequest(Request.Method.PATCH  , url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject respObj = new JSONObject(response);
                                    ambilabsennow(datenow);
                                    Toast.makeText(getActivity(), "Berhasil Check Out", Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // method to handle errors.
                        Toast.makeText(getActivity(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        JSONObject send = new JSONObject();
                        try {
                            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                            String jamnow = df.format(now);
                            send.put("cout",jamnow);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String jsonString = send.toString();
                        return jsonString.getBytes();
                    }
                };
                queue.add(request);
            }
        });

    }

    public void ambilabsennow(String date){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getResources().getString(R.string.apiget),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonisi = jsonObject.getJSONObject(peglogin.getNik());
                            JSONObject isi = jsonisi.getJSONObject(date);
                            String cin = isi.getString("cin");
                            if (cin != null){
                                txtcin.setText(cin);
                                btncin.setEnabled(false);
                                btncout.setEnabled(true);
                            }
                            String cout = isi.getString("cout");
                            if (cout != null){
                                txtcout.setText(cout);
                                btncout.setEnabled(false);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getCause().toString(),Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }
}