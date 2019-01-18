package cl.moriahdp.RosaApp.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import cl.moriahdp.RosaApp.BaseApplication;
import cl.moriahdp.RosaApp.db.dao.HomeModelObjectDao;
import cl.moriahdp.RosaApp.home.modelObject.HomeModelObject;

@Database(entities = {HomeModelObject.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract HomeModelObjectDao homeModelObjectDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                            //TODO Delete it when data comes from API
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executor executor = BaseApplication.getDatabaseIO();
                                    executor.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            getInstance().runInTransaction(new Runnable() {
                                                @Override
                                                public void run() {
                                                    getInstance().homeModelObjectDao().insert(
                                                            new HomeModelObject("Title", "Description", "videoUrl",
                                                                    "https://via.placeholder.com/150/0000FF/FFFFFF/?text=Digital.com", ""));
                                                }
                                            });
                                        }
                                    });
                                }
                            }).build();
                }
            }
        }
        return INSTANCE;
    }

    public static AppDatabase getInstance() {
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
