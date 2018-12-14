package myoelectricit.android.easicare.com.armbands.app;

import android.app.Application;

/**
 * Created by fengli on 16-10-3.
 */

public class MyApplication extends Application {
    public static MyApplication context;


    /**
     * 单例模式中获取唯一的MyApplication实例
     *
     * @return
     */
    public static MyApplication getInstance() {
        /*if (null == context) {
            context = new MyApplication();
        }*/
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
