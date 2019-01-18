package cl.moriahdp.RosaApp.home.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.otto.Bus;

import java.util.List;

import cl.moriahdp.RosaApp.R;
import cl.moriahdp.RosaApp.baseclasses.BaseFragmentView;
import cl.moriahdp.RosaApp.home.adapter.HomeAdapter;
import cl.moriahdp.RosaApp.home.fragment.HomeFragment;
import cl.moriahdp.RosaApp.home.modelObject.HomeModelObject;
import cl.moriahdp.RosaApp.homeDetail.fragment.HomeDetailFragment;
import cl.moriahdp.RosaApp.main.activities.DashboardActivity;
import cl.moriahdp.RosaApp.utils.recyclerListener.RecyclerOnItemClickListener;


public class HomeView extends BaseFragmentView {

    private RecyclerView rvHome;
    private HomeFragment fragment;
    private HomeAdapter adapter = new HomeAdapter();

    public HomeView(HomeFragment fragment, View rootView, final Bus bus) {
        super(fragment, rootView, bus);
        this.fragment = fragment;
        showLoadingOverlay();
        rvHome = rootView.findViewById(R.id.rv_sermons);
        LinearLayoutManager llm = new LinearLayoutManager(fragment.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvHome.setLayoutManager(llm);
        rvHome.setAdapter(adapter);
        adapter.setListener(new RecyclerOnItemClickListener<HomeModelObject>() {
            @Override
            public void onItemClickListener(HomeModelObject item) {
                bus.post(new onItemClickListener(item));
            }
        });

    }

    public void serRecycler(List<HomeModelObject> sermons) {
        if (adapter != null) {
            adapter.setHomeModelObjectList(sermons);
            hideLoadingOverlay();
        }
    }

    public void goToSermonDetailFragment(HomeModelObject sermon) {
        DashboardActivity dashboardActivity = ((DashboardActivity) fragment.getActivity());
        if (dashboardActivity != null) {
            dashboardActivity.goToFragmentWithStack(
                    R.id.tab_container,
                    HomeDetailFragment.newInstance(sermon),
                    HomeDetailFragment.getSermonDetailTag()
            );
        }
    }

    public static class onItemClickListener {
        private HomeModelObject homeItemList;

        public onItemClickListener(HomeModelObject homeItemList) {
            this.homeItemList = homeItemList;
        }

        public HomeModelObject getHomeItemList() {
            return homeItemList;
        }
    }
}
