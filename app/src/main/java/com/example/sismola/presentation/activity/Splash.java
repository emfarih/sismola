package com.example.sismola.presentation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.sismola.R;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread thread = new Thread() {
            public void run () {
                try {
                    sleep(1000);
                } catch ( InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(Splash.this, LoginActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}
