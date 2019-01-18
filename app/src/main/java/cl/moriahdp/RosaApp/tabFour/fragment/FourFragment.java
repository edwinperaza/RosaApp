package cl.moriahdp.RosaApp.tabFour.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.moriahdp.RosaApp.R;
import cl.moriahdp.RosaApp.baseclasses.BaseFragment;
import cl.moriahdp.RosaApp.baseclasses.IBackPressedCallback;
import cl.moriahdp.RosaApp.repository.DataRepository;
import cl.moriahdp.RosaApp.main.activities.DashboardActivity;
import cl.moriahdp.RosaApp.tabFour.model.FourModel;
import cl.moriahdp.RosaApp.tabFour.presenter.FourPresenter;
import cl.moriahdp.RosaApp.tabFour.view.FourView;
import cl.moriahdp.RosaApp.utils.bus.BusProvider;

public class FourFragment extends BaseFragment implements IBackPressedCallback {

    private static String FOUR_TAG = "FourFragment";
    private FourPresenter fourPresenter;

    public static FourFragment newInstance() {
        Bundle args = new Bundle();
        FourFragment fragment = new FourFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onFragmentBackPressed() {

    }

    @Override
    protected View onCreateEventView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_three, container, false);
        fourPresenter = new FourPresenter(
                new FourModel(new DataRepository(getContext()), BusProvider.getInstance()),
                new FourView(this, root, BusProvider.getInstance()));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureActionBar();
    }

    public void configureActionBar() {
        DashboardActivity dashboardActivity = (DashboardActivity) getActivity();
        if (dashboardActivity != null) {
            dashboardActivity.showToolbar();
            dashboardActivity.setToolbarTitle(R.string.connect_tab);
            dashboardActivity.hideBottomBar(false);
            dashboardActivity.configureToolbarBackArrow(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.register(this);
        BusProvider.register(fourPresenter);
    }

    public static String getFourTag() {
        return FOUR_TAG;
    }

}