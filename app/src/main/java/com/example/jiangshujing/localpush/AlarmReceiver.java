package com.example.jiangshujing.localpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;

import static com.example.jiangshujing.localpush.Contants.KEY_NOTIFY;
import static com.example.jiangshujing.localpush.Contants.TIMER_ACTION;

public class AlarmReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("AlarmReceiver","预约时间到，发送消息");
        if(TIMER_ACTION.equals(intent.getAction())){
            String str = intent.getStringExtra(KEY_NOTIFY);
            NotifyObject obj = null;
            try {
                obj = NotifyObject.from(str);
                if(obj != null){
                    NotificationUtil.notifyByAlarmByReceiver(context,obj);//立即开启通知
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
