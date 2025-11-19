package com.nimbit.nissekald_paired.presentation;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import androidx.annotation.NonNull;

public class WatchDataLayerListenerService extends WearableListenerService {

    public static String message = "start";

    @Override
    public void onMessageReceived(@NonNull MessageEvent messageEvent) {
        super.onMessageReceived(messageEvent);
        message = new String(messageEvent.getData());
        Log.d("nissekald", "message received");
        showApp(message);
        WatchJavaHelper.messageViewModel.updateMessage(message);
        new WatchJavaHelper().vibrate();
    }

    private void showApp(String message) {
        Intent showIntent = new Intent(getApplicationContext(), WatchMainActivity.class);
        showIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        showIntent.setAction(Intent.ACTION_MAIN);
        showIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        if (message != null) {
            showIntent.putExtra("message", message);
        }
        getApplicationContext().startActivity(showIntent);
    }
}
