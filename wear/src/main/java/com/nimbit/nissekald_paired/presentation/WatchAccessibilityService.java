package com.nimbit.nissekald_paired.presentation;

import android.accessibilityservice.AccessibilityService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.accessibility.AccessibilityEvent;

public class WatchAccessibilityService extends AccessibilityService {

    @Override
    protected void onServiceConnected() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Intent showIntent = new Intent(getApplicationContext(), WatchMainActivity.class);
                showIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                showIntent.setAction(Intent.ACTION_MAIN);
                showIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                getApplicationContext().startActivity(showIntent);
            }
        };
        getApplicationContext().registerReceiver(receiver, filter);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

    }

    @Override
    public void onInterrupt() {

    }
}
