package edu.neu.madcourse.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class StartWorkOut extends AppCompatActivity {
    private TextView countdownText;
    private Button countdownButton;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillisecounds = 600000; //600000=10mins
    private boolean timeRunning;
    private Button summaryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_work_out);

        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.countdown_button);
        summaryButton = findViewById(R.id.summary_button);
        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });
        updateTimer();

        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySummary();
            }
        });
    }
    public void openActivitySummary(){
        Intent intent = new Intent(this, summary.class);
        startActivity(intent);
    }
    public void startStop(){
        if (timeRunning){
            stopTimer();
        } else {
            startTimer();
        }
    }
    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMillisecounds, 1000) {
            @Override
            // l contains remaining time for countDownTimer
            // everytime pass in, update timeLeftInMillisecounds to timeLeft of this timer
            public void onTick(long l) {
                timeLeftInMillisecounds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        countdownButton.setText("PAUSE");
        timeRunning = true;

    }
    public void stopTimer(){
        countDownTimer.cancel();
        countdownButton.setText("START");
        timeRunning = false;
    }
    public void updateTimer(){
        int minutes = (int)timeLeftInMillisecounds / 60000; // divided 60 seconds
        int seconds = (int)timeLeftInMillisecounds % 60000 / 1000;
        String timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10){
            timeLeftText += "0";
        }
        timeLeftText += seconds;
        countdownText.setText(timeLeftText);
    }
}