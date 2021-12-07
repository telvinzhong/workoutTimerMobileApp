package edu.neu.madcourse.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class summary extends AppCompatActivity {
    private String exe;
    private String rest;
    private String totalET;
    private String totalRT;
    TextView totalresttimesummary, totalexercisetimesummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        totalresttimesummary = findViewById(R.id.totalresttimesummary);
        totalexercisetimesummary = findViewById(R.id.totalexercisetimesummary);
        Intent data = getIntent();
        totalET = data.getStringExtra("totalET");
        totalRT = data.getStringExtra("totalRT");
        totalresttimesummary.setText(totalRT);
        totalexercisetimesummary.setText(totalET);
    }
}