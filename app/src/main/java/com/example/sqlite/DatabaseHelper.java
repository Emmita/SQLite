package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "tree_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TREES = "trees";
    private static final String ID = "id";
    private static final String NAME = "name";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table trees(id integer primary key autoincrement, name text);
        sqLiteDatabase.execSQL( "CREATE TABLE " + TABLE_TREES + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT);" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TREES + ";");
        onCreate(sqLiteDatabase);

    }

    public long addTree(String name){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, name);

        long insert = database.insert(TABLE_TREES, null, values);

        return insert;
    }

    public ArrayList<String> getAllTrees(){

        ArrayList<String> treesArrayList = new ArrayList<String>();
        String name = "";
        String selectQuery = "SELECT * FROM " + TABLE_TREES;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                name = cursor.getString(cursor.getColumnIndex(NAME));
                treesArrayList.add(name);
            }while(cursor.moveToNext());
        }

        return treesArrayList;

    }
}
