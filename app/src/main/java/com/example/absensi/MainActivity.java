package com.example.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnlogin,btnregis;
    EditText etpass,etemail;
    AppDatabase db;
    Pegawai peg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment__profile);
        btnlogin = findViewById(R.id.btnlogin_lgn);
        btnregis = findViewById(R.id.btnregis_lgn);
        etpass = findViewById(R.id.etpassword);
        etemail = findViewById(R.id.etmail);
        db = AppDatabase.getDatabase(getApplicationContext());

    }

    public void clickregis(View v) {
        Intent regis = new Intent(this,register.class);
        startActivity(regis);
    }

    public void clicklogin(View v) {
        if (etpass.getText().length()<0 || etemail.getText().length()<0){
            Toast.makeText(this, "email dan password harus diisi", Toast.LENGTH_SHORT).show();
        }else {
            new LoadPegawai().execute();
        }
    }

    private class LoadPegawai extends AsyncTask<Void, Void, Pegawai> {
        @Override
        protected void onPostExecute(Pegawai pegawai) {
            super.onPostExecute(pegawai);
            peg = pegawai;
            if (peg == null){
                Toast.makeText(getApplicationContext(), "email dan password tidak sesuai", Toast.LENGTH_SHORT).show();
            }else {
//                System.out.println(peg.getNama());
                etpass.setText("");etemail.setText("");
                Intent hme= new Intent(getApplicationContext(),Home.class);
                hme.putExtra("pegawailogin", peg);
                startActivity(hme);
            }

        }

        @Override
        protected Pegawai doInBackground(Void... voids) {
            String email = etemail.getText().toString();
            String pass = etpass.getText().toString();
            peg = db.pegawaiDAO().getpeg(email,pass);
            return peg;
        }
    }
}