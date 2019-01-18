package cl.moriahdp.RosaApp.splash.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cl.moriahdp.RosaApp.R;
import cl.moriahdp.RosaApp.baseclasses.BaseActivity;
import cl.moriahdp.RosaApp.repository.DataRepository;
import cl.moriahdp.RosaApp.splash.model.SplashModel;
import cl.moriahdp.RosaApp.splash.presenter.SplashPresenter;
import cl.moriahdp.RosaApp.splash.view.SplashView;
import cl.moriahdp.RosaApp.utils.bus.BusProvider;

public class SplashActivity extends BaseActivity {

    private SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSplashPresenter = new SplashPresenter(
                new SplashModel(new DataRepository(SplashActivity.this), BusProvider.getInstance()),
                new SplashView(this, BusProvider.getInstance()));
        hideToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.register(mSplashPresenter);
        mSplashPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.unregister(mSplashPresenter);
        mSplashPresenter.onPause();
    }
}