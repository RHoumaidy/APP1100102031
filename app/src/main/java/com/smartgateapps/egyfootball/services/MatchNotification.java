package com.smartgateapps.egyfootball.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;

import com.smartgateapps.egyfootball.R;
import com.smartgateapps.egyfootball.activities.MainActivity;
import com.smartgateapps.egyfootball.egy.MyApplication;
import com.smartgateapps.egyfootball.model.Legue;
import com.smartgateapps.egyfootball.model.Match;

/**
 * Created by Raafat on 09/02/2016.
 */
public class MatchNotification extends IntentService {

    public MatchNotification() {
        super("AlarmService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        long mId = intent.getLongExtra("MATCH_ID", 0);
        Match match = Match.load(mId);
        sendNotification(match);


    }

    public void sendNotification(Match match) {
        String contentTitle = "بدء مباراة  " + Legue.load((long) match.getLeagueId()).get(0).getName();
        String contentText = match.getTeamL().getTeamName()+" X " + match.getTeamR().getTeamName();
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Notification builder = new Notification.Builder(this)
                .setContentTitle(contentTitle)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(contentText)
                .setLargeIcon(icon)
                .setStyle(new Notification.BigTextStyle())
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(MyApplication.APP_CTX,2,new Intent(MyApplication.APP_CTX,MainActivity.class),0))
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setVibrate(new long[]{0, 100, 500, 1000})
                .build();
        MyApplication.notificationManager.notify(match.getId().intValue(), builder);
    }
}
