package cl.moriahdp.RosaApp.tabThree.model;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.baseclasses.BaseModel;
import cl.moriahdp.RosaApp.repository.DataRepository;

public class ThreeModel extends BaseModel<DataRepository> {

    public ThreeModel(DataRepository repository, Bus bus) {
        super(repository, bus);
    }

}
