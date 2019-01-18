package cl.moriahdp.RosaApp.home.model;

import com.squareup.otto.Bus;

import java.util.List;

import cl.moriahdp.RosaApp.baseclasses.BaseModel;
import cl.moriahdp.RosaApp.repository.HomeModelRepository;
import cl.moriahdp.RosaApp.db.ResponseListener;
import cl.moriahdp.RosaApp.home.modelObject.HomeModelObject;

public class HomeModel extends BaseModel<HomeModelRepository> {


    public HomeModel(HomeModelRepository homeModelRepository, Bus bus) {
        super(homeModelRepository, bus);
    }

    public void requestHomeItemList() {
        repository.getAllHomeModelObjectFromDatabase(new ResponseListener<List<HomeModelObject>>() {
            @Override
            public void onResponse(List<HomeModelObject> body) {
                bus.post(new RequestHomeListSuccess(body));
            }
        });

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