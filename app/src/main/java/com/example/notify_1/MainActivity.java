package com.example.notify_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.example.notify_1.App.CHANNEL_1_ID;
import static com.example.notify_1.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_INTENT = 1;

    //NotificationManagerCompat used here to allow backwards compatibility.
    NotificationManagerCompat notificationManagerCompat;
    private EditText editTextTitle;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTitle = (EditText) findViewById(R.id.edit_text_title);
        editTextMessage = (EditText) findViewById(R.id.edit_text_message);

        notificationManagerCompat = NotificationManagerCompat.from(this);
    }

    public void sendOnChannel1(View v) {
        String Title = editTextTitle.getText().toString();
        String Message = editTextMessage.getText().toString();

        //For opening activity
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, NOTIFICATION_INTENT, activityIntent, 0);

        //For actions
        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("NOTIFICATION_MESSAGE", Message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, NOTIFICATION_INTENT, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Converting image to bitmap
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.girl);

        //Summary text remains same but content title and text changes as we toggle.
        //For api<26,notification attributes should also be defined here.
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one) //mandatory
                .setContentTitle(Title)
                .setContentText(Message)
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.big_text))
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Summary Text"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher_round, "TOAST", actionIntent)
                .build();
        notificationManagerCompat.notify(1, notification);
    }

    public void sendOnChannel2(View v) {
        String Title = editTextTitle.getText().toString();
        String Message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(Title)
                .setContentText(Message)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("This is line 1.")
                        .addLine("This is line 1.")
                        .addLine("This is line 1.")
                        .addLine("This is line 1.")
                        .addLine("This is line 1.")
                        .addLine("This is line 1.")
                        .addLine("This is line 1.")
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Summary Text"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        notificationManagerCompat.notify(2, notification);
    }
}