package cl.moriahdp.RosaApp.splash.view;

import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.R;
import cl.moriahdp.RosaApp.baseclasses.BaseActivity;
import cl.moriahdp.RosaApp.baseclasses.BaseActivityView;

public class SplashView extends BaseActivityView {

    private ConstraintLayout containerLayout;

    public SplashView(BaseActivity activity, Bus bus) {
        super(activity, bus);
        containerLayout = activity.findViewById(R.id.container_splash);

        containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDashBoard();
            }
        });
    }

}
