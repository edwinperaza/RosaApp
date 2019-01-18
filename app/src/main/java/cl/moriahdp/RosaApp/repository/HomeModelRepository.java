package cl.moriahdp.RosaApp.repository;

import android.content.Context;

import java.util.List;

import cl.moriahdp.RosaApp.db.ResponseListener;
import cl.moriahdp.RosaApp.home.modelObject.HomeModelObject;

public class HomeModelRepository extends DataRepository {

    public HomeModelRepository(Context context) {
        super(context);
    }

    public void getAllHomeModelObjectFromDatabase(final ResponseListener<List<HomeModelObject>> listener) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                listener.onResponse(database.homeModelObjectDao().getAll());
            }
        });
    }
}
