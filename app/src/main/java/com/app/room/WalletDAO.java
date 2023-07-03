package com.app.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WalletDAO {

    @Query("SELECT * FROM Wallet")
    List<Wallet> getAll();
   @Query("SELECT COUNT(1) FROM Wallet")
   int getTableSize();

    @Insert
     void  insert (Wallet wallet);

}

