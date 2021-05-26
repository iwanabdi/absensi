package com.example.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class register extends AppCompatActivity {

    EditText etnama,etnik,etpass,etconfpass;
    Spinner spjabatan;
    Button btnlgn,btnregis;
    String[] prodi = new String[]{"Enginer", "Supervisor","Manager"};
    ArrayList<String> listProdi;
    ArrayAdapter<String> adapterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etnama = findViewById(R.id.etNama);
        etnik = findViewById(R.id.etNIK);
        etpass = findViewById(R.id.etPass);
        etconfpass = findViewById(R.id.etconfirmpass);
        btnlgn = findViewById(R.id.btnlogin);
        btnregis = findViewById(R.id.btnregister);
        spjabatan = findViewById(R.id.spinjabatan);
        listProdi = new ArrayList<>();
        listProdi.addAll(Arrays.asList(prodi));
        adapterSpinner = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listProdi);
        spinProdi.setAdapter(adapterSpinner);
    }
}