package com.the313.playlistalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    AlarmManager manager;
    PendingIntent pendingIntent;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView) findViewById(R.id.tv);


    }

    public void startClick(View view) {
        Intent intent;
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        intent=new Intent(MainActivity.this, AlarmBroadcastReceiver.class);
        pendingIntent= PendingIntent.getBroadcast(this,0,intent,0);
        manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+3000,pendingIntent);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 48);
        calendar.set(Calendar.SECOND, 00);

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

        tv.setText("Alarm set on " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":00");

    }

    public void stopClick(View view) {
        manager.cancel(pendingIntent);
        tv.setText("Canceled");
    }

}
