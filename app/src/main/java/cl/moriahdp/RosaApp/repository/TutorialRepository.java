package cl.moriahdp.RosaApp.repository;

import android.content.Context;

public class TutorialRepository extends DataRepository {

    public static final String TUTORIAL_SHOWED = "tutorial_showed";

    public TutorialRepository(Context context, boolean tutorialShowed) {
        super(context);
        isTutorialShowed(tutorialShowed);
    }

    private void isTutorialShowed(boolean tutorialShowed) {
        tinyDB.putBoolean(TUTORIAL_SHOWED, tutorialShowed);
    }
}