package com.example.notify_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;
import androidx.core.app.RemoteInput;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import static com.example.notify_1.App.CHANNEL_1_ID;
import static com.example.notify_1.App.CHANNEL_2_ID;
import static com.example.notify_1.App.CHANNEL_3_ID;
import static com.example.notify_1.App.CHANNEL_4_ID;
import static com.example.notify_1.App.CHANNEL_5_ID;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_INTENT = 1;
    public static final String KEY_REMOTE_INPUT = "key_remote_input";
    public static final String USER_NAME = "ME";

    //NotificationManagerCompat used here to allow backwards compatibility.
    NotificationManagerCompat notificationManagerCompat;

    private EditText editTextTitle;
    private EditText editTextMessage;

    private MediaSessionCompat mediaSessionCompat;

    static List<Message> MESSAGES = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTitle = (EditText) findViewById(R.id.edit_text_title);
        editTextMessage = (EditText) findViewById(R.id.edit_text_message);

        mediaSessionCompat = new MediaSessionCompat(this, "TAG");
        notificationManagerCompat = NotificationManagerCompat.from(this);

        MESSAGES.add(new Message("Hello!!!", "JAM"));
        MESSAGES.add(new Message("Hello2!!!", "JAM"));
        MESSAGES.add(new Message("How r u??!!!", USER_NAME));
        MESSAGES.add(new Message("We r fine..U??", "HARRY"));
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
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
        notificationManagerCompat.notify(2, notification);
        //Can use different id for more of same notifications...as number of notifications>4
        // notification group would be formed by default for api>N
        /**for (int i = 0; i <5 ; i++) {
         SystemClock.sleep(2000);
         notificationManagerCompat.notify(i, notification);
         }**/
    }

    public void sendOnChannel3(View v) {
        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        String Title = editTextTitle.getText().toString();
        String Message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_3_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(Title)
                .setContentText(Message)
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null)) //so no icon in expanded state
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();
        notificationManagerCompat.notify(3, notification);
    }

    public void sendOnChannel4(View v) {
        Bitmap artWork = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        String Title = editTextTitle.getText().toString();
        String Message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_4_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(Title)
                .setContentText(Message)
                .setLargeIcon(artWork)
                .addAction(R.drawable.ic_dislike, "Dislike", null) //intent canb added here instead of null
                .addAction(R.drawable.ic_previous, "Previous", null)
                .addAction(R.drawable.ic_pause, "Pause", null)
                .addAction(R.drawable.ic_next, "Next", null)
                .addAction(R.drawable.ic_like, "Like", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3)
                        .setMediaSession(mediaSessionCompat.getSessionToken())) //gets media session control for notification.
                .setSubText("Sub Text")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        notificationManagerCompat.notify(4, notification);
    }

    public void sendOnChannel5(View v) {
        sendMessageChannel(this);
    }

    public static void sendMessageChannel(Context context) {

        //For opening activity
        Intent activityIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, NOTIFICATION_INTENT, activityIntent, 0);

        RemoteInput remoteInput = new RemoteInput.Builder(KEY_REMOTE_INPUT)
                .setLabel("Your reply....")
                .build();

        Intent replyIntent;
        PendingIntent replyPendingIntent = null;
        if (Build.VERSION.SDK_INT > -Build.VERSION_CODES.N) {
            replyIntent = new Intent(context, NotificationReceiver.class);
            replyPendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_INTENT, replyIntent, 0);
        } else {
            //start chat Activity instead(PendingIntent.getActivity)
            //cancel notification with notificationManagerCompat.cancel(id)
        }

        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                R.drawable.ic_next,
                "REPLY",
                replyPendingIntent
        ).addRemoteInput(remoteInput).build();

        Person user = new Person.Builder()
                .setName("ME")
                .build();
        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle(user);
        messagingStyle.setConversationTitle("GROUP CHAT");

        for (Message message : MESSAGES) {
            NotificationCompat.MessagingStyle.Message notification_message = new NotificationCompat.MessagingStyle.Message(
                    message.getText(),
                    message.getTimestamp(),
                    message.getSender()
            );
            messagingStyle.addMessage(notification_message);
        }

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_5_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setStyle(messagingStyle)
                .addAction(replyAction)
                .setColor(Color.BLUE)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(contentIntent)
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(5, notification);
    }

    public void sendOnChannel6(View v) {
        final int progressMax = 100;
        //THis allows us to change notification attributes.
        final NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle("Download")
                .setContentText("Download in progress")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setProgress(progressMax, 0, false);
        notificationManagerCompat.notify(6, notification.build());
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                for (int progress = 0; progress <= progressMax; progress += 10) {
                    notification.setProgress(progressMax, progress, false);
                    notificationManagerCompat.notify(6, notification.build());
                    SystemClock.sleep(1000);
                }
                notification.setContentText("Download finished")
                        .setProgress(0, 0, false)
                        .setOngoing(false);
                notificationManagerCompat.notify(6, notification.build());
            }
        }).start();
    }

    public void sendOnChannel7(View v) {
        //For lower level api<N
        String title1 = "Title 1";
        String message1 = "Message 1";
        String title2 = "Title 2";
        String message2 = "Message 2";
        Notification notification1 = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title1)
                .setContentText(message1)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setGroup("example_group")
                .build();
        Notification notification2 = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title2)
                .setContentText(message2)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setGroup("example_group")
                .build();
        Notification summaryNotification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine(title2 + " " + message2)
                        .addLine(title1 + " " + message1)
                        .setBigContentTitle("2 new messages")
                        .setSummaryText("user@example.com"))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setGroup("example_group")
                .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_CHILDREN)
                .setGroupSummary(true)
                .build();
        SystemClock.sleep(2000);
        notificationManagerCompat.notify(2, notification1);
        SystemClock.sleep(2000);
        notificationManagerCompat.notify(3, notification2);
        SystemClock.sleep(2000);
        notificationManagerCompat.notify(4, summaryNotification);
    }
}
