package edu.neu.madcourse.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;


public class prefConfig {
    public static void writeInPref(Context context, List<ExampleItem> list){
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("key", jsonString);
        editor.commit();
    }

    public static List<ExampleItem> readListFromPref(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString("key","");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ExampleItem>>(){}.getType();
        List<ExampleItem> list = gson.fromJson(jsonString, type);
        return list;
    }
}
