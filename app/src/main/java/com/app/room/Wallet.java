package com.app.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Wallet {

    @PrimaryKey (autoGenerate = true)
    public int id;


    @ColumnInfo(name = "Cryptocurrency")

    public String cryptocurrency;

    @ColumnInfo(name = "Balance")

    public float balance;


    public Wallet() {}

    public Wallet(String cryptocurrency, float balance) {
        this.cryptocurrency = cryptocurrency;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + cryptocurrency + '\'' +
                ", content='" + balance + '\'' +
                ", id=" + id +
                '}';
    }
}
