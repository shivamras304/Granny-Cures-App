package com.example.shivam.grannycures;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by Rishabh on 4/10/16.
 */
public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "GrannyCuresDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "ailment";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_REMEDY = "remedy";
    private static final String COLUMN_ID = "id";


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public  ArrayList<String> getAilmentName(){
        ArrayList<String> myAilments = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] {COLUMN_NAME} , null ,null  , null, null, COLUMN_NAME +  "  ASC"  );

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                myAilments.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }

        return myAilments;
    }

    public String getRemedyFromID(Integer id){
        String remedy = null;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_REMEDY}, "id = ? ", new String[]{id.toString()}, null, null, null);
        cursor.moveToFirst();
        if (cursor != null) {
            remedy = cursor.getString(0);
        }
        return  remedy;
    }

    public String getTitleFromID(Integer id){
        String name = null;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_NAME}, "id = ? ", new String[]{id.toString()}, null, null, null);
        cursor.moveToFirst();
        if (cursor != null) {
            name = cursor.getString(0);
        }
        return  name;
    }




}
