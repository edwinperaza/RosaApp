package cl.moriahdp.RosaApp.contact.model;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.baseclasses.BaseModel;
import cl.moriahdp.RosaApp.repository.DataRepository;

public class ContactModel extends BaseModel<DataRepository> {

    public ContactModel(DataRepository repository, Bus bus) {
        super(repository, bus);
    }
}
