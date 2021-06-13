package com.example.absensi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PegawaiDAO {
    @Query("SELECT * FROM pegawai")
    List<Pegawai> getAllpeg();

    @Query("SELECT * FROM pegawai WHERE pegawai_email = :email AND pegawai_password = :pass")
    Pegawai getpeg(String email,String pass);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Pegawai peg);

    @Update
    void update(Pegawai peg);

    @Delete
    void delete(Pegawai peg);
}
