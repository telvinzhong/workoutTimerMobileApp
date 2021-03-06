package edu.neu.madcourse.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.startworkout:
                startActivity(new Intent(this, workoutActivity.class));
                break;
            case R.id.running:
                startActivity(new Intent(this, Running.class));
                break;
            case R.id.setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.history:
                startActivity(new Intent(this, summary.class));
                break;
        }
    }
}