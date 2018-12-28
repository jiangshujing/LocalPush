package com.example.jiangshujing.localpush;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.open_time_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long now = System.currentTimeMillis();
                //模拟延时消息的时间
                long interval[] = {0, 6000, 30000, 100000, 200000, 500000, 1000000, 8000000};
                int count = 1;
                SimpleDateFormat smf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                Map<Integer, NotifyObject> notifyObjects = new HashMap<>();
                for (long inter : interval) {
                    Date date = new Date(now + inter);

                    NotifyObject obj = new NotifyObject();
                    obj.type = count++;
                    obj.title = "消息标题";
                    obj.subText = "提醒时间:" + smf.format(date);
                    obj.content = "类型:" + (count - 1) + "," + inter;
                    obj.noticeTime = now + inter;
                    obj.activityClass = MainActivity.class;
                    obj.icon = R.mipmap.ic_launcher;
                    notifyObjects.put(obj.type, obj);

                }
                NotificationUtil.notifyByAlarm(MainActivity.this, notifyObjects);
            }
        });
    }
}