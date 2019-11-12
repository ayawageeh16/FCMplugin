package com.fcm.plugin.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.fcm.plugin.MainActivity;
import com.fcm.plugin.R;
import com.fcm.plugin.dao.SubscriptionClient;
import com.fcm.plugin.model.ResponseModel;
import com.fcm.plugin.model.TokenModel;
import com.fcm.plugin.repository.SubscriptionRepository;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static android.content.ContentValues.TAG;

public class MyFirebaseService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "100";

    public MyFirebaseService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {

            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            Log.d(TAG, " *** Message Notification Title: " + title);
            Log.d(TAG, " *** Message Notification Body: " + body);


            Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSound(defaultSound)
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_MAX);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(/*notification id*/1, builder.build());

        }

    }


    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        ResponseModel responseModel;
        TokenModel tokenModel = new TokenModel();
        tokenModel.setPlatform(1);
        tokenModel.setToken(token);

        responseModel = SubscriptionRepository.sendToken(getApplicationContext().getString(R.string.appID), tokenModel);

        if (responseModel != null ){
            Log.d(TAG, "SEND TOKEN RESULT: true" );
        }else{
            Log.d(TAG, "SEND TOKEN RESULT: false" );
        }


        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(token);
    }
}
