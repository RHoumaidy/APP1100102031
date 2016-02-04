package com.smartgateapps.egyfootball.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.smartgateapps.egyfootball.R;
import com.smartgateapps.egyfootball.activities.NewsListFragmentBackground;
import com.smartgateapps.egyfootball.egy.MyApplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Raafat on 11/01/2016.
 */
public class GetAllDawriNewsReciever extends WakefulBroadcastReceiver {

    private NewsListFragmentBackground newsListFragment1 = new NewsListFragmentBackground();
    private NewsListFragmentBackground newsListFragment2 = new NewsListFragmentBackground();
    private NewsListFragmentBackground newsListFragment3 = new NewsListFragmentBackground();

    @Override
    public void onReceive(Context context, Intent intent) {

//        Toast.makeText(context,"started",Toast.LENGTH_LONG).show();

        Intent intentActivationUpateNewsService = new Intent(MyApplication.ACTION_ACTIVATION);

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(MyApplication.APP_CTX, 0, intentActivationUpateNewsService, PendingIntent.FLAG_UPDATE_CURRENT);

        MyApplication.alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 60 * 10 * 1000, pendingIntent);

        newsListFragment1.urlExtention = MyApplication.EGY_EXT_HOME;
        newsListFragment1.leaguId = 0;
        newsListFragment1.pageIdx = 1;
        newsListFragment1.isLeague = true;

        newsListFragment2.urlExtention = MyApplication.ALMASRI_LEAGUE_NEWS_EXT;
        newsListFragment2.leaguId = 1;
        newsListFragment2.pageIdx = 1;
        newsListFragment2.isLeague=true;

        newsListFragment3.urlExtention = MyApplication.ALMASRI_CUP_NEWS_EXT;
        newsListFragment3.leaguId = 2;
        newsListFragment3.pageIdx = 1;
        newsListFragment3.isLeague = true;


        newsListFragment1.featchData();
        newsListFragment2.featchData();
        newsListFragment3.featchData();


        Set<String> selectedLeagues = new HashSet<>();
        if (MyApplication.pref.getBoolean(MyApplication.APP_CTX.getString(R.string.masri_league_notificatin_pref_key), false))
            selectedLeagues.add("1");
        if (MyApplication.pref.getBoolean(MyApplication.APP_CTX.getString(R.string.masri_cup_notification_pref_key), false))
            selectedLeagues.add("2");

        MyApplication.pref.edit()
                .putStringSet(MyApplication.APP_CTX.getString(R.string.selected_leagues_pref_key), selectedLeagues)
                .commit();

        if (selectedLeagues.size() > 0) {
            Intent toNotification = new Intent(context, NotificationService.class);
            startWakefulService(context, toNotification);
        }

        completeWakefulIntent(intent);

    }


}
