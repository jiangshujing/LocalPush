package com.example.jiangshujing.localpush;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

import static com.example.jiangshujing.localpush.Contants.KEY_NOTIFY;

public class AlarmService extends Service {
    public AlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("AlarmService","预约时间到，发送消息");
        String str = intent.getStringExtra(KEY_NOTIFY);
        if (str == null || str.trim().length() == 0) return super.onStartCommand(intent, flags, startId);
        try {
            NotifyObject obj = NotifyObject.from(str);
            NotificationUtil.notifyByAlarmByReceiver(this, obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
