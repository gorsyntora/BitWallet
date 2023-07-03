package com.app.room;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.RoomDatabase;
import org.jetbrains.annotations.NotNull;

import java.io.File;

@Database(entities = {Wallet.class}, version = 1)
public abstract  class AppDatabase extends RoomDatabase {
public abstract WalletDAO articleDAO();

    @Override
    public void init(@NotNull DatabaseConfiguration configuration) {

                super.init(configuration);
    }



    private boolean ifDBExists(String dbpath) {
        File db = new File(dbpath);
        if(db.exists()) return true;
        File dir = new File(db.getParent());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return false;
    }


}
