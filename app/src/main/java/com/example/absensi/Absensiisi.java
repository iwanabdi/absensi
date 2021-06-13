package com.example.absensi;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

public class Absensiisi implements Parcelable {
    String cin,cout,date;

    public Absensiisi() {
    }

    public Absensiisi(@Nullable String cin,@Nullable String cout, String date) {
        this.cin = cin;
        this.cout = cout;
        this.date = date;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCout() {
        return cout;
    }

    public void setCout(String cout) {
        this.cout = cout;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    protected Absensiisi(Parcel in) {
        cin = in.readString();
        cout = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cin);
        dest.writeString(cout);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Absensiisi> CREATOR = new Creator<Absensiisi>() {
        @Override
        public Absensiisi createFromParcel(Parcel in) {
            return new Absensiisi(in);
        }

        @Override
        public Absensiisi[] newArray(int size) {
            return new Absensiisi[size];
        }
    };
}
