package com.example.datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtn1,mbtn2,mbtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtn1 = findViewById(R.id.btn1);
        mbtn1.setOnClickListener(this);
        mbtn2 = findViewById(R.id.btn2);
        mbtn2.setOnClickListener(this);
        mbtn3 = findViewById(R.id.btn3);
        mbtn3.setOnClickListener(this);
        Button mbtn4 = findViewById(R.id.btn4);
        mbtn4.setOnClickListener(this);
        Button mbtn5 = findViewById(R.id.btn5);
        mbtn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                Intent intent1 = new Intent(MainActivity.this,FileStore.class);
                startActivity(intent1);
                break;
            case R.id.btn2:
                Intent intent2 = new Intent(MainActivity.this,SharedPerferencesStore.class);
                startActivity(intent2);
                break;
            case R.id.btn3:
                Intent intent3 = new Intent(MainActivity.this,SQliteStore.class);
                startActivity(intent3);
                break;
            case R.id.btn4:
                Intent intent4 = new Intent(MainActivity.this,SQliteStore.class);
                startActivity(intent4);
                break;
            case R.id.btn5:
                Intent intent5 = new Intent(MainActivity.this,ContentProvider.class);
                startActivity(intent5);
                break;
            default:
        }
    }
}