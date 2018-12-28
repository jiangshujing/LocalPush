package com.example.jiangshujing.localpush;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import static android.content.Context.ALARM_SERVICE;

/**
 * 闹钟定时工具类
 */

public class AlarmTimerUtil {

    private static final String TAG = "AlarmTimerUtil";

    /**
     * 设置定时闹钟
     *
     * @param context
     * @param alarmId
     * @param action
     * @param map     要传递的参数
     */
    public static void setAlarmTimer(Context context, int alarmId, long time, String action, Map<String, Serializable> map) {
//        Intent intent = new Intent(context, AlarmReceiver.class);
        Intent intent = new Intent();
        intent.setAction(action);//广播的 action

        //传递参数
        if (map != null) {
            for (String key : map.keySet()) {
                Log.i(TAG, "setAlarmTimer key ::" + key);
                intent.putExtra(key, map.get(key));
            }
        }

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
//        PendingIntent sender = PendingIntent.getBroadcast(context, alarmId, intent, 0);//如果是广播，就这么写
        PendingIntent sender = PendingIntent.getService(context, alarmId, intent, 0);

//        Intent timeTaskIntent = new Intent(context, AlarmReceiver.class);
//        PendingIntent sender = PendingIntent.getBroadcast(context, alarmId, timeTaskIntent, PendingIntent.FLAG_CANCEL_CURRENT);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //MARSHMALLOW OR ABOVE
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, sender);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //LOLLIPOP 21 OR ABOVE
            AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(time, sender);
            alarmManager.setAlarmClock(alarmClockInfo, sender);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //KITKAT 19 OR ABOVE
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, sender);
        } else { //FOR BELOW KITKAT ALL DEVICES
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, sender);
        }
    }


    /**
     * 取消闹钟
     *
     * @param context
     * @param action
     */
    public static void cancelAlarmTimer(Context context, String action, int alarmId) {
        Intent myIntent = new Intent();
        myIntent.setAction(action);
//        PendingIntent sender = PendingIntent.getBroadcast(context, alarmId, myIntent, 0);//如果是广播，就这么写
        PendingIntent sender = PendingIntent.getService(context, alarmId, myIntent, 0);
        AlarmManager alarm = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarm.cancel(sender);
    }
}