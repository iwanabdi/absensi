package com.example.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class register extends AppCompatActivity {

    EditText etnama,etnik,etpass,etconfpass,etemail;
    Spinner spjabatan;
    Button btnlgn,btnregis;
    String[] jab = new String[]{"Enginer", "Supervisor","Manager"};
    ArrayList<String> listjaba;
    ArrayAdapter<String> adapterSpinner;
    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etnama = findViewById(R.id.etNama);
        etnik = findViewById(R.id.etNIK);
        etpass = findViewById(R.id.etPass);
        etemail = findViewById(R.id.etemail);
        etconfpass = findViewById(R.id.etconfirmpass);
        btnlgn = findViewById(R.id.btnlogin);
        btnregis = findViewById(R.id.btnregister);
        spjabatan = findViewById(R.id.spinjabatan);
        listjaba = new ArrayList<>();
        listjaba.addAll(Arrays.asList(jab));
        adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listjaba);
        spjabatan.setAdapter(adapterSpinner);
    }

    public void clickregis(View v) {
        String nik = etnik.getText().toString();
        String nama = etnama.getText().toString();
        String jabat = spjabatan.getSelectedItem().toString();
        String pass = etpass.getText().toString();
        String email = etemail.getText().toString();
        Pegawai mhs = new Pegawai(nama,nik, jabat, email,pass);

        try {
            new Addpegawaitask().execute(mhs);
            reset();
            Toast.makeText(this, "berhasil register", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void clicklogin(View v) {
        finish();
    }

    private class Addpegawaitask extends AsyncTask<Pegawai, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Pegawai... pegawais) {
            db = AppDatabase.getDatabase(getApplicationContext());
            db.pegawaiDAO().insert(pegawais[0]);
            return null;
        }
    }

    public void reset() {
        etnama.setText("");
        etnik.setText("");
        etpass.setText("");
        etconfpass.setText("");
        spjabatan.setSelected(false);
    }
}