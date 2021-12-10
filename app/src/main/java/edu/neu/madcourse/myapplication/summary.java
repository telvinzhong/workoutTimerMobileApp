package edu.neu.madcourse.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class summary extends AppCompatActivity {
    RecyclerView recyclerView;
    String s1[], s2[];
    TextView exe, rest, conditionTextView;
//    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//    DatabaseReference conditionRef = rootRef.child("exerciseTime");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
//        rest = findViewById(R.id.rest);
//        exe = findViewById(R.id.exercise);
//        conditionTextView = findViewById(R.id.test);

        recyclerView = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.exercise_times);
        s2 = getResources().getStringArray(R.array.rest_times);
        MyAdapter myAdapter = new MyAdapter(this, s1, s2);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        conditionRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String text = snapshot.getValue(String.class);
//                conditionTextView.setText(text);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}
