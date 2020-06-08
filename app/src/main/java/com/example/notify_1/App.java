package com.example.notify_1;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class App extends Application {

    public static final String CHANNEL_1_ID="CHANNEL 1";
    public static final String CHANNEL_2_ID="CHANNEL 2";
    public static final String CHANNEL_3_ID="CHANNEL 3";
    public static final String CHANNEL_4_ID="CHANNEL 4";

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

            NotificationChannel channel3 = new NotificationChannel(CHANNEL_3_ID,
                    "Channel 3",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel2.setDescription("THIS IS CHANNEL 3.");

            NotificationChannel channel4 = new NotificationChannel(CHANNEL_4_ID,
                    "Channel 4",
                    NotificationManager.IMPORTANCE_LOW);
            channel2.setDescription("THIS IS CHANNEL 4.");


            //To register channels in api>=26
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
            notificationManager.createNotificationChannel(channel2);
            notificationManager.createNotificationChannel(channel3);
            notificationManager.createNotificationChannel(channel4);
        }
    }
}
