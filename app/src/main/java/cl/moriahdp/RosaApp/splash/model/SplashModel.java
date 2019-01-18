package cl.moriahdp.RosaApp.splash.model;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.baseclasses.BaseModel;
import cl.moriahdp.RosaApp.repository.DataRepository;

public class SplashModel extends BaseModel<DataRepository> {

    private Handler uiHandler = new Handler(Looper.getMainLooper());
    private Runnable finalDialogRunnable = new Runnable() {
        @Override
        public void run() {
            bus.post(new TimeUpEvent());
        }
    };

    public SplashModel(DataRepository repository, Bus bus) {
        super(repository, bus);
    }

    public void startTimer() {
        uiHandler.postDelayed(finalDialogRunnable, 1500);
    }

    public void cancelTimer() {
        uiHandler.removeCallbacks(finalDialogRunnable);
    }

    /* Bus messages */
    public static class TimeUpEvent {
        /*Nothing to do here*/
    }
}
