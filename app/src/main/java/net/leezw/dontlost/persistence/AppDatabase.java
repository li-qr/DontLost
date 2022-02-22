package net.leezw.dontlost.persistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Item.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract ItemDao itemDao();

    private static volatile String INIT_SQL= "insert into item (`name`,`date`,`index`) " +
            "values " +
            "(\"劳动节\",\"1651334400000\",\"0\")," +
            "(\"端午节\",\"1654185600000\",\"0\")";


    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "dont_lost.db")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    db.execSQL(INIT_SQL);
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
