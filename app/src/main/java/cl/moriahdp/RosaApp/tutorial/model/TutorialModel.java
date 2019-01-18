package cl.moriahdp.RosaApp.tutorial.model;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.baseclasses.BaseModel;
import cl.moriahdp.RosaApp.repository.DataRepository;

public class TutorialModel extends BaseModel<DataRepository> {

    public TutorialModel(DataRepository repository, Bus bus) {
        super(repository, bus);
    }
}
