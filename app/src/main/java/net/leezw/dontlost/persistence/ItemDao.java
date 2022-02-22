package net.leezw.dontlost.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;


@Dao
public interface ItemDao {

    @Query("select * from item")
    Flowable<List<Item>> getAll();

    @Query("select * from item where id = :id")
    Single<Item> findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Item item);

    @Delete
    Completable deleteById(Item item);
}
