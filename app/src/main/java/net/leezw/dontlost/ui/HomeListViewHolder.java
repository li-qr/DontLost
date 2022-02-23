package net.leezw.dontlost.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.leezw.dontlost.R;
import net.leezw.dontlost.persistence.Item;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HomeListViewHolder extends RecyclerView.ViewHolder {
    TextView nameTextView;
    TextView dateTextView;
    TextView tokenTextView;
    TextView tictTextView;

    public HomeListViewHolder(@NonNull View itemView) {
        super(itemView);
        this.nameTextView = itemView.findViewById(R.id.nameText);
        this.dateTextView = itemView.findViewById(R.id.dateText);
        this.tokenTextView = itemView.findViewById(R.id.tokenText);
        this.tictTextView = itemView.findViewById(R.id.tickText);
    }

    public void setView(Item item){
        this.nameTextView.setText(item.name);
        Instant instant = Instant.ofEpochMilli(item.date);
        ZoneId zone = ZoneId.systemDefault();
        this.dateTextView.setText(LocalDateTime.ofInstant(instant,zone)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        long now = System.currentTimeMillis();
        this.tokenTextView.setText(now<item.date?"还有":"已经");
        long span = Math.abs(item.date-now);
        this.tictTextView.setText(span/(1000*60*60*24)+"天");
    }
}
