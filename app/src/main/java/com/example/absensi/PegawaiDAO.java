package com.example.absensi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PegawaiDAO {
    @Query("SELECT * FROM pegawai")
    List<Pegawai> getAllMhs();

    @Insert
    void insert(Pegawai peg);

    @Update
    void update(Pegawai peg);

    @Delete
    void delete(Pegawai peg);
}
