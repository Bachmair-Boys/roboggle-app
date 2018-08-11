package com.boys.bachmair.interviewbotapp;

import android.content.Intent;
import android.icu.util.Output;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Hashtable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {
    private static int number = 0;
    private static String question = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateDropdown();
    }

    private Hashtable<String, Integer> types;

    public void restApiCall() {
        RequestParams rp = new RequestParams();
    }

    private int getQuestionQuantity(int category) {
        number = 0;
        RequestParams rp = new RequestParams();
        rp.add("category", Integer.toString(category));
        HttpUtils.post("https://interview-bot-server.herokuapp.com/get-count", rp, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    number = (Integer)response.get("count");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        return number;
    }

    private String getQuestion(int category, int num) {
        question = "";
        RequestParams rp = new RequestParams();
        rp.add("category", Integer.toString(category));
        rp.add("question", Integer.toString(num));
        HttpUtils.post("https://interview-bot-server.herokuapp.com/get-question", rp, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    question = (String)response.get("question");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        return "";
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



        int questionNumber = (int)(Math.random() * getQuestionQuantity(option));
        String question = getQuestion(option, questionNumber);

        box.setText(question);

    }

    public void changePage(View view) {
        Intent intent = new Intent(this, SubmissionActivity.class);
        startActivity(intent);
    }
}
