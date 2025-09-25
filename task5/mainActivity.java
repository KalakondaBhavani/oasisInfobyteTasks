package com.example.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;
    private Button btnStart, btnHold, btnStop;

    private Handler handler = new Handler();

    private long startTime = 0L;
    private long timeBuff = 0L;
    private boolean running = false;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (running) {
                long millis = SystemClock.uptimeMillis() - startTime + timeBuff;
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                int hours = minutes / 60;
                seconds = seconds % 60;
                minutes = minutes % 60;
                int milliseconds = (int) (millis % 1000) / 10; // for two-digit milliseconds

                String time = String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, milliseconds);
                tvTimer.setText(time);
                handler.postDelayed(this, 50); // update every 50 ms
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimer = findViewById(R.id.tvTimer);
        btnStart = findViewById(R.id.btnStart);
        btnHold = findViewById(R.id.btnHold);
        btnStop = findViewById(R.id.btnStop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    startTime = SystemClock.uptimeMillis();
                    handler.post(runnable);
                    running = true;
                }
            }
        });

        btnHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    timeBuff += SystemClock.uptimeMillis() - startTime;
                    handler.removeCallbacks(runnable);
                    running = false;
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                startTime = 0L;
                timeBuff = 0L;
                handler.removeCallbacks(runnable);
                tvTimer.setText("00:00:00:00");
            }
        });
    }
}
