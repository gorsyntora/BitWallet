package com.app.bitwallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.app.bitwallet.databinding.ActivityMainBinding;
import com.app.recycler.Item;
import com.app.recycler.ItemViewAdapter;
import com.app.room.AppDatabase;
import com.app.room.Wallet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String BIT_WALLET = "BIT_WALLET";
    private AppDatabase db;
    private RecyclerView recyclerView;
    ActivityMainBinding binding;

    private float userBalance = 0.000_000_15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        if (db == null) {
            db = Room
                    .databaseBuilder(this, AppDatabase.class, "appdata.db")
                    .allowMainThreadQueries()
                    .build();
        }

        binding.wallet.startBtn.setOnClickListener(v -> {
            binding.wallet.seekBar.setProgress(5);
            binding.wallet.balanceTextvalue.setText(String.format("%d Satoshi", 15));
            binding.wallet.balanceNumvalue.setText(String.format("%.8f", userBalance ));
        });


        List<Item> list = new ArrayList<>();
        list.add(new Item("Server 1", R.drawable.speed_off));
        list.add(new Item("Server 2", R.drawable.speed_off));
        list.add(new Item("Server 3", R.drawable.speed_off));
        list.add(new Item("Server 4", R.drawable.speed_off));

        setRecyclerView(list);
        binding.wallet.boostBtn.setOnClickListener(v ->
                {

                    ItemViewAdapter adapter = (ItemViewAdapter) recyclerView.getAdapter();
                    List<Item> items = adapter.getList();
                    for (int i = 0; i < items.size(); i++) {

                        // Для тестирования UI используем main thread
                        // Для пинга серверов нужен будет отдельный трэд

                        Handler handler = new Handler(Looper.getMainLooper());
                        int finalI = i;
                        handler.postDelayed(() -> {
                            Log.d("THREAD", Thread.currentThread().getName());
                            items.get(finalI).setDrawable(R.drawable.speeder_on);
                            adapter.notifyItemChanged(finalI);
                        }, 2000L * finalI);


                    }

                }
        );
        binding.wallet.takeBtn.setOnClickListener(v -> {
            initRoom();
            showShareDlg();
        });

    }

    void setRecyclerView(List<Item> list) {
        recyclerView = findViewById(R.id.content_view);
        recyclerView.setHasFixedSize(true);
        ItemViewAdapter adapter = new ItemViewAdapter(list, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    void initRoom() {

        int size = db.articleDAO().getTableSize();
            if (size == 0) {
            Wallet wallet = new Wallet("Bitcoin", userBalance);
            db.articleDAO().insert(wallet);
        }

    }

    public void showShareDlg() {

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Check out the App at: https://play.google.com/store/apps/details?id=" + this.getPackageName();
        String shareSubject = "Get the Wallet on PlayMarket";
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }
}