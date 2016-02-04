package com.smartgateapps.egyfootball.egy;

import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.webkit.WebView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.smartgateapps.egyfootball.R;
import com.smartgateapps.egyfootball.model.Legue;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Raafat on 04/11/2015.
 */
public class MyApplication extends Application {


    public static AlarmManager alarmManager;
    public static SharedPreferences pref;
    public static DbHelper dbHelper;
    public static SQLiteDatabase dbw, dbr;

    public static int pageSize = 15;

    public static final String BASE_URL = "http://m.kooora.com/";
    public static final String EGY_EXT_HOME = "?n=0&o=nceg&pg=";
    public static final String ALMASRI_LEAGUE_EXT = "?c=11580";
    public static final String ALMASRI_CUP_EXT = "?c=11916";
    public static final String ALMASRI_LEAGUE_NEWS_EXT = "?n=0&o=n11580&pg=";
    public static final String ALMASRI_CUP_NEWS_EXT = "?n=0&o=n11916&pg=";
    public static final String TEAM_NEWS_EXT = "?n=0&o=n1000000";
    public static final String TEAM_MATCHES_EXT = "?region=-6&team=";

    public static final String TEAMS_CM = "&cm=t";
    public static final String POSES_CM = "&cm=i";
    public static final String MATCHES_CM = "&cm=m";
    public static final String SCORERS_CM = "&scorers=true";

    public static Context APP_CTX;
    public static final String LIVE_CAST_APP_PACKAGE_NAME = "com.smartgateapps.livesport";

    public static Picasso picasso;
    public static WebView webView;

    public static final int HEADER_TYPE_GOALERS = 0;

    public static String[] PLAYERS_POS = new String[]{"", "مدرب", "حارس", "دفاع", "وسط", "هجوم", "مساعد مدرب"};
    public static HashMap<String, Integer> monthOfTheYear = new HashMap<>(12);

    public static MyApplication instance;

    public static HashMap<Integer, Integer> teamsLogos = new HashMap<>();

    public static String ACTION_ACTIVATION = "ACTION_ACTIVATION";
    public static NotificationManager notificationManager;

    public static SimpleDateFormat sourceDF = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat destDF = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat sourceDateFormat = new SimpleDateFormat("E d MMMM yyy", new Locale("ar"));
    public static SimpleDateFormat destDateFormat = new SimpleDateFormat("E d MMMM yyy", new Locale("ar"));

    public static TimeZone currentTimeZone;

    public static InterstitialAd mInterstitialAd;

    public static String converteTime(String time, String date) throws ParseException {
        try {

            long dated = 0;
            if (date != "" && date != null)
                dated = sourceDateFormat.parse(date).getTime();
            long timed = 0;
            if (time != "" && time != null)
                timed = sourceDF.parse(time).getTime();
            long dateTime = timed + dated;
            return destDF.format(dateTime);

        } catch (Exception e) {
            return "";
        }
    }

