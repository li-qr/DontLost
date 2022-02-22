package net.leezw.dontlost;
import net.leezw.dontlost.persistence.AppDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends AppCompatActivity {
    public static final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}