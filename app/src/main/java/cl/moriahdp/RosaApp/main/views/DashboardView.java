package cl.moriahdp.RosaApp.main.views;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.FrameLayout;

import cl.moriahdp.RosaApp.R;
import cl.moriahdp.RosaApp.baseclasses.BaseActivity;
import cl.moriahdp.RosaApp.baseclasses.BaseActivityView;
import cl.moriahdp.RosaApp.baseclasses.BaseFragment;
import cl.moriahdp.RosaApp.contact.fragment.ContactFragment;
import cl.moriahdp.RosaApp.home.fragment.HomeFragment;
import cl.moriahdp.RosaApp.main.events.DashboardEvent;
import cl.moriahdp.RosaApp.search.fragment.SearchFragment;
import cl.moriahdp.RosaApp.tabFour.fragment.FourFragment;
import cl.moriahdp.RosaApp.tabThree.fragment.ThreeFragment;

public class DashboardView extends BaseActivityView {

    private static final String TAG = "DashboardView";

    FrameLayout tabContainer;
    BottomNavigationView navigation;

    public DashboardView(BaseActivity activity) {
        super(activity);
        tabContainer = activity.findViewById(R.id.tab_container);
        navigation = activity.findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.home);
        addBottomTabsBarListener();
    }

    @Override
    public void setDashboardEvent(BaseFragment baseFragment, String tag) {
        super.setDashboardEvent(baseFragment, tag);
    }

    private void addBottomTabsBarListener() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        if (getDashboardEvent() != null) {
                            sendDashboardEvent(getDashboardEvent());
                            cleanDashboardEvent();
                        } else {
                            sendDashboardEvent(HomeFragment.newInstance(), HomeFragment.getHomeTag());
                        }
                        return true;
                    case R.id.search:
                        if (getDashboardEvent() != null) {
                            sendDashboardEvent(getDashboardEvent());
                            cleanDashboardEvent();
                        } else {
                            sendDashboardEvent(SearchFragment.newInstance(), SearchFragment.getSearchTag());
                        }
                        return true;
                    case R.id.favorites:
                        if (getDashboardEvent() != null) {
                            sendDashboardEvent(getDashboardEvent());
                            cleanDashboardEvent();
                        } else {
                            sendDashboardEvent(ThreeFragment.newInstance(), ThreeFragment.getThreeTag());
                        }
                        return true;
                    case R.id.connect:
                        if (getDashboardEvent() != null) {
                            sendDashboardEvent(getDashboardEvent());
                            cleanDashboardEvent();
                        } else {
                            sendDashboardEvent(FourFragment.newInstance(), FourFragment.getFourTag());
                        }
                        return true;
                    case R.id.profile:
                        if (getDashboardEvent() != null) {
                            sendDashboardEvent(getDashboardEvent());
                            cleanDashboardEvent();
                        } else {
                            sendDashboardEvent(ContactFragment.newInstance(), ContactFragment.getContactTag());
                        }
                        return true;
                }
                return false;
            }
        });

//        mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
//            @Override
//            public void onTabReSelected(@IdRes int tabId) {
//
//                switch (tabId) {
//                    case R.id.home:
//                        if (!((DashboardActivity) activityRef.get()).getmCurrentFragmentTag().equals(HomeFragment.getHomeTag())) {
//                            activityRef.get().getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
//                            sendDashboardEvent(HomeFragment.newInstance(), HomeFragment.getHomeTag());
//                        }
//                        break;
//
//                    case R.id.search:
//                        if (!((DashboardActivity) activityRef.get()).getmCurrentFragmentTag().equals(SearchFragment.getSearchTag())) {
//                            activityRef.get().getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
//                            sendDashboardEvent(SearchFragment.newInstance(), SearchFragment.getSearchTag());
//                        }
//                        break;
//
//                    case R.id.connect:
//                        if (!((DashboardActivity) activityRef.get()).getmCurrentFragmentTag().equals(ConnectFragment.getFaithTag())) {
//                            activityRef.get().getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
//                            sendDashboardEvent(ConnectFragment.newInstance(), ConnectFragment.getFaithTag());
//                        }
//                        break;
//
//                    case R.id.favorites:
//                        if (!((DashboardActivity) activityRef.get()).getmCurrentFragmentTag().equals(FourFragment.getFourTag())) {
//                            activityRef.get().getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
//                            sendDashboardEvent(FourFragment.newInstance(), FourFragment.getFourTag());
//                        }
//                        break;
//
//                    case R.id.profile:
//                        if (!((DashboardActivity) activityRef.get()).getmCurrentFragmentTag().equals(ContactFragment.getContactTag())) {
//                            activityRef.get().getSupportFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
//                            sendDashboardEvent(ContactFragment.newInstance(), ContactFragment.getContactTag());
//                        }
//                        break;
//                }
//            }
//        });
    }

    public void setSelectedTab(int tabId) {
        navigation.setSelectedItemId(tabId);
    }

    public boolean checkCurrentTabSelected(int tabId) {
        return tabId == navigation.getSelectedItemId();
    }

    public BottomNavigationView getNavigation() {
        return navigation;
    }

    private void sendDashboardEvent(BaseFragment baseFragment, String tag) {
        bus.post(new DashboardEvent(baseFragment, R.id.tab_container, tag));
    }

    private void sendDashboardEvent(DashboardEvent dashboardEvent) {
        bus.post(dashboardEvent);
    }
}