package com.example.datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MyContentProviderActivity extends AppCompatActivity {
    private String newId;
    String TAG = "MyContentProviderActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_content_provider);
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加数据
                Uri uri = Uri.parse("content://com.example.datapersistence.provider/book");
                ContentValues values = new ContentValues();
                values.put("name","A Clash Of Kings");
                values.put("author","George Martin");
                values.put("pages",1098);
                values.put("price",22.98);
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri.getPathSegments().get(1);
            }
        });

        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询数据
                Uri uri = Uri.parse("content://com.example.datapersistence.provider/book");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if (cursor != null){
                    while(cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages =  cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "Book name is : "+ name);
                        Log.d(TAG, "Book author is : "+ author);
                        Log.d(TAG, "Book pages is : "+ pages);
                        Log.d(TAG, "Book price is : "+ price);
                    }
                    cursor.close();
                }
            }
        });

        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新数据
                Uri uri = Uri.parse("content://com.example.datapersistence.provider/book");
                ContentValues values = new ContentValues();
                values.put("name","A Storm Of Swords");
                values.put("pages",1000);
                values.put("price",26.99);
                getContentResolver().update(uri,values,"id = ?",new String[]{"1"});

            }
        });

        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除数据
                Uri uri = Uri.parse("content://com.example.datapersistence.provider/book");
                getContentResolver().delete(uri,"id = 2",null);
            }
        });
    }
}