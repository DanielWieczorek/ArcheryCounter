package io.dwi.archerycounter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationIntentReceiver extends BroadcastReceiver {
    public static final String INCREMENT_INTENT ="increment";
    private UiDataManager uiDataManager;

    public NotificationIntentReceiver() {
        uiDataManager = UiDataManager.getInstance();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(INCREMENT_INTENT)) {
                uiDataManager.addShots(1);
                Log.d("Xooo", "lololololol");
            }
    }
}
