package cl.moriahdp.RosaApp.contact.view;

import android.view.View;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.baseclasses.BaseFragmentView;
import cl.moriahdp.RosaApp.contact.fragment.ContactFragment;

public class ContactView extends BaseFragmentView {

    public ContactView(ContactFragment fragment, View rootView, Bus bus) {
        super(fragment, rootView, bus);
    }

}
