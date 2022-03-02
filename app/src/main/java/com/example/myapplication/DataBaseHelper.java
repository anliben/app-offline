package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String COSTUMERS_TABLE = "COSTUMERS_TABLE";
    public static final String COLUMN_COSTUMER_NAME = "COLUMN_COSTUMER_NAME";
    public static final String COLUMN_COSTUMER_AGE = "COLUMN_COSTUMER_AGE";
    public static final String COLUMN_COSTUMER_ACTIVE = "COLUMN_COSTUMER_ACTIVE";
    public static final String COLUMN_COSTUMER_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "Costumers.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE IF NOT EXISTS " + COSTUMERS_TABLE + "(" + COLUMN_COSTUMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COSTUMER_NAME + " TEXT, " + COLUMN_COSTUMER_AGE + " INTEGER, " + COLUMN_COSTUMER_ACTIVE + " BOOL)";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean addOne(Costumers costumers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COSTUMER_NAME, costumers.getName());
        cv.put(COLUMN_COSTUMER_AGE, costumers.getAge());
        cv.put(COLUMN_COSTUMER_ACTIVE, costumers.isActive());

        long insert = db.insert(COSTUMERS_TABLE, null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public List<Costumers> getEveryOne (){
        List<Costumers> returnList = new ArrayList<Costumers>();
        String queryString = "SELECT * FROM " + COSTUMERS_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            do{
                int customerId = cursor.getInt(0);
                String customersName = cursor.getString(1);
                int customersAge = cursor.getInt(2);
                boolean customersActive = cursor.getInt(3) == 1 ? true : false;

                Costumers newCustomers = new Costumers(customerId, customersName, customersAge, customersActive);
                returnList.add(newCustomers);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean deleteOne(Costumers costumers){
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "DELETE FROM " + COSTUMERS_TABLE + " WHERE "+ COLUMN_COSTUMER_ID + " = " + costumers.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
}
