package cl.moriahdp.RosaApp.main.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import cl.moriahdp.RosaApp.R;
import cl.moriahdp.RosaApp.baseclasses.BaseActivity;
import cl.moriahdp.RosaApp.home.fragment.HomeFragment;
import cl.moriahdp.RosaApp.main.events.DashboardEvent;
import cl.moriahdp.RosaApp.main.views.DashboardView;
import cl.moriahdp.RosaApp.profile.fragment.ProfileFragment;
import cl.moriahdp.RosaApp.search.fragment.SearchFragment;
import cl.moriahdp.RosaApp.tabFour.fragment.FourFragment;
import cl.moriahdp.RosaApp.tabThree.fragment.ThreeFragment;
import cl.moriahdp.RosaApp.utils.bus.BusProvider;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class DashboardActivity extends BaseActivity {

    private static final String DASHBOARD_ACTIVITY_TAG = DashboardActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private Fragment mCurrentFragment;
    private DashboardView mDashboardView = null;
    private String mCurrentFragmentTag;
    private static DashboardActivity instance;

    private boolean mDoubleBackToExitPressedOnce;
    private Menu mMenu;

    // variable to track event time
    private static final int LONG_TIME_SECONDS_REDIRECT = 800;
    private long mLastClickTime = 0;

    //Toolbar
    protected RelativeLayout toolbarTitleContainer;
    protected TextView toolbarTitle;
    protected LinearLayout linearLayoutContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        instance = this;
        mDoubleBackToExitPressedOnce = false;
        bindViewElements();
    }

    /**
     * Bind element from view to Activity
     */
    public void bindViewElements() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_more));
        setSupportActionBar(toolbar);
        linearLayoutContainer = toolbar.findViewById(R.id.ll_container);

        toolbarTitleContainer = toolbar.findViewById(R.id.title_container);
        toolbarTitle = toolbar.findViewById(R.id.tv_toolbar_title);
    }

    public void showToolbar() {
        toolbarTitleContainer.setVisibility(View.VISIBLE);
    }

    public void setToolbarTitle(int text){
        toolbarTitle.setText(text);
    }

    public static DashboardActivity getInstance() {
        return instance;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        if (mDashboardView == null)
            mDashboardView = new DashboardView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    /**
     * Show a new fragment from Activity. Call super goToFragment at BaseActivity and
     * update mCurrentFragment to be able to go back when user press Back at 2nd, 3rd or other
     * level fragments
     *
     * @param layout   to be replace by new fragment
     * @param fragment to be shown to user
     * @param tag      to customize process
     */
    @Override
    public void goToFragment(int layout, Fragment fragment, String tag) {
        super.goToFragment(layout, fragment, tag);
        mCurrentFragment = fragment;
        mCurrentFragmentTag = tag;
    }

    public void goToFragment(Fragment fragment, String tag) {
        mCurrentFragmentTag = tag;
        mCurrentFragment = fragment;
        super.goToFragment(R.id.tab_container, fragment, tag);
    }

    @Override
    public void goToFragmentWithStack(int fragmentContainer, Fragment fragment, String tag) {
        mCurrentFragment = fragment;
        setmCurrentFragmentTag(tag);
        super.goToFragmentWithStack(fragmentContainer, fragment, tag);
    }

    @Subscribe
    public void setCurrentFragmentInTab(DashboardEvent dashboardEvent) {
        mCurrentFragment = dashboardEvent.getBaseFragment();
        mCurrentFragmentTag = dashboardEvent.getFragmentTag();
        goToFragmentWithStack(dashboardEvent.getLayoutContainerID(), mCurrentFragment, dashboardEvent.getFragmentTag());
    }

    /**
     * When user is at first level fragment like myProfileFragment and press back the app
     * show initial fragment clear
     */
    public void backToHomeFragment() {
        setSelectedTab(R.id.home);
    }

    public void backToSearchFragment() {
        setSelectedTab(R.id.search);
    }

    public void backToFavoritesFragment() {
        setSelectedTab(R.id.favorites);
    }

    public void backToConnectFragment() {
        setSelectedTab(R.id.connect);
    }

    public void backToContactFragment() {
        setSelectedTab(R.id.profile);
    }

    /**
     * Select specific tabs at bottomBar, also check if bottom bar is null.
     *
     * @param tabId id of tab to be selected
     */
    public void setSelectedTab(int tabId) {
        // Preventing multiple clicks, using threshold of 10 second
        if (SystemClock.elapsedRealtime() - mLastClickTime < LONG_TIME_SECONDS_REDIRECT) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        BottomNavigationView navigation = mDashboardView.getNavigation();
        if (navigation != null) {
            navigation.setSelectedItemId(tabId);
        } else {
            Log.d(DASHBOARD_ACTIVITY_TAG, "BottomBar is null");
            //TODO implement behavior when bottomBar is null
        }
    }

    public int getSelectedTab() {
        BottomNavigationView navigation = mDashboardView.getNavigation();
        return navigation.getSelectedItemId();
    }

    public void hideBottomBar(boolean hide) {
        if (mDashboardView != null && mDashboardView.getNavigation() != null) {
            if (hide) {
                mDashboardView.getNavigation().setVisibility(View.GONE);
            } else {
                mDashboardView.getNavigation().setVisibility(View.VISIBLE);
            }
        }
    }

    public String getmCurrentFragmentTag() {
        return mCurrentFragmentTag;
    }

    public void setmCurrentFragmentTag(String mCurrentFragmentTag) {
        this.mCurrentFragmentTag = mCurrentFragmentTag;
    }

    public Fragment getmCurrentFragment() {
        return mCurrentFragment;
    }

    public String getCurrentSelectedFragment() {
        return mCurrentFragmentTag;
    }


    public void setEditProfileIcon() {
            mMenu.getItem(0).setIcon(R.drawable.ic_edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getLastFragmentFromStack() {
        int index = getSupportFragmentManager().getBackStackEntryCount() - 1;
        android.support.v4.app.FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(index);
        String previousTag = backEntry.getName();
        setmCurrentFragmentTag(previousTag);
        mCurrentFragment = getSupportFragmentManager().findFragmentByTag(previousTag);

        if (previousTag != null && previousTag.equals(HomeFragment.getHomeTag())) {
            setSelectedTab(R.id.home);
        }
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragmentTag.equals(FourFragment.getFourTag()) ||
                mCurrentFragmentTag.equals(SearchFragment.getSearchTag()) ||
                mCurrentFragmentTag.equals(ThreeFragment.getThreeTag()) ||
                mCurrentFragmentTag.equals(ProfileFragment.getProfileTag())) {
            getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
            setSelectedTab(R.id.home);
            return;
        }

        if (mCurrentFragment instanceof HomeFragment) {
            ((HomeFragment) mCurrentFragment).onFragmentBackPressed();
        } else {
            super.onBackPressed();
        }
//        getLastFragmentFromStack();
    }

    public void doBackPressed() {
        if (mDoubleBackToExitPressedOnce) {
            getSupportFragmentManager().popBackStackImmediate();
            super.onBackPressed();
            return;
        }
        mDoubleBackToExitPressedOnce = true;
//        mDashboardView.showExitSnackbar();
        Timer t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                mDoubleBackToExitPressedOnce = false;
            }
        }, 2500);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //mDashboardView.showCallAlertDialog();
        } else {
            //TODO: show snackbar with error
        }
    }

    public DashboardView getView() {
        return mDashboardView;
    }

}
