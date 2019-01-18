package cl.moriahdp.RosaApp.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.moriahdp.RosaApp.R;
import cl.moriahdp.RosaApp.baseclasses.BaseFragment;
import cl.moriahdp.RosaApp.baseclasses.IBackPressedCallback;
import cl.moriahdp.RosaApp.repository.HomeModelRepository;
import cl.moriahdp.RosaApp.home.model.HomeModel;
import cl.moriahdp.RosaApp.home.presenter.HomePresenter;
import cl.moriahdp.RosaApp.home.view.HomeView;
import cl.moriahdp.RosaApp.main.activities.DashboardActivity;
import cl.moriahdp.RosaApp.utils.bus.BusProvider;

public class HomeFragment extends BaseFragment implements IBackPressedCallback {

    private static String HOME_TAG = "HomeFragment";
    private View mRoot;
    private HomePresenter homePresenter;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    protected View onCreateEventView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_home, container, false);
        homePresenter = new HomePresenter(
                new HomeModel(
                        new HomeModelRepository(getContext()),
                        BusProvider.getInstance()),
                new HomeView(this, mRoot, BusProvider.getInstance()));
        return mRoot;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureActionBar();
    }

    public void configureActionBar() {
        DashboardActivity dashboardActivity = (DashboardActivity) getActivity();
        if (dashboardActivity != null) {
            dashboardActivity.showToolbar();
            dashboardActivity.setToolbarTitle(R.string.home_tab);
            dashboardActivity.hideBottomBar(false);
            dashboardActivity.configureToolbarBackArrow(false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.register(this);
        BusProvider.register(homePresenter);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        BusProvider.getInstance().unregister(homePresenter);
    }

    public static String getHomeTag() {
        return HOME_TAG;
    }

    @Override
    public void onFragmentBackPressed() {
        DashboardActivity dashboardActivity = (DashboardActivity) getActivity();
        if (dashboardActivity != null) {
            dashboardActivity.doBackPressed();
        }
    }

}
