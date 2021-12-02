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
    private TextView exerciseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        exerciseTimePicker = findViewById(R.id.exercisetimepicker);
        exerciseTime = findViewById(R.id.exercisetime);

        Time.initTime();
        exerciseTimePicker.setMaxValue(Time.getTimeArrayList().size() -1);
        exerciseTimePicker.setMinValue(0);
        exerciseTimePicker.setDisplayedValues(Time.timeString());

        exerciseTimePicker.setVisibility(View.GONE);

        exerciseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseTimePicker.setVisibility(View.VISIBLE);
//                show();
            }
        });



        exerciseTimePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                exerciseTime.setText(Time.getTimeArrayList().get(newValue).getMinute() +":"+ Time.getTimeArrayList().get(newValue).getSecond());
            }

    });
}

}