package net.leezw.dontlost;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.leezw.dontlost.persistence.AppDatabase;
import net.leezw.dontlost.persistence.Item;
import net.leezw.dontlost.ui.HomeListDataAdapter;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomeListFragment extends Fragment {

    HomeListDataAdapter dataAdapter;
    CompositeDisposable mDisposable;
    public HomeListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisposable=new CompositeDisposable();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_list, container, false);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.homeRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        dataAdapter = new HomeListDataAdapter();
        if(null==HomeListDataAdapter.itemList) {
            mDisposable.add(dataAdapter.updateList(getContext()));
        }
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(dataAdapter);
        return rootView;
    }



    @Override
    public void onStop() {
        mDisposable.clear();
        super.onStop();
    }
}