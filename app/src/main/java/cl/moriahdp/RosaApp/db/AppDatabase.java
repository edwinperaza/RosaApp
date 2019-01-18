package cl.moriahdp.RosaApp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import cl.moriahdp.RosaApp.db.dao.HomeModelObjectDao;
import cl.moriahdp.RosaApp.home.modelObject.HomeModelObject;

@Database(entities = {HomeModelObject.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract HomeModelObjectDao homeModelObjectDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(
                                    context,
                                    AppDatabase.class,
                                    "app_database")
                            .build();
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
