package com.jianjiaoproduction.youremoticons.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * This class provides the initialization support for the app
 * Created by zhibzhang on 6/2/2016.
 */
public class InitManager {
    /**
     * The User need to pass the context to create the InitManager
     * @param context Activity Context that used to get the SharedPreferences
     */
    public InitManager(Context context){
        this.context = context;
    }
    public enum InitStat {FIRST_TIME, NEW_VERSION, NORMAL;}

    public InitStat getInitState(){
        InitStat initStat = InitStat.NORMAL;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        PackageInfo packageInfo;

        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            int lastVersion = sharedPreferences.getInt(LAST_VERSION, -1);
            int currVersion = packageInfo.versionCode;
            initStat = getInitState(currVersion, lastVersion);
            //update sharedPreferences with the current version
            sharedPreferences.edit().putInt(LAST_VERSION, currVersion).commit();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "Cannot get the init status of the app");
        }

        return initStat;

    }

    private InitStat getInitState(int currVersion, int lastVersion){
        if(lastVersion == -1)
            return InitStat.FIRST_TIME;
        else if(currVersion == lastVersion)
            return InitStat.NORMAL;
        else if(currVersion > lastVersion)
            return InitStat.NEW_VERSION;
        else {
            //current version is older than the last version
            //return normal for now
            return InitStat.NORMAL;
        }
    }

    private Context context;
    private static final String LAST_VERSION = "last_app_version";
    private static final String LOG_TAG = "Emoticon_Init_Manager";
}
