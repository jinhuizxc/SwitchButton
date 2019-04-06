package com.example.switchbutton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;

public class StyleActivity extends AppCompatActivity {

    private SwitchButton mFlymeSb, mMIUISb, mCustomSb, mDefaultSb, mSB;

    private boolean isSelect = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);

        SwitchButton disableSb = (SwitchButton) findViewById(R.id.sb_disable_control);
        SwitchButton disableNoEventSb = (SwitchButton) findViewById(R.id.sb_disable_control_no_event);
        mFlymeSb = (SwitchButton) findViewById(R.id.sb_custom_flyme);
        mMIUISb = (SwitchButton) findViewById(R.id.sb_custom_miui);
        mCustomSb = (SwitchButton) findViewById(R.id.sb_custom);
        mDefaultSb = (SwitchButton) findViewById(R.id.sb_default);
        mSB = (SwitchButton) findViewById(R.id.sb_ios);


        mDefaultSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                if (isSelect){
                    isSelect = false;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(StyleActivity.this, "isChecked = " + isChecked, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }else {
                    isSelect = true;
                    // 模拟网络请求
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(2000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(StyleActivity.this, "isChecked = " + isChecked, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });
        if (disableSb != null) {
            disableSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mFlymeSb.setEnabled(isChecked);
                    mMIUISb.setEnabled(isChecked);
                    mCustomSb.setEnabled(isChecked);
                    mDefaultSb.setEnabled(isChecked);
                    mSB.setEnabled(isChecked);
                }
            });
        }
        if (disableNoEventSb != null) {
            disableNoEventSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mFlymeSb.setEnabled(isChecked);
                    mMIUISb.setEnabled(isChecked);
                    mCustomSb.setEnabled(isChecked);
                    mDefaultSb.setEnabled(isChecked);
                    mSB.setEnabled(isChecked);
                }
            });
            disableNoEventSb.setCheckedImmediatelyNoEvent(false);
        }

    }
}
