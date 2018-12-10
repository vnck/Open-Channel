package g3_2.open_channel_app.application;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

public class OpenChannelApplication extends Application {
    private static OpenChannelApplication instance;
    private static Context appContext;

    public static OpenChannelApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context mAppContext) {
        appContext = mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        this.setAppContext(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}