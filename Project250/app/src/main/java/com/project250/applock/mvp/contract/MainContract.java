package com.project250.applock.mvp.contract;

import android.content.Context;

import com.project250.applock.base.BasePresenter;
import com.project250.applock.base.BaseView;
import com.project250.applock.model.CommLockInfo;

import java.util.List;



public interface MainContract {
    interface View extends BaseView<Presenter> {
        void loadAppInfoSuccess(List<CommLockInfo> list);
    }

    interface Presenter extends BasePresenter {
        void loadAppInfo(Context context, boolean isSort);

        void loadLockAppInfo(Context context);

        void onDestroy();
    }
}
