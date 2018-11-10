package jp.techacademy.takashi.sasaki.timer;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer timer;
    TextView timerText;
    double timerSec = 0.0;

    Handler handler = new Handler();

    Button startButton;
    Button pauseButton;
    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = findViewById(R.id.timer);
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer == null) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            timerSec += 0.1;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    timerText.setText(String.format("%.1f", timerSec));
                                }
                            });
                        }
                    }, 100, 100);
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerSec = 0.0;
                timerText.setText(String.format("%.1f", timerSec));

                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
            }
        });
    }
}
