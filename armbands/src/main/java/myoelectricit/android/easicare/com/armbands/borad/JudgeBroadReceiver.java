package myoelectricit.android.easicare.com.armbands.borad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import myoelectricit.android.easicare.com.armbands.app.MyApplication;
import myoelectricit.android.easicare.com.armbands.utli.SPUtils;


public class JudgeBroadReceiver extends BroadcastReceiver {
    private static final String ACTION = "com.broad.judgeBroadreceiver";
    private String action="";
    @Override
    public void onReceive(Context context, Intent intent) {
        SPUtils spUtils=new SPUtils(MyApplication.context,"ACT");

        if (intent.getAction().equals(ACTION)){
            action=intent.getStringExtra("action");
            Toast.makeText(context, action, Toast.LENGTH_SHORT).show();
            spUtils.putString("action",action);
        }
    }
}
