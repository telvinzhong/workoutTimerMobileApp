package edu.neu.madcourse.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class restTimer extends AppCompatActivity {
    private TextView countdownText;
    private Button countdownButton;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillisecounds; //120000=2mins
    private boolean timeRunning;
    private Button summaryButton;
    private Button next_round_button;
    private String totalET;
    private String totalRT;
    private int ET;
    private int RT;
    private Intent i;
    private Intent j;
    private int set;
    private int round;
    private int breakTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_timer);
        countdownText = findViewById(R.id.rest_countdown_text);
        countdownButton = findViewById(R.id.rest_countdown_button);
        summaryButton = findViewById(R.id.summary_button);
        next_round_button = findViewById(R.id.next_round_button);
        Intent data = getIntent();
        totalET = data.getStringExtra("totalET");
        totalRT = data.getStringExtra("totalRT");
        ET = data.getIntExtra("ET", 1);
        RT = data.getIntExtra("RT",1);
        set = data.getIntExtra("set",1);
        round = data.getIntExtra("round",1);
        breakTime = data.getIntExtra("BT",0);
//        if (set != 1){
//            RT = (RT - breakTime) / set;
//        }
//        if (round != 1){
//            RT = RT / round;
//        }


        long lng = Long.valueOf(RT).longValue();
        timeLeftInMillisecounds = lng * 1000;

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
                startActivity(i);
                stopTimer();
            }
        });
        updateTimer();
        next_round_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewRoundTimer();
                startActivity(j);
                stopTimer();
            }
        });
        updateTimer();
    }
    public void openNewRoundTimer(){
        j = new Intent(this, StartWorkOut.class);
        j.putExtra("totalET", totalET);
        j.putExtra("totalRT", totalRT);
        j.putExtra("ET", ET);
        j.putExtra("RT", RT);
        j.putExtra("BT", breakTime);
        j.putExtra("set", set);
        j.putExtra("round",round);
    }
    public void openActivitySummary(){
        i = new Intent(this, summary.class);
        i.putExtra("totalET", totalET);
        i.putExtra("totalRT", totalRT);
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