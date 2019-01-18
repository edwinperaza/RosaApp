package cl.moriahdp.RosaApp.repository;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cl.moriahdp.RosaApp.db.AppDatabase;
import cl.moriahdp.RosaApp.utils.TinyDB;
import cl.moriahdp.RosaApp.utils.data.APIService;
import cl.moriahdp.RosaApp.utils.data.ApiUtils;

public class DataRepository {

    protected final AppDatabase database;
    protected final APIService apiService;
    protected Executor executor = Executors.newSingleThreadExecutor();
    protected TinyDB tinyDB;

    public DataRepository(Context context) {
        this.database = AppDatabase.getDatabase(context);
        this.tinyDB = new TinyDB(context);
        this.apiService = ApiUtils.getAPIService();
    }
}