package com.example.cur97.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class FameActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fame_activity);
        final TextView editText = findViewById(R.id.text);
        String a = Integer.toString(MainActivity.con);
        editText.setText(a);
    }

}
