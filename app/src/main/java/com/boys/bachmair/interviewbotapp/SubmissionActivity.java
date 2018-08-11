package com.boys.bachmair.interviewbotapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SubmissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        generateSubmissionDropdown();
    }

    public void generateSubmissionDropdown() {
        Spinner dropdown = findViewById(R.id.submissionDropdown);
        String[] categories = {"Basic", "Behavioral", "Salary", "Brainteaser"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        dropdown.setAdapter(adapter);
    }
}
