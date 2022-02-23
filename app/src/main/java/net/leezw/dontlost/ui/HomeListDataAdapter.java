package net.leezw.dontlost.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.leezw.dontlost.R;
import net.leezw.dontlost.persistence.AppDatabase;
import net.leezw.dontlost.persistence.Item;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeListDataAdapter extends RecyclerView.Adapter<HomeListViewHolder> {

    public static List<Item> itemList;

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
        holder.setView(HomeListDataAdapter.itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return null==itemList?0:HomeListDataAdapter.itemList.size();
    }

    public Disposable updateList(Context context) {
        return Observable
                .<List<Item>>create(emitter -> {
                    emitter.onNext(AppDatabase.getInstance(context).itemDao().getAll());
                    emitter.onComplete();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemList -> {
                    HomeListDataAdapter.itemList = itemList;
                    this.notifyItemRangeInserted(0, itemList.size());
                });
    }
}
