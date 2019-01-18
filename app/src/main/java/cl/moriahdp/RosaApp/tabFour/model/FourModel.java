package cl.moriahdp.RosaApp.tabFour.model;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.baseclasses.BaseModel;
import cl.moriahdp.RosaApp.repository.DataRepository;

public class FourModel extends BaseModel<DataRepository> {

    public FourModel(DataRepository repository, Bus bus) {
        super(repository, bus);
    }

}
