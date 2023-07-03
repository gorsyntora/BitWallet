package com.app.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.app.bitwallet.R;


import java.util.List;


public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.DataItemHolder> {
    private final List<Item> list;

    public ItemViewAdapter(List<Item> list, Context context) {
        this.list = list;
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    private final Context context;
    private final LayoutInflater mLayoutInflater;

    @NonNull
    @Override
    public DataItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View recyclerViewItem = mLayoutInflater.inflate(R.layout.items_layout, parent, false);

        recyclerViewItem.setOnClickListener(v -> handleRecyclerItemClick((RecyclerView) parent, v));
        return new DataItemHolder(recyclerViewItem, context);
    }

    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        // TO DO
    }

    @Override
    public void onBindViewHolder(@NonNull DataItemHolder holder, int position) {
        Item item = list.get(position);
        holder.setItemView(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<Item> getList() {
        return list;
    }

    static class DataItemHolder extends RecyclerView.ViewHolder {
        private final ImageView imgView;
        private final TextView txtView;
        private final TextView speedTxtView;

        public DataItemHolder(@NonNull View itemView, Context context) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imageView);
            txtView = itemView.findViewById(R.id.server_text);
            speedTxtView = itemView.findViewById(R.id.speed_text);
        }

        void setItemView(Item item) {
            txtView.setText(item.getServerName());
            speedTxtView.setText(String.valueOf(item.getSpeedNet()));
            imgView.setImageResource(item.getDrawable());

        }

    }
}

