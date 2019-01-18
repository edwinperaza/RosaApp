package cl.moriahdp.RosaApp.homeDetail.presenter;


import cl.moriahdp.RosaApp.baseclasses.BasePresenter;
import cl.moriahdp.RosaApp.homeDetail.model.HomeDetailModel;
import cl.moriahdp.RosaApp.homeDetail.view.HomeDetailView;

public class HomeDetailPresenter extends BasePresenter<HomeDetailModel, HomeDetailView> {

    public HomeDetailPresenter(HomeDetailModel model, HomeDetailView view) {
        super(model, view);
        if (model.isValidSermon()) {
            view.setSermonDetail(model.getSermon());
        } else {
            view.showSermonDetailError();
        }
    }

}
