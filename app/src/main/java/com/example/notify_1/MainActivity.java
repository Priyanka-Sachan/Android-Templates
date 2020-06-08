package com.example.notify_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.example.notify_1.App.CHANNEL_1_ID;
import static com.example.notify_1.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {

    //NotificationManagerCompat used here to allow backwards compatibility.
    NotificationManagerCompat notificationManagerCompat;
    private EditText editTextTitle;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTitle=(EditText)findViewById(R.id.edit_text_title);
        editTextMessage=(EditText)findViewById(R.id.edit_text_message);

        notificationManagerCompat=NotificationManagerCompat.from(this);

    }
    public  void sendOnChannel1(View v){
        String Title=editTextTitle.getText().toString();
        String Message=editTextMessage.getText().toString();

        //For api<26,notification attributes should also be defined here.
        Notification notification=new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(Title)
                .setContentText(Message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        notificationManagerCompat.notify(1,notification);
    }
    public  void sendOnChannel2(View v){
        String Title=editTextTitle.getText().toString();
        String Message=editTextMessage.getText().toString();

        Notification notification=new NotificationCompat.Builder(this,CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(Title)
                .setContentText(Message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        notificationManagerCompat.notify(2,notification);
    }
}