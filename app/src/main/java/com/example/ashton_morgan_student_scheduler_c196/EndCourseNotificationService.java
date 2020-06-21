package com.example.ashton_morgan_student_scheduler_c196;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class EndCourseNotificationService extends Service {
    Timer timer;
    TimerTask timerTask;
    public int triggerTimeInSec = 80000;
    private String notificationTitle = "";
    private String notificationText = "";
    public static final String channelID = "2";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        notificationTitle = intent.getStringExtra(AllCoursesActivity.EXTRA_NOTIFICATION_TITLE);
        notificationText = intent.getStringExtra(AllCoursesActivity.EXTRA_NOTIFICATION_TEXT);
        notificationsEnabledNotification();
        startTimer();
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimerTask();
    }

    final Handler handler = new Handler() ;
    public void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 5000, triggerTimeInSec * 1000);
    }
    public void stopTimerTask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        newNotification();
                    }
                });
            }
        };
    }
    private void newNotification() {
        NotificationManager notificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelID);
        builder.setContentTitle(notificationTitle);
        builder.setContentText(notificationText);
        builder.setSmallIcon(R.drawable.ic_alert);
        builder.setAutoCancel(true);
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelID ,"NOTIFICATION_CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH);
            builder.setChannelId(channelID);
            assert notificationMgr != null;
            notificationMgr.createNotificationChannel(notificationChannel);
        }
        assert notificationMgr != null;
        notificationMgr.notify((int) System.currentTimeMillis(), builder.build());
        stopTimerTask();
    }


    private void notificationsEnabledNotification() {
        NotificationManager notificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelID);
        builder.setContentTitle("Notifications Enabled");
        builder.setContentText("Notification scheduled tomorrow");
        builder.setSmallIcon(R.drawable.ic_alert);
        builder.setAutoCancel(true);
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelID ,"NOTIFICATION_CHANNEL_NAME", NotificationManager.IMPORTANCE_DEFAULT);
            builder.setChannelId(channelID);
            assert notificationMgr != null;
            notificationMgr.createNotificationChannel(notificationChannel);
        }
        assert notificationMgr != null;
        notificationMgr.notify((int) System.currentTimeMillis(), builder.build());
    }





}
