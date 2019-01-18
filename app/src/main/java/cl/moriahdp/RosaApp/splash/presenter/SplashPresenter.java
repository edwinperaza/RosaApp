package cl.moriahdp.RosaApp.splash.presenter;

import com.squareup.otto.Subscribe;

import cl.moriahdp.RosaApp.baseclasses.BasePresenter;
import cl.moriahdp.RosaApp.splash.model.SplashModel;
import cl.moriahdp.RosaApp.splash.view.SplashView;

public class SplashPresenter  extends BasePresenter<SplashModel, SplashView> {

    public SplashPresenter(SplashModel model, SplashView view) {
        super(model, view);
    }

    public void onResume(){
        model.startTimer();
    }

    @Subscribe
    public void onTimeUp(SplashModel.TimeUpEvent event) {
        view.goToDashBoard();
    }

    public void onPause() {
        model.cancelTimer();
    }
}
