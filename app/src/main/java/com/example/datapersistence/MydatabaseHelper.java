package com.example.datapersistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MydatabaseHelper extends SQLiteOpenHelper {
    //建表语句
    private static final String CREATE_BOOK = "create table Book ("
                                                +"id integer primary key autoincrement,"
                                                +"author text,"
                                                 +"price real,"
                                                 +"pages integer,"
                                                 +"name text)";
    private static final String CREATE_CATEGORY = "create table Category ("
            +"id integer primary key autoincrement,"
            +"catagory_name text,"
            +"category_code integer)";
    private Context mContext;
    public MydatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //在创建数据库的时候同时创建表
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();

    }


    //在对数据库进行升级的时候执行
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists category");
        onCreate(db);

    }
}
