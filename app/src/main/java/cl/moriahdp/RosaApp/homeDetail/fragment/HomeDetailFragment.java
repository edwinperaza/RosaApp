package cl.moriahdp.RosaApp.homeDetail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.moriahdp.RosaApp.R;
import cl.moriahdp.RosaApp.baseclasses.BaseFragment;
import cl.moriahdp.RosaApp.baseclasses.IBackPressedCallback;
import cl.moriahdp.RosaApp.repository.DataRepository;
import cl.moriahdp.RosaApp.home.modelObject.HomeModelObject;
import cl.moriahdp.RosaApp.homeDetail.model.HomeDetailModel;
import cl.moriahdp.RosaApp.homeDetail.presenter.HomeDetailPresenter;
import cl.moriahdp.RosaApp.homeDetail.view.HomeDetailView;
import cl.moriahdp.RosaApp.main.activities.DashboardActivity;
import cl.moriahdp.RosaApp.utils.bus.BusProvider;

public class HomeDetailFragment extends BaseFragment implements IBackPressedCallback {

    private static String SERMON_DETAIL_TAG = "HomeDetailFragment";
    private View mRoot;
    private HomeDetailPresenter homeDetailPresenter;

    public static HomeDetailFragment newInstance(HomeModelObject sermon) {
        Bundle args = new Bundle();
        args.putSerializable("Sermon", sermon);
        HomeDetailFragment fragment = new HomeDetailFragment();
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
        mRoot = inflater.inflate(R.layout.fragment_sermon_detail, container, false);

        HomeModelObject sermon = new HomeModelObject();
        if (getArguments() != null && getArguments().containsKey("Sermon")) {
            sermon = (HomeModelObject) getArguments().getSerializable("Sermon");
        }

        homeDetailPresenter = new HomeDetailPresenter(
                new HomeDetailModel(new DataRepository(getContext()), BusProvider.getInstance(), sermon),
                new HomeDetailView(this, mRoot, BusProvider.getInstance()));
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
            dashboardActivity.configureToolbarBackArrow(true);
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
        BusProvider.register(homeDetailPresenter);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        BusProvider.getInstance().unregister(homeDetailPresenter);
    }

    public static String getSermonDetailTag() {
        return SERMON_DETAIL_TAG;
    }

    @Override
    public void onFragmentBackPressed() {
        DashboardActivity dashboardActivity = (DashboardActivity) getActivity();
        if (dashboardActivity != null) {
            dashboardActivity.doBackPressed();
        }
    }

}
