package com.example.jiangshujing.localpush;

import android.app.*;
import android.support.annotation.DrawableRes;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class NotifyObject implements Serializable{
    //id
    public Integer type;
    public String title;
    public String subText;
    public String content;
    public String param;
    //发送通知时间
    public Long noticeTime;
    //点击跳转到的 activity
    public Class<? extends Activity> activityClass;
    //图标
    @DrawableRes public int icon;
    public List<Long> times = new ArrayList<>();

    public static byte[] toBytes(NotifyObject obj) throws  IOException{
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        String content = null;
        oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);
        oos.close();
        byte[] bytes = bout.toByteArray();
        bout.close();
        return bytes;
    }

    public static NotifyObject from(String content) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bin = new ByteArrayInputStream(content.getBytes("ISO-8859-1"));
//        ByteArrayInputStream bin = new ByteArrayInputStream(content.getBytes("UTF-8"));
        ObjectInputStream ois = null;
        NotifyObject obj = null;

        ois = new ObjectInputStream(bin);
        obj = (NotifyObject)ois.readObject();
        ois.close();
        bin.close();
        return obj;
    }

    public static String to(NotifyObject obj) throws  IOException{
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        String content = null;
        oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);
        oos.close();
        byte[] bytes = bout.toByteArray();
        content = new String(bytes,"ISO-8859-1");
//        content = new String(bytes,"UTF-8");
        bout.close();
        return content;
    }

    @Override
    public String toString() {
        return "NotifyObject{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", subText='" + subText + '\'' +
                ", content='" + content + '\'' +
                ", param='" + param + '\'' +
                ", noticeTime=" + noticeTime +
                ", activityClass=" + activityClass +
                ", icon=" + icon +
                ", times=" + times +
                '}';
    }
}