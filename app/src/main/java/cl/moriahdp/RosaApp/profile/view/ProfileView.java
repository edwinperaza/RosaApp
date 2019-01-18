package cl.moriahdp.RosaApp.profile.view;

import android.view.View;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.baseclasses.BaseFragmentView;
import cl.moriahdp.RosaApp.profile.fragment.ProfileFragment;

public class ProfileView extends BaseFragmentView {

    public ProfileView(ProfileFragment fragment, View rootView, Bus bus) {
        super(fragment, rootView, bus);
    }
}