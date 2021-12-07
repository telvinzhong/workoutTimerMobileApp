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
    private long timeLeftInMillisecounds; //600000=10mins
    private boolean timeRunning;
    private Button restButton;
    private String totalET;
    private String totalRT;
    private int ET;
    private int RT;
    private int BT;
    private int set;
    private int round;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_work_out);
        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.countdown_button);
        restButton = findViewById(R.id.rest_button);

        Intent data = getIntent();
        totalET = data.getStringExtra("totalET");
        totalRT = data.getStringExtra("totalRT");
        ET = data.getIntExtra("ET", 0);
        RT = data.getIntExtra("RT",0);
        BT = data.getIntExtra("BT", 0);
        set = data.getIntExtra("set",1);
        round = data.getIntExtra("round",1);
//        if (round != 1){
//            ET = ET / round;
//        }
//        if (set != 1){
//            ET = ET / set;
//        }

        long lng = Long.valueOf(ET).longValue();
        timeLeftInMillisecounds = lng * 1000;

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });
        updateTimer();

        restButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRestTimer();
                startActivity(i);
                stopTimer();
            }
        });
        updateTimer();
    }
    public void openRestTimer(){
        i = new Intent(this, restTimer.class);
        i.putExtra("totalET", totalET);
        i.putExtra("totalRT", totalRT);
        i.putExtra("ET", ET);
        i.putExtra("RT", RT);
        i.putExtra("BT", BT);
        i.putExtra("set", set);
        i.putExtra("round",round);
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