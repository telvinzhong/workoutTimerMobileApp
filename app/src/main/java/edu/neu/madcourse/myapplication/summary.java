package edu.neu.madcourse.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class summary extends AppCompatActivity {
    //ArrayList<ExampleItem> mExampleList;
    //ArrayList<ExampleItem> s1, s2;
    RecyclerView recyclerView;
    ArrayList<String> s1 = new ArrayList<String>();
    ArrayList<String> s2 = new ArrayList<String>();
    TextView exe, rest, conditionTextView, conditionTextView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        rest = findViewById(R.id.rest);
//        exe = findViewById(R.id.exercise);
//        conditionTextView = findViewById(R.id.conditionTextView);
//        conditionTextView1 = findViewById(R.id.conditionTextView1);
//        conditionTextView.setText(e);
//        conditionTextView1.setText(r);
//        setContentView(R.layout.test);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        recyclerView = findViewById(R.id.recyclerView);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("exercise", Context.MODE_PRIVATE);
        String e = sp.getString("exercise","");
        String r = sp.getString("rest","");


//        SharedPreferences sharedPreferences = getSharedPreferences("exercise", MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString("exercise", null);
//        Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();
//        mExampleList = gson.fromJson(json, type);
//        if (mExampleList == null) {
//            mExampleList = new ArrayList<>();
//        }
//        s1 = Arrays.asList(getResources().getStringArray(R.array.exercise_times));
//        s2 = Arrays.asList(getResources().getStringArray(R.array.rest_times));

        s1.add(e);
        s2.add(r);
        MyAdapter myAdapter = new MyAdapter(this, s1, s2);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
