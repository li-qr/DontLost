package net.leezw.dontlost;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.leezw.dontlost.persistence.AppDatabase;
import net.leezw.dontlost.ui.HomeListDataAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HomeListFragment extends Fragment {

    public HomeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static HomeListFragment newInstance() {
        HomeListFragment fragment = new HomeListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_list, container, false);

        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.homeRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        HomeListDataAdapter dataAdapter = new HomeListDataAdapter();
        MainActivity.mDisposable.add(AppDatabase.getInstance(getContext())
                .itemDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemList -> dataAdapter.itemList=itemList,
                        throwable -> Log.e(HomeListFragment.class.getSimpleName(), "Unable to get username", throwable)));
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(dataAdapter);
        return rootView;
    }
}