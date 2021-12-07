package edu.neu.madcourse.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class exerciseList extends AppCompatActivity {
    TextView fitonedesc, fittwodesc;
    Button btnStart;
    private Intent i;
    private String totalET;
    private String totalRT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        fitonedesc = findViewById(R.id.fitonedesc);
        fittwodesc = findViewById(R.id.fittwodesc);
        btnStart = findViewById(R.id.btnStart);

        Intent data = getIntent();
        totalET = data.getStringExtra("totalET");
        totalRT = data.getStringExtra("totalRT");
        fitonedesc.setText(totalET);
        fittwodesc.setText(totalRT);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStartTimer();
            }
        });
    }

    public void openStartTimer(){
        Intent intent = new Intent(this, StartWorkOut.class);
        startActivity(intent);
    }

    public void openNewIntent(){
        i = new Intent(this, summary.class);
        i.putExtra("totalET", totalET);
        i.putExtra("totalRT", totalRT);
    }
}