package com.liuting.animedemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by liuting on 2016/2/16.
 */
public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new welcomeThread().start();//开启线程
    }

    class welcomeThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Message msg = hand.obtainMessage();
            hand.sendMessage(msg);
        }
    }

    Handler hand = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isFirstRun()) {
                Intent intent = new Intent(WelcomeActivity.this,
                        SplashActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(WelcomeActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
            finish();
        };
    };

    private boolean isFirstRun() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(
                "share", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!isFirstRun) {
            return false;
        } else {
            editor.putBoolean("isFirstRun", false);
            editor.commit();
            return true;
        }
    }


}
