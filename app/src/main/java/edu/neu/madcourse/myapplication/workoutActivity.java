package edu.neu.madcourse.myapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class workoutActivity extends AppCompatActivity {
    private NumberPicker exerciseTimePicker;
    private NumberPicker restTimePicker;
    private NumberPicker roundPicker;
    private NumberPicker breakTimePicker;
    private NumberPicker setPicker;
    private TextView totalExerciseTime;
    private TextView totalRestTime;
    private TextView breaks;
    private String totalTime;

    private Button ok;
    private Button exerciseTime;
    private Button round;
    private Button restTime;
    private Button set;
    private Button breakTime;
    static Dialog d ;

    /**
     * round.getText().toString()
     * set.getText().toString()
     *
     * exerciseTime.getText().toString()
     * restTime.getText().toString()
     * breakTime.getText().toString()
     *
     * HOW TO BREAK DOWN TIME:
     * String str[] = exerciseTime.getText().toString().split(":");
     * Minute will be str[0]
     * Minute will be str[1]
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        exerciseTime = findViewById(R.id.exercisetime);
        restTime = findViewById(R.id.resttime);
        round = findViewById(R.id.roundnumber);
        set = findViewById(R.id.setnumber);
        breakTime = findViewById(R.id.breaktime);
        breaks = findViewById(R.id.breaks);
        totalExerciseTime = findViewById(R.id.totalexercisetime);
        totalRestTime = findViewById(R.id.totalresttime);


        exerciseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show("exerciseTime");
            }
        });

        restTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show("restTime");
            }
        });

        round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show("round");
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show("set");
            }
        });

        breakTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show("breakTime");
            }
        });

        calculateTime();
//        toggleBreakTime();
}
    public void show(String type)
    {
        final Dialog d = new Dialog(this);

        if (type.equals("exerciseTime")){
            d.setContentView(R.layout.exercise_dialog);
            exerciseTimePicker = d.findViewById(R.id.exercisetimepicker);
            ok = d.findViewById(R.id.ok);
            Time.initTime();
            exerciseTimePicker.setMaxValue(Time.getTimeArrayList().size() -1);
            exerciseTimePicker.setMinValue(0);
            exerciseTimePicker.setDisplayedValues(Time.timeString());
            exerciseTimePicker.setValue(17);


            exerciseTimePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                    exerciseTime.setText(Time.getTimeArrayList().get(newValue).getMinute() +":"+ Time.getTimeArrayList().get(newValue).getSecond());
                    calculateTime();
                }
            });


        }
        else if (type.equals("restTime")){
            d.setContentView(R.layout.rest_dialog);
            restTimePicker = d.findViewById(R.id.resttimepicker);
            ok = d.findViewById(R.id.ok);
            Time.initTime();
            restTimePicker.setMaxValue(Time.getTimeArrayList().size() -1);
            restTimePicker.setMinValue(0);
            restTimePicker.setDisplayedValues(Time.timeString());
            restTimePicker.setValue(8);
            restTimePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                    restTime.setText(Time.getTimeArrayList().get(newValue).getMinute() +":"+ Time.getTimeArrayList().get(newValue).getSecond());
                    calculateTime();
                }
            });

        }

        else if (type.equals("round")){
            d.setContentView(R.layout.round_dialog);
            roundPicker = d.findViewById(R.id.roundpicker);
            ok = d.findViewById(R.id.ok);
            roundPicker.setMinValue(1);
            roundPicker.setMaxValue(10);
            // Remember previous value
            roundPicker.setValue(Integer.parseInt(round.getText().toString()));
            roundPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                    round.setText(String.valueOf(newValue));
                    calculateTime();
                }
            });

        }

        else if (type.equals("set")){
            d.setContentView(R.layout.set_dialog);
            setPicker = d.findViewById(R.id.setpicker);
            ok = d.findViewById(R.id.ok);
            setPicker.setMinValue(1);
            setPicker.setMaxValue(10);
            // Remember previous value
            setPicker.setValue(Integer.parseInt(set.getText().toString()));
            setPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                    set.setText(String.valueOf(newValue));
                    toggleBreakTime();
                    calculateTime();
                }
            });
        }

        else if (type.equals("breakTime")){
            d.setContentView(R.layout.break_dialog);
            breakTimePicker = d.findViewById(R.id.breaktimepicker);
            ok = d.findViewById(R.id.ok);
            Time.initTime();
            breakTimePicker.setMaxValue(Time.getTimeArrayList().size() -1);
            breakTimePicker.setMinValue(0);
            breakTimePicker.setDisplayedValues(Time.timeString());
            breakTimePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                    breakTime.setText(Time.getTimeArrayList().get(newValue).getMinute() +":"+ Time.getTimeArrayList().get(newValue).getSecond());
                }
            });
        }

        ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }
    public void calculateTime(){
        String et[] = exerciseTime.getText().toString().split(":");
        String rt[] = restTime.getText().toString().split(":");

        int totalET = Integer.parseInt(et[0]) * 60 * Integer.parseInt(round.getText().toString()) + Integer.parseInt(et[1]) * Integer.parseInt(round.getText().toString());;
        int totalRT = Integer.parseInt(rt[0]) * 60 * Integer.parseInt(round.getText().toString()) + Integer.parseInt(rt[1]) * Integer.parseInt(round.getText().toString());;

        if (Integer.parseInt(set.getText().toString()) != 1){
            totalET = totalET * Integer.parseInt(set.getText().toString());
        }
        // convert time to seconds
        // divide to get minute
        totalExerciseTime.setText(totalET / 60 + ":" + String.format("%02d" , totalET % 60));

        totalRestTime.setText(totalRT / 60 + ":" + String.format("%02d" , totalRT % 60));


    }

    public void toggleBreakTime(){
        Log.d("set", set.getText().toString());
        if(set.getText().toString().equals("1")){
            breakTime.setVisibility(View.INVISIBLE);
            breaks.setVisibility(View.INVISIBLE);
        }
        else{
            breakTime.setVisibility(View.VISIBLE);
            breaks.setVisibility(View.VISIBLE);
        }
    }


}