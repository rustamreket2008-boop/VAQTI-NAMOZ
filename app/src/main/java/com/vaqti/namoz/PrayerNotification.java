
package com.vaqti.namoz;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class PrayerNotification {

    public static final String CHANNEL_ID = "prayer_times";

    public static void createChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Огоҳии вақти намоз",
                    NotificationManager.IMPORTANCE_HIGH
            );

            channel.setDescription("Огоҳии вақти намоз");

            NotificationManager manager =
                    (NotificationManager) context.getSystemService(
                            Context.NOTIFICATION_SERVICE
                    );

            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }
}
