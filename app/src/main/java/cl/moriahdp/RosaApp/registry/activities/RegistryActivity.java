package cl.moriahdp.RosaApp.registry.activities;

import android.os.Bundle;

import butterknife.ButterKnife;
import cl.moriahdp.RosaApp.R;
import cl.moriahdp.RosaApp.baseclasses.BaseActivity;
import cl.moriahdp.RosaApp.repository.DataRepository;
import cl.moriahdp.RosaApp.registry.model.RegistryModel;
import cl.moriahdp.RosaApp.registry.presenter.RegistryPresenter;
import cl.moriahdp.RosaApp.registry.view.RegistryView;
import cl.moriahdp.RosaApp.utils.bus.BusProvider;

public class RegistryActivity extends BaseActivity {

    private RegistryPresenter mRegistryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        RegistryView registryView = new RegistryView(this, BusProvider.getInstance());
        mRegistryPresenter = new RegistryPresenter(new RegistryModel(new DataRepository(RegistryActivity.this), BusProvider.getInstance()), registryView);
        ButterKnife.bind(registryView, this);
        hideToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.register(mRegistryPresenter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.unregister(mRegistryPresenter);
    }
}
