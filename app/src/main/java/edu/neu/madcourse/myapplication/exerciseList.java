package edu.neu.madcourse.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class exerciseList extends AppCompatActivity {
    TextView title, fitonetitle, fitonedesc, fittwotitle, fittwodesc;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySummary();
            }
        });
    }
    public void openActivitySummary(){
        Intent intent = new Intent(this, StartWorkOut.class);
        startActivity(intent);
    }
}