    public static String converteDate(String time, String date) throws ParseException {
        try {

            long dated = 0;
            if (date != "" && date != null)
                dated = sourceDateFormat.parse(date).getTime();
            long timed = 0;
            if (time != "" && time != null)
                timed = sourceDF.parse(time).getTime();
            long dateTime = timed + dated;
            return destDateFormat.format(dateTime);
        } catch (Exception e) {
            return "";
        }

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                requestNewInterstitial();
            }
        });
        requestNewInterstitial();

        currentTimeZone = TimeZone.getDefault();
        sourceDF.setTimeZone(TimeZone.getTimeZone("UTC"));
        destDF.setTimeZone(currentTimeZone);
        sourceDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        destDateFormat.setTimeZone(currentTimeZone);

        teamsLogos.put(11414, R.mipmap.t11414);
        teamsLogos.put(12169, R.mipmap.t12169);
        teamsLogos.put(12708, R.mipmap.t12708);
        teamsLogos.put(130, R.mipmap.t130);
        teamsLogos.put(131, R.mipmap.t131);
        teamsLogos.put(132, R.mipmap.t132);
        teamsLogos.put(133, R.mipmap.t133);
        teamsLogos.put(134, R.mipmap.t134);
        teamsLogos.put(136, R.mipmap.t136);
        teamsLogos.put(137, R.mipmap.t137);
        teamsLogos.put(138, R.mipmap.t138);
        teamsLogos.put(139, R.mipmap.t139);
        teamsLogos.put(140, R.mipmap.t140);
        teamsLogos.put(141, R.mipmap.t141);
        teamsLogos.put(142, R.mipmap.t142);
        teamsLogos.put(143, R.mipmap.t143);
        teamsLogos.put(1928, R.mipmap.t1928);
        teamsLogos.put(1931, R.mipmap.t1931);
        teamsLogos.put(1932, R.mipmap.t1932);
        teamsLogos.put(24405, R.mipmap.t24405);
        teamsLogos.put(5048, R.mipmap.t5048);
        teamsLogos.put(5606, R.mipmap.t5606);
        teamsLogos.put(5662, R.mipmap.t5662);
        teamsLogos.put(5795, R.mipmap.t5795);
        teamsLogos.put(5806, R.mipmap.t5806);
        teamsLogos.put(5898, R.mipmap.t5898);
        teamsLogos.put(5899, R.mipmap.t5899);
        teamsLogos.put(5906, R.mipmap.t5906);
        teamsLogos.put(594, R.mipmap.t594);
        teamsLogos.put(7825, R.mipmap.t7825);
        teamsLogos.put(7917, R.mipmap.t7917);

        APP_CTX = getApplicationContext();

        dbHelper = new DbHelper(APP_CTX);
        dbw = dbHelper.getWritableDatabase();
        dbr = dbHelper.getReadableDatabase();

        picasso = Picasso.with(this);

        Legue egy = new Legue(0, "اخبار مصر");
        Legue masriLeague = new Legue(1, "الدوري المصري");
        Legue masriCup = new Legue(2, "كأس مصر");

        egy.save();
        masriLeague.save();
        masriCup.save();

        pref = PreferenceManager.getDefaultSharedPreferences(MyApplication.APP_CTX);
        boolean b = pref.getBoolean(getString(R.string.masri_league_notificatin_pref_key),true);
        pref.edit().putBoolean(getString(R.string.masri_league_notificatin_pref_key),b).apply();
        notificationManager = (NotificationManager) APP_CTX.getSystemService(NOTIFICATION_SERVICE);

        alarmManager = (AlarmManager) APP_CTX.getSystemService(ALARM_SERVICE);


        monthOfTheYear.put("يناير", 1);
        monthOfTheYear.put("فبراير", 2);
        monthOfTheYear.put("مارس", 3);
        monthOfTheYear.put("أبريل", 4);
        monthOfTheYear.put("مايو", 5);
        monthOfTheYear.put("يونيو", 6);
        monthOfTheYear.put("يوليو", 7);
        monthOfTheYear.put("أغسطس", 8);
        monthOfTheYear.put("سبتمبر", 9);
        monthOfTheYear.put("أكتوبر", 10);
        monthOfTheYear.put("نوفمبر", 11);
        monthOfTheYear.put("ديسمبر", 12);

        //Parse.enableLocalDatastore(this);
        Parse.initialize(this, "1qcCTaWXVfDvwgktZW1Y24z4oMRbYCRO6ZK9Lph7", "gUA5UUCBOrJKouYmYIawDQbFuHVu0KsAgxdKtbzM");
        ParseInstallation.getCurrentInstallation().saveInBackground();



    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }




    public static void openPlayStor(String appPackageName){
        try {
            APP_CTX.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(appPackageName)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        catch (android.content.ActivityNotFoundException anfe) {
            APP_CTX.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }



}