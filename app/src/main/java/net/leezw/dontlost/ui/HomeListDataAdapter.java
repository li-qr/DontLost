package net.leezw.dontlost.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.leezw.dontlost.R;
import net.leezw.dontlost.persistence.AppDatabase;
import net.leezw.dontlost.persistence.Item;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HomeListDataAdapter extends RecyclerView.Adapter<HomeListViewHolder> {

    public volatile List<Item> itemList = new LinkedList<>();

    public HomeListDataAdapter() {
    }

    @NonNull
    @Override
    public HomeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.home_list_item,parent,false);
        HomeListViewHolder viewHolder = new HomeListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListViewHolder holder, int position) {
        holder.setView(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
