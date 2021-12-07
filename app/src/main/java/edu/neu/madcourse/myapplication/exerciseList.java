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
    Intent i;
    private String totalET;
    private String totalRT;
    private int ET;
    private int RT;

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
        ET = data.getIntExtra("ET", 1);
        RT = data.getIntExtra("RT",1);
        fitonedesc.setText(totalET);
        fittwodesc.setText(totalRT);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewIntent();
                startActivity(i);
            }
        });
    }

    public void openNewIntent(){
        i = new Intent(this, StartWorkOut.class);
        i.putExtra("totalET", totalET);
        i.putExtra("totalRT", totalRT);
        i.putExtra("ET", ET);
        i.putExtra("RT", RT);
    }
}