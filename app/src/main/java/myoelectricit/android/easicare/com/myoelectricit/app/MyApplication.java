package myoelectricit.android.easicare.com.myoelectricit.app;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fengli on 16-10-3.
 */

public class MyApplication extends Application {
    public static MyApplication context;
    public List<Activity> activitys = null;

    public MyApplication() {
        activitys = new LinkedList();
    }

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

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (activitys != null && activitys.size() > 0) {
            if(!activitys.contains(activity)){
                activitys.add(activity);
            }
        }else{
            activitys.add(activity);
        }

    }

//    // 遍历所有Activity并finish
//    public void exit() {
//        Log.e("SHESHE","??????????????");
//        byte[] bytess = ConvertData.hexStringToBytes(InstructionBooks.Ins_Standby);
//        DTing_ConIforActivity.CurrentMode = "待机模式";
//                            if(MyApplication.bluetoothService!=null)
//        MyApplication.bluetoothService.sendData(bytess);
//        if (activitys != null && activitys.size() > 0) {
//            for (Activity activity :activitys) {
//                activity.finish();
//            }
//        }
//        System.exit(0);
//    }

   /* @Override
    public void onTerminate() {
        super.onTerminate();
        byte[] bytess = ConvertData.hexStringToBytes(InstructionBooks.Ins_Standby);
        DTing_ConIforActivity.CurrentMode = "待机模式";
        MyApplication.bluetoothService.sendData(bytess);
    }*/


}
