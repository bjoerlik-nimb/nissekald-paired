package com.nimbit.nissekald_paired;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;

public class MobileJavaHelper {

    public static Context context;

    public void sendMessage() {
        CapabilityClient client = Wearable.getCapabilityClient(context);
        client.getCapability("wear", CapabilityClient.FILTER_ALL).addOnSuccessListener(capabilityInfo -> {
            Log.d("nissekald", "got caps");
            for (Node node : capabilityInfo.getNodes()) {
                Wearable.getMessageClient(context).sendMessage(node.getId(), "/message", "Heyson hey".getBytes());
            }
            showToast("Besked sendt");
        });
    }


    public void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        Vibrator vib = (Vibrator) context.getSystemService(Vibrator.class);
        vib.vibrate(VibrationEffect.createOneShot(200,1));
        Log.d("nissekald", "Toasting?");
    }
}
