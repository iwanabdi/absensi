package com.example.absensi;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName="pegawai",indices = {@Index(value = {"pegawai_nik", "pegawai_email"},unique = true)})
public class Pegawai implements Parcelable{
    @NonNull
    @PrimaryKey
    @ColumnInfo(name="pegawai_nik")
    String nik;

    @ColumnInfo(name="pegawai_nama")
    String nama;

    @ColumnInfo(name="pegawai_jabatan")
    String jabatan;

    @ColumnInfo(name="pegawai_email")
    String email;

    @ColumnInfo(name="pegawai_password")
    String password;

    public Pegawai(String nama, String nik, String jabatan, String email, String password) {
        this.nama = nama;
        this.nik = nik;
        this.jabatan = jabatan;
        this.email = email;
        this.password = password;
    }

    protected Pegawai(Parcel in) {
        nama = in.readString();
        nik = in.readString();
        jabatan = in.readString();
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<Pegawai> CREATOR = new Creator<Pegawai>() {
        @Override
        public Pegawai createFromParcel(Parcel in) {
            return new Pegawai(in);
        }

        @Override
        public Pegawai[] newArray(int size) {
            return new Pegawai[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(nik);
        dest.writeString(jabatan);
        dest.writeString(email);
        dest.writeString(password);
    }
}
