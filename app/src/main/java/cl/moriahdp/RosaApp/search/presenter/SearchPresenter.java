package cl.moriahdp.RosaApp.search.presenter;

import cl.moriahdp.RosaApp.baseclasses.BasePresenter;
import cl.moriahdp.RosaApp.search.model.SearchModel;
import cl.moriahdp.RosaApp.search.view.SearchView;

public class SearchPresenter extends BasePresenter<SearchModel, SearchView> {

    public SearchPresenter(SearchModel model, SearchView view) {
        super(model, view);
    }

}
