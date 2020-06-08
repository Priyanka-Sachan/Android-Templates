package com.example.notify_1;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class App extends Application {

    public static final String CHANNEL_1_ID="CHANNEL 1";
    public static final String CHANNEL_2_ID="CHANNEL 2";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID,
                        "Channel 1",
                        NotificationManager.IMPORTANCE_HIGH);
                channel1.setDescription("THIS IS CHANNEL 1.");

            NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW);
            channel2.setDescription("THIS IS CHANNEL 2.");


            //To register channels in api>=26
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
            notificationManager.createNotificationChannel(channel2);
        }
    }
}
