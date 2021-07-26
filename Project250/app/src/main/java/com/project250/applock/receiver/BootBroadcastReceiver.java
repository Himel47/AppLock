package com.project250.applock.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.project250.applock.base.AppConstants;
import com.project250.applock.services.BackgroundManager;
import com.project250.applock.services.LoadAppListService;
import com.project250.applock.services.LockService;
import com.project250.applock.utils.LogUtil;
import com.project250.applock.utils.SpUtil;


public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(@NonNull Context context, Intent intent) {
        LogUtil.i("Boot service....");
        //TODO: pie compatable done
       // context.startService(new Intent(context, LoadAppListService.class));
        BackgroundManager.getInstance().init(context).startService(LoadAppListService.class);
        if (SpUtil.getInstance().getBoolean(AppConstants.LOCK_STATE, false)) {
            BackgroundManager.getInstance().init(context).startService(LockService.class);
            BackgroundManager.getInstance().init(context).startAlarmManager();
        }
    }
}
