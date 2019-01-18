package cl.moriahdp.RosaApp.home.model;

import android.support.annotation.IntDef;

import com.squareup.otto.Bus;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cl.moriahdp.RosaApp.baseclasses.BaseModel;
import cl.moriahdp.RosaApp.repository.HomeModelRepository;
import cl.moriahdp.RosaApp.db.ResponseListener;
import cl.moriahdp.RosaApp.home.modelObject.HomeModelObject;

public class HomeModel extends BaseModel<HomeModelRepository> {

    protected Executor executor = Executors.newSingleThreadExecutor();

    public HomeModel(HomeModelRepository homeModelRepository, Bus bus) {
        super(homeModelRepository, bus);
    }

    public void requestHomeItemList() {

        final List<HomeModelObject> homeModelObjects = new ArrayList<>();
        for (Integer i = 1; i < 8; i++) {
            HomeModelObject headerModel = new HomeModelObject("GLOBERS POR ALMORZAR", "",
                    "https://cachapasdon70.cl/wp-content/uploads/2017/04/MARACUCHA-DE-POLLO-768x576.jpg",
                    "https://cachapasdon70.cl/wp-content/uploads/2017/04/MARACUCHA-DE-POLLO-768x576.jpg" ,
                    "", 1);
            homeModelObjects.add(headerModel);
            for (Integer j = 1; j < 10; j++) {
                HomeModelObject model = new HomeModelObject("Persona " + i, "",
                        "https://cachapasdon70.cl/wp-content/uploads/2017/04/PATACON-1-683x512.jpg",
                        "https://cachapasdon70.cl/wp-content/uploads/2017/04/PATACON-1-683x512.jpg" ,
                        "", 2);
                homeModelObjects.add(model);
            }
        }

        executor.execute(new Runnable() {
            @Override
            public void run() {
                bus.post(new RequestHomeListSuccess(homeModelObjects));
            }
        });


//        repository.getAllHomeModelObjectFromDatabase(new ResponseListener<List<HomeModelObject>>() {
//            @Override
//            public void onResponse(List<HomeModelObject> body) {
//                bus.post(new RequestHomeListSuccess(body));
//            }
//        });

//        repository.getApiService().getHomeItemList().enqueue(new Callback<List<HomeModelObject>>() {
//            @Override
//            public void onResponse(Call<List<HomeModelObject>> call, Response<List<HomeModelObject>> response) {
//                if (response.isSuccessful()) {
//                    List<HomeModelObject> homeModelObjectList = response.body();
//                    bus.post(new RequestHomeListSuccess(homeModelObjectList));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<HomeModelObject>> call, Throwable t) {
//                bus.post(new RequestHomeListFailure());
//            }
//        });
    }

    public class RequestHomeListSuccess {
        private List<HomeModelObject> homeItemList;

        RequestHomeListSuccess(List<HomeModelObject> homeItemList) {
            this.homeItemList = homeItemList;
        }

        public List<HomeModelObject> getHomeItemList() {
            return homeItemList;
        }
    }

    public class RequestHomeListFailure {
    }
}