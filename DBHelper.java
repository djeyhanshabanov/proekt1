package com.example.programa2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create table Userdetails (regnomer TEXT primary key, marka TEXT, model TEXT, danak DATE, zastrahovka DATE, pregled DATE, vinetka DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop TABle if exists Userdetails");
    }

    public Boolean insertuserdata(String model, String marka, String regnomer, String danak, String zastrahovka, String pregled, String vinetka)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("marka", marka);
        contentValues.put("model", model);
        contentValues.put("regnomer", regnomer);
        contentValues.put("danak", danak);
        contentValues.put("zastrahovka", zastrahovka);
        contentValues.put("pregled", pregled);
        contentValues.put("vinetka", vinetka);
        long result = DB.insert("Userdetails", null, contentValues);
            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }

    public Boolean updateuserdata(String marka, String model, String regnomer, String danak, String zastrahovka, String pregled, String vinetka) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("marka", marka);
        contentValues.put("model", model);
        contentValues.put("danak", danak);
        contentValues.put("zastrahovka", zastrahovka);
        contentValues.put("pregled", pregled);
        contentValues.put("vinetka", vinetka);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where regnomer = ?", new String[]{regnomer});
        if (cursor.getCount() > 0)
        {
            long result = DB.update("Userdetails", contentValues, "regnomer=?", new String[]{regnomer});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


        public Boolean deletedata(String regnomer){
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("Select * from Userdetails where regnomer = ?", new String[]{regnomer});
            if (cursor.getCount() > 0){
                long result = DB.delete("Userdetails", "regnomer=?", new String[]{regnomer});
                if (result == -1) {
                    return false;
                }
                else {
                    return true;
                }
                }
                else{
                return false;
            }
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
    }
