package com.the313.playlistalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.util.Calendar;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexandrius.accordionswipelayout.library.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    AlarmManager manager;
    PendingIntent pendingIntent;
    TextView tv;

    AlarmAdapter alarmAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv=(TextView) findViewById(R.id.tv);

        RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        List<String> aBunchOfStrings= new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            //aBunchOfStrings.add(String .valueOf(Math.random()));
            aBunchOfStrings.add("Alarm " + i);
        }

        alarmAdapter= new AlarmAdapter(this,aBunchOfStrings);
        myRecyclerView.setAdapter(alarmAdapter);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));





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
        tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/DroidKufi-Regular.ttf"));


    }

    public void stopClick(View view) {
        manager.cancel(pendingIntent);
        tv.setText("Canceled");
    }

    public void cardClick(View view) {
        Intent in = new Intent(this,Main2Activity.class);
        startActivity(in);
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
    }


    public void alarmIconClick(View view) {
        ImageView icon = (ImageView) view;
        if (icon.getImageTintList()==myColors.myGrayList)
        icon.setImageTintList(myColors.myGreenList);
        else icon.setImageTintList(myColors.myGrayList);
    }
}
