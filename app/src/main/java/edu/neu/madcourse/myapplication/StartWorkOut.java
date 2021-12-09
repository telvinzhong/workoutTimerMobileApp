package edu.neu.madcourse.myapplication;import androidx.appcompat.app.AppCompatActivity;import android.media.MediaPlayer;import android.os.Bundle;import android.os.CountDownTimer;import android.util.Log;import android.view.View;import android.widget.Button;import android.widget.TextView;import android.content.Intent;import android.widget.Toast;public class StartWorkOut extends AppCompatActivity {    private TextView countdownText;    private Button countdownButton;    private CountDownTimer countDownTimer;    private TextView exerciseStatus ;    private long timeLeftInMillisecounds; //600000=10mins    private boolean timeRunning;    private Button homeButton;    private String totalET;    private String totalRT;    private long lngrest;    private long lngbreak;    private long lng;    private int ET;    private int RT;    private int BT;    private int set;    private int round;    private int original_round;    private Intent i;    MediaPlayer countdownSoundPlayer;    MediaPlayer halfwaySoundPlayer;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_start_work_out);        countdownText = findViewById(R.id.countdown_text);        countdownButton = findViewById(R.id.countdown_button);        homeButton = findViewById(R.id.home_button);        exerciseStatus = findViewById(R.id.exercise_status);        Intent data = getIntent();        totalET = data.getStringExtra("totalET");        totalRT = data.getStringExtra("totalRT");        ET = data.getIntExtra("ET", 0);        RT = data.getIntExtra("RT",0);        BT = data.getIntExtra("BT", 0);        set = data.getIntExtra("set",1);        original_round = data.getIntExtra("round",1);        round = original_round;//        if (round != 1){//            ET = ET / round;//        }//        if (set != 1){//            ET = ET / set;//        }        lng = Long.valueOf(ET).longValue();        lngrest = Long.valueOf(RT).longValue();        lngbreak = Long.valueOf(BT).longValue();        timeLeftInMillisecounds = lng * 1000 + 1000;        Log.d("BT", String.valueOf(BT));        Log.d("ET", String.valueOf(ET));        Log.d("RT", String.valueOf(RT));        countdownSoundPlayer = MediaPlayer.create(getApplicationContext(), R.raw.countdown5);        halfwaySoundPlayer = MediaPlayer.create(getApplicationContext(), R.raw.halfway);        startStop();        countdownButton.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                startStop();            }        });        homeButton.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {//                openRestTimer();                startActivity(new Intent(StartWorkOut.this, MainActivity.class));//                stopTimer();                // GO HOME            }        });    }    public void openRestTimer(){        i = new Intent(this, restTimer.class);        i.putExtra("totalET", totalET);        i.putExtra("totalRT", totalRT);        i.putExtra("ET", ET);        i.putExtra("RT", RT);        i.putExtra("BT", BT);        i.putExtra("set", set);        i.putExtra("round",round);    }    public void startStop(){        if (timeRunning){            stopTimer();        } else {            startTimer();        }    }    public void startTimer(){        countDownTimer = new CountDownTimer(timeLeftInMillisecounds, 1000) {            private final long totalTimeInMilliseconds = timeLeftInMillisecounds;            private boolean halfwayDone = false;            private boolean countdownStarted = false;            @Override            // l contains remaining time for countDownTimer            // everytime pass in, update timeLeftInMillisecounds to timeLeft of this timer            public void onTick(long l) {                timeLeftInMillisecounds = l;                updateTimer();                if (timeLeftInMillisecounds <= 5000 && !countdownStarted && Setting.getInstance().isCountdownSoundEnabled()) {                    countdownSoundPlayer.start();                    countdownStarted = true;                }                if (timeLeftInMillisecounds <= totalTimeInMilliseconds / 2 && !halfwayDone && Setting.getInstance().isHalfwaySoundEnabled()) {                    halfwaySoundPlayer.start();                    halfwayDone = true;                }            }            @Override            public void onFinish() {            }        }.start();        countdownButton.setText("PAUSE");        timeRunning = true;    }    public void stopTimer(){        countDownTimer.cancel();        countdownButton.setText("START");        timeRunning = false;    }    public void updateTimer(){        int minutes = (int)timeLeftInMillisecounds / 60000; // divided 60 seconds        int seconds = (int)timeLeftInMillisecounds % 60000 / 1000;        String timeLeftText = "" + minutes;        timeLeftText += ":";        if (seconds < 10){            timeLeftText += "0";        }        timeLeftText += seconds;        countdownText.setText(timeLeftText);        if(timeLeftInMillisecounds < 1000) {            if (exerciseStatus.getText().equals("Exercise")) {                if (round > 1 ) {                    round = round - 1;                    timeLeftInMillisecounds = lngrest * 1000 + 1000;                    startTimer();                    exerciseStatus.setText("Rest");                }                else if (round == 1 ) {                    if (set > 1){                        set = set - 1;                        timeLeftInMillisecounds = lngbreak * 1000 + 1000;                        startTimer();                        exerciseStatus.setText("Break");                    }                    else if (set == 1){                        exerciseStatus.setText("Congratulations, Exercise Finished");                        countdownButton.setVisibility(View.INVISIBLE);                        homeButton.setVisibility(View.VISIBLE);                    }                }            }            else if (exerciseStatus.getText().equals("Rest")) {                timeLeftInMillisecounds = lng * 1000 + 1000 ;                startTimer();                exerciseStatus.setText("Exercise");            }            else if (exerciseStatus.getText().equals("Break")){                round = original_round;                timeLeftInMillisecounds = lng * 1000 + 1000;                startTimer();                exerciseStatus.setText("Exercise");            }        }    }}