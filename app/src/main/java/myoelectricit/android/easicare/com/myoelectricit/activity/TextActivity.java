package myoelectricit.android.easicare.com.myoelectricit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import myoelectricit.android.easicare.com.myoelectricit.R;
import myoelectricit.android.easicare.com.myoelectricit.interfaces.OnCallReaxInterface;
import myoelectricit.android.easicare.com.myoelectricit.interfaces.OnSendDataInterface;
import myoelectricit.android.easicare.com.myoelectricit.interfaces.OnSynchronizationInterface;
import myoelectricit.android.easicare.com.myoelectricit.utli.BLEBraceletUtlis;
import myoelectricit.android.easicare.com.myoelectricit.utli.ConvertData;
import myoelectricit.android.easicare.com.myoelectricit.utli.RootUtil;

public class TextActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        initView();
        initData();

    }

    private void initData() {

    }

    private void initView() {
        button_send = (Button) findViewById(R.id.button_send);

        button_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_send:

                break;
        }
    }
}
