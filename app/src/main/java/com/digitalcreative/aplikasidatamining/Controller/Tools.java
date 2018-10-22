package com.digitalcreative.aplikasidatamining.Controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Tools {

    public static void saveSharedPreferencesLogList(Context context, ArrayList<ArrayList> collageList) {
        SharedPreferences mPrefs = context.getSharedPreferences("PhotoCollage", context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(collageList);
        prefsEditor.putString("myJson", json);
        prefsEditor.commit();
    }

    public static ArrayList<ArrayList> loadSharedPreferencesLogList(Context context) {
        ArrayList<ArrayList> savedCollage = new ArrayList<ArrayList>();
        SharedPreferences mPrefs = context.getSharedPreferences("PhotoCollage", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("myJson", "");
        if (json.isEmpty()) {
            savedCollage = new ArrayList<ArrayList>();
        } else {
            Type type = new TypeToken<List<ArrayList>>() {
            }.getType();
            savedCollage = gson.fromJson(json, type);
        }

        return savedCollage;
    }

    public ArrayList<ArrayList> load_excel_format_csv(String filename,String cvsSplitBy) {
        String csvFile = filename;
        String line = "";
        ArrayList<ArrayList> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            ArrayList<String> angka = new ArrayList<>();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                for (int i = 0; i < country.length; i++) {
                    angka.add(country[i]);
                }
                data.add(angka);
                angka = new ArrayList<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
