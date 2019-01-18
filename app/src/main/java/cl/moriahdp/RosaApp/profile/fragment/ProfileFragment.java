package cl.moriahdp.RosaApp.profile.fragment;

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
import cl.moriahdp.RosaApp.profile.model.ProfileModel;
import cl.moriahdp.RosaApp.profile.presenter.ProfilePresenter;
import cl.moriahdp.RosaApp.profile.view.ProfileView;
import cl.moriahdp.RosaApp.utils.bus.BusProvider;

public class ProfileFragment extends BaseFragment implements IBackPressedCallback {

    private static String PROFILE_TAG = "ProfileFragment";
    private View mRoot;
    private ProfilePresenter profilePresenter;

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onFragmentBackPressed() {
    }

    @Override
    protected View onCreateEventView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_profile, container, false);
        profilePresenter = new ProfilePresenter(
                new ProfileModel(new DataRepository(getContext()), BusProvider.getInstance()),
                new ProfileView(this, mRoot, BusProvider.getInstance()));
        return mRoot;

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
            dashboardActivity.setToolbarTitle(R.string.profile_tab);
            dashboardActivity.hideBottomBar(true);
            dashboardActivity.configureToolbarBackArrow(true);
        }
    }

    public static String getProfileTag() {
        return PROFILE_TAG;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.register(this);
        BusProvider.register(profilePresenter);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        BusProvider.getInstance().unregister(profilePresenter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
