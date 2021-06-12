package com.example.absensi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Fragment_Awal extends Fragment {

    Pegawai peglogin;
    TextView txtnama,txtnip,txttgl,txtcin,txtcout,txtjabatan;

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

        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        String datenow = df.format(now);
        txtnama.setText(peglogin.getNama());
        txtnip.setText(peglogin.getNik());
        txtjabatan.setText(peglogin.getJabatan());
        txttgl.setText(datenow);

    }
}