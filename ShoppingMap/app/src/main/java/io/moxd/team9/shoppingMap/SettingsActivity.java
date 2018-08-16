package io.moxd.team9.shoppingMap;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "my_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button sendPush = findViewById(R.id.buttonSendPush);
        Switch switch1 = findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(this);

        sendPush.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("preference_key", Context.MODE_PRIVATE);
        final boolean notification = sharedPreferences.getBoolean("notification", true);
        if(notification) {
            NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent resultIntent = new Intent(this, PlanActivity.class);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel nChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

                //Configure
                nChannel.setDescription("Geo Push Notification");
                nChannel.enableLights(true);
                nChannel.setLightColor(Color.CYAN);
                nm.createNotificationChannel(nChannel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle("Forum Gummersbach")
                    .setContentText("Nutze unsere Shopping Map!")
                    .setContentIntent(resultPendingIntent);

            nm.notify(NOTIFICATION_ID, builder.build());
        }
        else{
            Toast.makeText(this, "Notification off!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            //switchOnOff = true;
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("preference_key", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("notification", true);
            editor.commit();
        }
        else{
            //switchOnOff = false;
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("preference_key", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("notification", false);
            editor.commit();
        }
    }
}
