package cl.moriahdp.RosaApp.search.model;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.baseclasses.BaseModel;
import cl.moriahdp.RosaApp.repository.DataRepository;

public class SearchModel extends BaseModel<DataRepository> {

    public SearchModel(DataRepository repository, Bus bus) {
        super(repository, bus);
    }
}
