package com.worldtechq.grocerylistapp;

import android.provider.BaseColumns;

/*this class conatins the parameter for creating table in sqlite*/

public class GroceryData {

    public GroceryData()
    {

    }


    public  static final class DataEntry implements BaseColumns {
        public  static final String Tablename="GroceryList";
        public static  final String ColumnName="Name";
        public  static  final String amount="amount";
        public static final  String timestamp="timestamp";

    }
}
