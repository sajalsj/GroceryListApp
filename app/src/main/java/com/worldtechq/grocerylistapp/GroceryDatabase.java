package com.worldtechq.grocerylistapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


/*this class link dataabse to app we creating databse here*/
public class GroceryDatabase extends SQLiteOpenHelper {
    public static final String DatabaseName="GroceryDataItem.db";
    public GroceryDatabase( Context context) {
        super (context, DatabaseName,  null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String GroceryListTable= "CREATE TABLE " + GroceryData.DataEntry.Tablename
                + " ("+ GroceryData.DataEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            GroceryData.DataEntry.ColumnName + " TEXT NOT NULL, " + GroceryData.DataEntry.amount + " INTEGER NOT NULL, "
                + GroceryData.DataEntry.timestamp + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                ");";
        db.execSQL (GroceryListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS "+ GroceryData.DataEntry.Tablename);
        onCreate (db);

    }
}
