package com.example.notify_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.RemoteInput;

import static com.example.notify_1.MainActivity.KEY_REMOTE_INPUT;
import static com.example.notify_1.MainActivity.USER_NAME;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

//         For Channel-1 Broadcast
//        String message=intent.getStringExtra("NOTIFICATION_MESSAGE");
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        //For Channel-5
        Bundle bundle=RemoteInput.getResultsFromIntent(intent);
        if(bundle!=null){
            //Should actually use extractMessagingStyleFromNotification() or use database..
            CharSequence replyText=bundle.getCharSequence(KEY_REMOTE_INPUT);
            Message reply=new Message(replyText,USER_NAME);
            MainActivity.MESSAGES.add(reply);

            MainActivity.sendMessageChannel(context);
        }
    }
}
