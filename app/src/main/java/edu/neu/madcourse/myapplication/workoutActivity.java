package edu.neu.madcourse.myapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class workoutActivity extends AppCompatActivity {
    private NumberPicker exerciseTimePicker;
    private TextView exerciseTime;
    static Dialog d ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        exerciseTime = findViewById(R.id.exercisetime);

        exerciseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });


}
    public void show()
    {
        final Dialog d = new Dialog(this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b2 = d.findViewById(R.id.ok);
        exerciseTimePicker = d.findViewById(R.id.exercisetimepicker);

        Time.initTime();
        exerciseTimePicker.setMaxValue(Time.getTimeArrayList().size() -1);
        exerciseTimePicker.setMinValue(0);
        exerciseTimePicker.setDisplayedValues(Time.timeString());

        exerciseTimePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                exerciseTime.setText(Time.getTimeArrayList().get(newValue).getMinute() +":"+ Time.getTimeArrayList().get(newValue).getSecond());
            }
    });

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }


}