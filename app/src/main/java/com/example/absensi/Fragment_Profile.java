package com.example.absensi;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_Profile extends Fragment {

    Pegawai peglogin;
    TextView txtnama,txtnip,txtjabatan,txtemail;
    EditText txtpass,txtconfirmpass;
    Button btnrubah;
    AppDatabase db;


    public Fragment_Profile() {
        // Required empty public constructor
    }

    public Fragment_Profile(Pegawai pegawailogin) {
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
        return inflater.inflate(R.layout.fragment__profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtnama = view.findViewById(R.id.txtnamapro);
        txtnip = view.findViewById(R.id.txtnippro);
        txtjabatan = view.findViewById(R.id.txtjabpro);
        txtemail = view.findViewById(R.id.txtemapro);
        txtpass = view.findViewById(R.id.etPass);
        txtconfirmpass = view.findViewById(R.id.etconfirmpass);
        btnrubah = view.findViewById(R.id.btnubah);

        txtnama.setText(peglogin.getNama());
        txtnip.setText(peglogin.getNik());
        txtjabatan.setText(peglogin.getJabatan());
        txtemail.setText(peglogin.getEmail());

        btnrubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtpass.getText().toString().equals(txtconfirmpass.getText().toString())){
                    peglogin.setEmail(txtpass.getText().toString());
                    new updatepass().execute(peglogin);
                    Toast.makeText(getActivity(), "Password berhasil di rubah", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "Password pastikan sama", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class updatepass extends AsyncTask<Pegawai,Void,Void>{

        @Override
        protected Void doInBackground(Pegawai... pegawais) {
            db.pegawaiDAO().update(pegawais[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            Toast.makeText(MainActivity.this,"Update barang berhasil dilakukan",Toast.LENGTH_SHORT).show();
        }
    }
}