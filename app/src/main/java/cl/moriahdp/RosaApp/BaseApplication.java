package cl.moriahdp.RosaApp;

import android.app.Application;

public class BaseApplication extends Application {

    private static BaseApplication instance;

    public BaseApplication() {
        instance = this;
    }

    public static BaseApplication getInstance() {
        if (instance == null) {
            instance = new BaseApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
