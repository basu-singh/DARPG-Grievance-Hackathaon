package com.basusingh.mygrievance.Utils;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        //TODO sendRegistrationToServer(token);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            //TODO handleNow();
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
// then you use
        prefs.getBoolean("keystring", true);

    }

}
