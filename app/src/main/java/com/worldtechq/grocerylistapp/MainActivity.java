package com.worldtechq.grocerylistapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase sdb;
    public MyAdaper myAdaper;
    private EditText ItemName;
    private Button Sub;
    private  Button add;
    private  Button AddItem;
    private  TextView amount;
     RecyclerView recyclerView;
    private  int mamount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        GroceryDatabase groceryDatabase = new GroceryDatabase (this);
        sdb = groceryDatabase.getWritableDatabase ( );
        recyclerView=findViewById (R.id.rv);
        recyclerView.setLayoutManager (new LinearLayoutManager (this));
        myAdaper = new MyAdaper (this, getItem ( ));
        recyclerView.setAdapter (myAdaper);


        /* this is used for deleting item on left or right swipe*/

        new ItemTouchHelper (new ItemTouchHelper.SimpleCallback (0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removedItem((long) viewHolder.itemView.getTag ());

            }
        }).attachToRecyclerView (recyclerView);
        ItemName = findViewById (R.id.etname);
        amount = findViewById (R.id.tvitem);
        Sub = findViewById (R.id.btnsub);
        add = findViewById (R.id.btnplus);
        AddItem = findViewById (R.id.btnadd);

        add.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                increase ( );
            }
        });
        Sub.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                decrease ( );
            }
        });
        AddItem.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                additem ( );
            }
        });
    }
    private void increase(){
        mamount++;
         amount.setText (String.valueOf (mamount));
    }
    private  void decrease(){
        if (mamount > 0){
        mamount--;
        amount.setText (String.valueOf (mamount));}

    }

    /*this method is used for adding item into list*/
    private void additem(){
        if (ItemName.getText ().toString ().trim ().length () == 0 || mamount==0){
            return;
        }
        String name=ItemName.getText ().toString ();
        ContentValues cv=new ContentValues ();
        cv.put (GroceryData.DataEntry.ColumnName,name);
        cv.put (GroceryData.DataEntry.amount,mamount);
        sdb.insert (GroceryData.DataEntry.Tablename,null,cv);
        ItemName.getText ().clear ();
        myAdaper.swapcursor (getItem ());
    }
    /*this method is used for deleting item from database*/

    private void removedItem(long id){
        sdb.delete (GroceryData.DataEntry.Tablename,GroceryData.DataEntry._ID + "=" + id,null);
        myAdaper.swapcursor (getItem ());

    }
    private Cursor getItem(){

        return  sdb.query (
                GroceryData.DataEntry.Tablename,
                null,
                null,
                null,
                null,
                null,
                GroceryData.DataEntry.timestamp + " DESC" );
    }
}
