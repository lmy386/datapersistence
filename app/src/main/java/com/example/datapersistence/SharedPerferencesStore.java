package com.example.datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SharedPerferencesStore extends AppCompatActivity {
    private Button mbtn1,mbtn2;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_perferences_store);
        mbtn1 = findViewById(R.id.sp_btn);
        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","lmy");
                editor.putInt("age",22);
                editor.putBoolean("married",false);
                editor.apply();
            }
        });

        mbtn2 = findViewById(R.id.sp_btn2);
        tv = findViewById(R.id.sp_tv1);
        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
                String name = preferences.getString("name","");
                int age = preferences.getInt("age" ,0);
                boolean married = preferences.getBoolean("married",false);
                tv.setText("name is "+name+"\n"
                          +"age is "+age+"\n"
                            +"married is "+married);

            }
        });
    }
}