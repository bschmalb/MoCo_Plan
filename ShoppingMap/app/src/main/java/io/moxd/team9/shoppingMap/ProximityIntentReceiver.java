package io.moxd.team9.shoppingMap;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;




class ProximityIntentReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "my_notification_channel";

    @Override
    public void onReceive(Context context, Intent intent) {

        String key = LocationManager.KEY_PROXIMITY_ENTERING;
        Boolean entering = intent.getBooleanExtra(key, false);

        SharedPreferences sharedPreferences = context.getSharedPreferences("preference_key", Context.MODE_PRIVATE);
        final boolean notification = sharedPreferences.getBoolean("notification", true);

        if (entering &&notification ) {

            //Push Notification
            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent resultIntent = new Intent(context, PlanActivity.class);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel nChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

                //Configure
                nChannel.setDescription("Geo Push Notification");
                nChannel.enableLights(true);
                nChannel.setLightColor(Color.CYAN);
                nm.createNotificationChannel(nChannel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle("Forum Gummersbach")
                    .setContentText(context.getString(R.string.notification))
                    .setContentIntent(resultPendingIntent);

            nm.notify(NOTIFICATION_ID, builder.build());

            //Info
            Log.d(getClass().getSimpleName(), "entering");
        } else {
            Log.d(getClass().getSimpleName(), "exiting");
        }
    }
}
