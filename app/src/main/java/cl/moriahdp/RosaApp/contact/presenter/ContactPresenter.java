package cl.moriahdp.RosaApp.contact.presenter;

import cl.moriahdp.RosaApp.baseclasses.BasePresenter;
import cl.moriahdp.RosaApp.contact.model.ContactModel;
import cl.moriahdp.RosaApp.contact.view.ContactView;

public class ContactPresenter extends BasePresenter<ContactModel, ContactView> {

    public ContactPresenter(ContactModel model, ContactView view) {
        super(model, view);
    }

}
