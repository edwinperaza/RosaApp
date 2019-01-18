package cl.moriahdp.RosaApp.profile.model;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.baseclasses.BaseModel;
import cl.moriahdp.RosaApp.repository.DataRepository;

public class ProfileModel extends BaseModel<DataRepository> {

    public ProfileModel(DataRepository repository, Bus bus) {
        super(repository, bus);
    }

}
