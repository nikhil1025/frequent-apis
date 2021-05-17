package com.niktek.androidtutorial.activities.viewModels;

import android.os.CountDownTimer;

public class timer {
    public void timerFunction() {
        CountDownTimer time = new CountDownTimer(50000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };
    }
}
