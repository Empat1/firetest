package com.example.firetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushFirebaseService extends FirebaseMessagingService {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        String title = message.getNotification().getTitle();
        String text = message.getNotification().getBody();
        final String CHANNEL_ID = "HEARS_UP";
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID , "HY", NotificationManager.IMPORTANCE_HIGH);

        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notufucation = new Notification.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.profile)
                .setContentTitle(title)
                .setContentText(text);

        NotificationManagerCompat.from(this).notify(1 , notufucation.build());

        Log.i("TAG" , title + "\n"  + text);
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.i("TAG" , token);

    }
}
