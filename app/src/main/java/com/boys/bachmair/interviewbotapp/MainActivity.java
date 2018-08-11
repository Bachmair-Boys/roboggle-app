package com.boys.bachmair.interviewbotapp;

import android.icu.util.Output;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Hashtable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateDropdown();
    }

    private Hashtable<String, Integer> types;

    public void restApiCall() {
        try {
            URL url = new URL("https://interview-bot-server.herokuapp.com/");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "get-categories";

            OutputStream os = connection.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        catch (MalformedURLException  e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void generateDropdown() {
        Spinner dropdown = findViewById(R.id.dropdown);
        String[] categories = {"Basic", "Behavioral", "Salary", "Brainteaser"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        dropdown.setAdapter(adapter);

        types = new Hashtable<>();
        for (int i = 0; i < categories.length; i++) {
            types.put(categories[i], i);
        }
    }

    public void generateQuestion(View view) {
        Spinner dropdown = findViewById(R.id.dropdown);
        String text = dropdown.getSelectedItem().toString();
        int option = types.get(text);

        TextView box = findViewById(R.id.questionText);
    }
}
