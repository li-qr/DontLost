package net.leezw.dontlost;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.leezw.dontlost.persistence.Item;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}