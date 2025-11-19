package com.nimbit.nissekald_paired.presentation;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

public class WatchJavaHelper {

    private static Vibrator vibrator = null;
    public static Context context;
    public static MessageViewModel messageViewModel;

    public WatchJavaHelper() {
        if (context != null) {
            vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        }
    }

    public void vibrate() {
        Log.d("nisseur", "support?");

        //long[] timings = new long[] { 1000, 2000, 5000, 1000 };
        //int[] amplitudes = new int[] { 255, 0, 255, 0 };
        long[] timings = new long[] { 50, 50, 100, 50, 50 };
        int[] amplitudes = new int[] { 64, 128, 255, 128, 64 };
        int repeat = 0; // Repeat from the second entry, index = 1.
        VibrationEffect repeatingEffect = VibrationEffect.createWaveform(
                timings, amplitudes, repeat);
        if (vibrator != null) {
            vibrator.vibrate(repeatingEffect);
        }
    }

    public void stop() {
        if (vibrator != null) {
            vibrator.cancel();
        }
    }
}
