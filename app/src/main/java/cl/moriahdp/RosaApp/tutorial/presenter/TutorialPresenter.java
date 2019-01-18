package cl.moriahdp.RosaApp.tutorial.presenter;

import cl.moriahdp.RosaApp.baseclasses.BasePresenter;
import cl.moriahdp.RosaApp.tutorial.model.TutorialModel;
import cl.moriahdp.RosaApp.tutorial.view.TutorialView;

public class TutorialPresenter extends BasePresenter<TutorialModel, TutorialView> {

    public TutorialPresenter(TutorialModel model, TutorialView view) {
        super(model, view);
    }
}
