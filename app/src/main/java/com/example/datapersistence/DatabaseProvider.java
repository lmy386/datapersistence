package com.example.datapersistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.datapersistence.provider";
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;
    private static UriMatcher uriMatcher;
    private MydatabaseHelper mydatabaseHelper;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }

    public DatabaseProvider() {
    }
    //  从内容提供器中删除数据，使用uri参数确定删除那张表中的数据，
    //  selection和selectionArgs参数用于约束删除那些行，被删除的行数将作为返回值返回
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mydatabaseHelper.getWritableDatabase();
       int deleteRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deleteRows = db.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookid = uri.getPathSegments().get(1);
                deleteRows = db.delete("Book","id = ?",new String[]{bookid});
                break;
            case CATEGORY_DIR:
                deleteRows = db.delete("Category",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRows = db.delete("Category","id = ?",new String[]{categoryId});
                break;
            default:break;


        }


        return deleteRows;
    }
    //根据传入的uri来返回相应的mime类型
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return  "vnd.android.Cursor.dir/vnd.com.example.datapersistence.provider.book";
            case BOOK_ITEM:
                return  "vnd.android.Cursor.item/vnd.com.example.datapersistence.provider.book";
            case CATEGORY_DIR:
                return  "vnd.android.Cursor.dir/vnd.com.example.datapersistence.provider.category";
            case CATEGORY_ITEM:
                return  "vnd.android.Cursor.item/vnd.com.example.datapersistence.provider.category";

        }
        return null;
    }
    //向内容提供器中添加一条数据
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mydatabaseHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookID = db.insert("Book",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/book/"+newBookID);
                break;
            case CATEGORY_DIR:
                break;
            case CATEGORY_ITEM:
                long newCategoryId = db.insert("Category",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/category/"+newCategoryId);
                break;
            default:
                break;
        }


        return uriReturn;
    }
    //初始化你饿哦让提供器的时候调用，通常在这里完成对数据库的创建和升级等操作，返回true表示内容提供器初始化成功
    @Override
    public boolean onCreate() {
        mydatabaseHelper = new MydatabaseHelper(getContext(),"BookStore.db",null,2);
        return true;
    }
        //  从内容提供器中查询数据，projection用于确定查询那些列，selection和selectionArgs用于约束查询那些行
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mydatabaseHelper.getWritableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = db.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookid = uri.getPathSegments().get(1);
                cursor = db.query("Book",projection,"id = ?",new String[]{bookid},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category",projection,"id = ?",new String[]{categoryId},null,null,sortOrder);
                break;
            default:break;


        }
        return cursor;
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mydatabaseHelper.getWritableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updateRows = db.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookid = uri.getPathSegments().get(1);
                updateRows = db.update("Book",values,"id = ?",new String[]{bookid});
                break;
            case CATEGORY_DIR:
                updateRows = db.update("Category",values,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updateRows = db.update("Category",values,"id = ?",new String[]{categoryId});
                break;
            default:break;


        }

        return  updateRows;
    }
}