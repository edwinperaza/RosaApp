package cl.moriahdp.RosaApp.search.view;

import android.view.View;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.baseclasses.BaseFragmentView;
import cl.moriahdp.RosaApp.search.fragment.SearchFragment;

public class SearchView extends BaseFragmentView {

    private SearchFragment fragment;

    public SearchView(SearchFragment fragment, View rootView, Bus bus) {
        super(fragment, rootView, bus);
        this.fragment = fragment;
        showLoadingOverlay();
    }
}
