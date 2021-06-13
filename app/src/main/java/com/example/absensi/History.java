package com.example.absensi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class History extends Fragment {

    Pegawai peglogin;
    private RecyclerView rvData;
    private ArrayList<Absensiisi> listabsen;
    HistoryAdapter adapter;


    public History() {
        // Required empty public constructor
    }

    public History(Pegawai pegawailogin) {
        peglogin = pegawailogin;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listabsen = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvData = view.findViewById(R.id.rvhistory);
        rvData.setHasFixedSize(true);
        rvData.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HistoryAdapter(getContext(), listabsen);
        ambilabsennow();
        rvData.setAdapter(adapter);
    }

    public void ambilabsennow(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getResources().getString(R.string.apiget),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonisi = jsonObject.getJSONObject(peglogin.getNik());

                            //rubah objet to arry
                            Iterator x = jsonisi.keys();
                            JSONArray jsonArray = new JSONArray();
                            while (x.hasNext()){
                                String key = (String) x.next();
                                jsonArray.put(jsonisi.get(key));
                            }

                            listabsen.clear();
                            String cin="00:00" ;String cout= "00:00";
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                try {
                                    cin = jsonObject1.getString("cin");
                                }catch (JSONException e){

                                }
                                try {
                                    cout = jsonObject1.getString("cout");
                                }catch (JSONException e){

                                }
                                String date = jsonObject1.getString("date");
                                Absensiisi isiab = new Absensiisi(cin,cout,date);
                                System.out.println(isiab.getDate()+":"+isiab.getCin()+"/"+isiab.getCout());
                                listabsen.add(isiab);
                            }

                            adapter.notifyDataSetChanged();
                            System.out.println(jsonArray.length());
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
        RequestQueue tee = Volley.newRequestQueue(getActivity());
        tee.add(stringRequest);
    }
}