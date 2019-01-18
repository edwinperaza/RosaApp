package cl.moriahdp.RosaApp.profile.presenter;

import cl.moriahdp.RosaApp.baseclasses.BasePresenter;
import cl.moriahdp.RosaApp.profile.model.ProfileModel;
import cl.moriahdp.RosaApp.profile.view.ProfileView;

public class ProfilePresenter extends BasePresenter<ProfileModel, ProfileView> {

    public ProfilePresenter(ProfileModel model, ProfileView view) {
        super(model, view);
    }

}
