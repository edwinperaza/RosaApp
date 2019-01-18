package cl.moriahdp.RosaApp.utils.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cl.moriahdp.RosaApp.BaseApplication;
import cl.moriahdp.RosaApp.main.activities.DashboardActivity;
import cl.moriahdp.RosaApp.utils.TinyDB;


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                TinyDB tinyDB = new TinyDB(context);
                // Set the alarm here.
                NotificationScheduler.setReminder(context,
                        AlarmReceiver.class,
                        tinyDB.getInt("hour"),
                        tinyDB.getInt("min"));
                return;
            }
        }

        if (context != null) {
            NotificationScheduler.showNotification(context, DashboardActivity.class, "Titulo", "Mensaje");
        }

    }
}
