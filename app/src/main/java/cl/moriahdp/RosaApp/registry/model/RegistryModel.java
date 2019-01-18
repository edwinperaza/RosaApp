package cl.moriahdp.RosaApp.registry.model;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.baseclasses.BaseModel;
import cl.moriahdp.RosaApp.repository.DataRepository;

public class RegistryModel extends BaseModel<DataRepository> {

    public RegistryModel(DataRepository repository, Bus bus) {
        super(repository, bus);
    }
}
