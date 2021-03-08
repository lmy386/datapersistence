package com.example.datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SQliteStore extends AppCompatActivity {
    private Button mbtn1,mbtn2,mbtn3,mbtn4,mbtn5;
    private MydatabaseHelper mydatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_qlite_store);
        mbtn1 = findViewById(R.id.sqlite_btn1);
        //创建数据库并创建表
        mydatabaseHelper = new MydatabaseHelper(this,"BookStore.db",null,3);
        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydatabaseHelper.getWritableDatabase();
            }
        });

        //插入数据
        mbtn2 = findViewById(R.id.sqlite_btn2);
        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mydatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始插入第一条数据
                values.put("name","生命不能承受之轻");
                values.put("author","米兰·昆德拉");
                values.put("pages",556);
                values.put("price",16.98);
                db.insert("Book",null,values);
                values.clear();
                //插入第二条数据
                values.put("name","活着");
                values.put("author","余华");
                values.put("pages",345);
                values.put("price",26.88);
                db.insert("Book",null,values);
                values.clear();

            }
        });

        //更新数据
        mbtn3 = findViewById(R.id.sqlite_btn3);
        mbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mydatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",10.99);
                db.update("Book",values,"name = ?",new String[]{"生命不能承受之轻"});
            }
        });

        //删除数据
        mbtn4 = findViewById(R.id.sqlite_btn4);
        mbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mydatabaseHelper.getWritableDatabase();
                db.delete("Book","id == ?",new String[]{"10"});
            }
        });

        //查询数据
        mbtn5 = findViewById(R.id.sqlite_btn5);
        mbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mydatabaseHelper.getWritableDatabase();
                //查询Book中的全部数据
                Cursor cursor = db.query("Book",null,null,null,null,null,null);
                //moveToFirst将数据的指针移动到第一行的位置
                if (cursor.moveToFirst()){
                    do {
                        //遍历cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("Book", "Book name: "+name);
                        Log.d("Book", "Book author: "+author);
                        Log.d("Book", "Book pages: "+pages);
                        Log.d("Book", "Book price: "+price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });



    }
}