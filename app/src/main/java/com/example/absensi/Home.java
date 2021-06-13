package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    Intent intentLogin;
    Pegawai pegawailogin;
    BottomNavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intentLogin = getIntent();
        pegawailogin = intentLogin.getParcelableExtra("pegawailogin");

        navigationView = findViewById(R.id.btmnav);
        navigationView.setOnNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            navigationView.setSelectedItemId(R.id.menu_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        if (item.getItemId() == R.id.menu_home) {
            fragment = new Fragment_Awal(pegawailogin);
        }
        if (item.getItemId() == R.id.menu_profile) {
            fragment = new Fragment_Profile(pegawailogin);
        }
        if (item.getItemId() == R.id.menu_profile) {
            fragment = new History();
        }History
        if (item.getItemId() == R.id.menu_logout) {
            finish();
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            return true;
        }

        return false;
    }
}