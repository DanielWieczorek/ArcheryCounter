package io.dwi.archerycounter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.widget.RemoteViews;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.ArrayList;
import java.util.List;

public class TrainingDayView extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_day_view);
        JodaTimeAndroid.init(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager(viewPager);

        //tabLayout = (TabLayout) findViewById(R.id.tabs);
       // tabLayout.setupWithViewPager(viewPager);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.update_shot_count_notification);

        Intent resultIntent = new Intent();
        resultIntent.setAction(NotificationIntentReceiver.INCREMENT_INTENT);
        // Creates the PendingIntent
        PendingIntent notifyPendingIntent =
                PendingIntent.getBroadcast(
                        this,
                        0,
                        resultIntent,
                        0
                );

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_focused)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setContent(remoteViews);
        remoteViews.setOnClickPendingIntent(R.id.incrementButton,notifyPendingIntent);
// Puts the PendingIntent into the notification builder
           mBuilder.setContentIntent(notifyPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
    //    NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
    //    mBuilder.setStyle(inboxStyle);
        mBuilder.setVisibility(Notification.VISIBILITY_PUBLIC);
        mNotificationManager.notify(0, mBuilder.build());
    }

    private void setupViewPager(ViewPager viewPager) {
        TrainingDayStatisticsAdapter adapter = new  TrainingDayStatisticsAdapter(getSupportFragmentManager(), toolbar);
        viewPager.setAdapter(adapter);
    }

}
