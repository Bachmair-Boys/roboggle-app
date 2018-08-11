package com.boys.bachmair.interviewbotapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateDropdown();
    }

    private Connection connect() {
        String url="";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            Log.wtf("Interview Bot App", "Error connecting to database");
            e.printStackTrace();
        }
        return connection;
    }

    public void generateDropdown() {
        String sql = "SELECT type FROM type";

        try {
            Connection connection = this.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        }
        catch (SQLException e) {
            Log.wtf("Interview Bot App", "Error reading database");
            e.printStackTrace();
        }

        Spinner dropdown = findViewById(R.id.dropdown);
        String[] categories = {"Basic", "Behavioral", "Salary", "Brainteaser"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        dropdown.setAdapter(adapter);
    }

    public void generateQuestion(View view) {
        TextView box = findViewById(R.id.questionText);
    }
}
