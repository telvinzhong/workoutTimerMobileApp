package edu.neu.madcourse.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.tomergoldst.tooltips.ToolTip;
import com.tomergoldst.tooltips.ToolTipsManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class workoutActivity extends AppCompatActivity implements ToolTipsManager.TipListener {
    private ConstraintLayout constraintLayout;
    private int exercise_memory = 17;
    private int rest_memory = 8;
    private int break_memory = 11;
    private NumberPicker exerciseTimePicker;
    private NumberPicker restTimePicker;
    private NumberPicker roundPicker;
    private NumberPicker breakTimePicker;
    private NumberPicker setPicker;
    private TextView totalExerciseTime;
    private TextView totalRestTime;
    private TextView breaks;
    private TextView setText;
    private String totalTime;
    private ToolTipsManager toolTipsManager;
    private Intent i;
    private Intent j;
    private int totalET;
    private int totalRT;
    private int totalBT;
    private int ET;
    private int RT;
    private int BT;
    int number_of_set;
    int number_of_round;
    private Button ok;
    private Button exerciseTime;
    private Button round;
    private Button restTime;
    private Button set;
    private Button breakTime;
    private Button starttimer;
    static Dialog d;

    List<ExampleItem> taskList;

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
        setText = findViewById(R.id.set);
        toolTipsManager = new ToolTipsManager(this);
        constraintLayout = findViewById(R.id.constraintlayout);
        starttimer = findViewById(R.id.starttimer);

        setText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayTooltip(ToolTip.POSITION_ABOVE, ToolTip.ALIGN_RIGHT);
            }
        });

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

        taskList = new ArrayList<>();

        starttimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Toast.makeText(workoutActivity.this,"Saved To History",Toast.LENGTH_SHORT).show();
                openNewIntent();
                startActivity(i);
            }
        });
}
    private void saveData(){
        String newExe = totalExerciseTime.getText().toString();
        String newRest = totalRestTime.getText().toString();

        ExampleItem exampleItem = new ExampleItem(newExe, newRest);
        taskList.add(exampleItem);
        List<ExampleItem> taskList1 = prefConfig.readListFromPref(this);
        if (taskList1 != null){
            for (ExampleItem item : taskList1) {
                taskList.add(item);
            }
        }
        prefConfig.writeInPref(getApplicationContext(), taskList);
    }

    public void openNewIntent(){
        i = new Intent(this, StartWorkOut.class);
        i.putExtra("totalET", totalExerciseTime.getText().toString());
        i.putExtra("totalRT", totalRestTime.getText().toString());
        i.putExtra("ET", ET);
        i.putExtra("RT", RT);
        i.putExtra("BT",BT);
        i.putExtra("set",number_of_set);
        i.putExtra("round",number_of_round);
    }

    public void show(String type)
    {
        final Dialog d = new Dialog(this);

        if (type.equals("exerciseTime")){
            d.setContentView(R.layout.exercise_dialog);
            exerciseTimePicker = d.findViewById(R.id.exercisetimepicker);
            ok = d.findViewById(R.id.setting);
            Time.initTime();
            exerciseTimePicker.setMaxValue(Time.getTimeArrayList().size() -1);
            exerciseTimePicker.setMinValue(0);
            exerciseTimePicker.setDisplayedValues(Time.timeString());
            exerciseTimePicker.setValue(exercise_memory);


            exerciseTimePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                    exerciseTime.setText(Time.getTimeArrayList().get(newValue).getMinute() +":"+ Time.getTimeArrayList().get(newValue).getSecond());
                    exercise_memory = newValue;
                    calculateTime();
                    openNewIntent();
                }
            });
        }
        else if (type.equals("restTime")){
            d.setContentView(R.layout.rest_dialog);
            restTimePicker = d.findViewById(R.id.resttimepicker);
            ok = d.findViewById(R.id.setting);
            Time.initTime();
            restTimePicker.setMaxValue(Time.getTimeArrayList().size() -1);
            restTimePicker.setMinValue(0);
            restTimePicker.setDisplayedValues(Time.timeString());
            restTimePicker.setValue(rest_memory);
            restTimePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                    restTime.setText(Time.getTimeArrayList().get(newValue).getMinute() +":"+ Time.getTimeArrayList().get(newValue).getSecond());
                    rest_memory = newValue;
                    calculateTime();
                    openNewIntent();
                }
            });

        }

        else if (type.equals("round")){
            d.setContentView(R.layout.round_dialog);
            roundPicker = d.findViewById(R.id.roundpicker);
            ok = d.findViewById(R.id.setting);
            roundPicker.setMinValue(1);
            roundPicker.setMaxValue(10);
            // Remember previous value
            roundPicker.setValue(Integer.parseInt(round.getText().toString()));
            roundPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                    round.setText(String.valueOf(newValue));
                    calculateTime();
                    openNewIntent();
                }
            });

        }

        else if (type.equals("set")){
            d.setContentView(R.layout.set_dialog);
            setPicker = d.findViewById(R.id.setpicker);
            ok = d.findViewById(R.id.setting);
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
                    openNewIntent();
                }
            });
        }

        else if (type.equals("breakTime")){
            d.setContentView(R.layout.break_dialog);
            breakTimePicker = d.findViewById(R.id.breaktimepicker);
            ok = d.findViewById(R.id.setting);
            Time.initTime();
            breakTimePicker.setMaxValue(Time.getTimeArrayList().size() -1);
            breakTimePicker.setMinValue(0);
            breakTimePicker.setDisplayedValues(Time.timeString());
            // remember previous value
            breakTimePicker.setValue(break_memory);

            breakTimePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                    breakTime.setText(Time.getTimeArrayList().get(newValue).getMinute() +":"+ Time.getTimeArrayList().get(newValue).getSecond());
                    break_memory = oldValue + 1;
                    calculateTime();
                    openNewIntent();
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
        String bt[] = breakTime.getText().toString().split(":");


        number_of_round = Integer.parseInt(round.getText().toString());
        number_of_set = Integer.parseInt(set.getText().toString());

        ET = (Integer.parseInt(et[0]) * 60 + Integer.parseInt(et[1]));
        RT = (Integer.parseInt(rt[0]) * 60 + Integer.parseInt(rt[1]));
//
//        totalET = Integer.parseInt(et[0]) * 60 * number_of_round + Integer.parseInt(et[1]) * number_of_round;
//        totalRT = Integer.parseInt(rt[0]) * 60 * (number_of_round -1) + Integer.parseInt(rt[1]) * (number_of_round-1);
        totalET = (Integer.parseInt(et[0]) * 60 + Integer.parseInt(et[1])) * number_of_round;
        totalRT = (Integer.parseInt(rt[0]) * 60 + Integer.parseInt(rt[1])) * (number_of_round-1);

        if (number_of_set != 1){
            totalET = totalET * number_of_set;
            BT = (Integer.parseInt(bt[0]) * 60 + Integer.parseInt(bt[1]));
            totalBT = (Integer.parseInt(bt[0]) * 60 + Integer.parseInt(bt[1])) * (number_of_set-1);
            totalRT = totalRT * number_of_set + totalBT;
        }
        // convert time to seconds
        // divide to get minute
        totalExerciseTime.setText(totalET / 60 + ":" + String.format("%02d" , totalET % 60));
        totalRestTime.setText(totalRT / 60 + ":" + String.format("%02d" , totalRT % 60));
    }

    public void toggleBreakTime(){
        if(set.getText().toString().equals("1")){
            breakTime.setVisibility(View.INVISIBLE);
            breaks.setVisibility(View.INVISIBLE);
        }
        else{
            breakTime.setVisibility(View.VISIBLE);
            breaks.setVisibility(View.VISIBLE);
        }
    }

    private void displayTooltip(int position, int align) {
        toolTipsManager.findAndDismiss(setText);
        ToolTip.Builder builder = new ToolTip.Builder(this,setText,constraintLayout,"Set is the number of cycles of rounds you complete",position);
        builder.setAlign(align);
        builder.withArrow(true);
        builder.setBackgroundColor(Color.GRAY);
        builder.setGravity(ToolTip.GRAVITY_CENTER);
        toolTipsManager.show(builder.build());
    }

    @Override
    public void onTipDismissed(View view, int anchorViewId, boolean byUser) {

    }
}