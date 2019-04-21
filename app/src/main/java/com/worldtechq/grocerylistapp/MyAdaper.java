package com.worldtechq.grocerylistapp;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/*this our adapter class for showing data in recyclerView that comes from database*/

public class MyAdaper extends RecyclerView.Adapter<MyAdaper.ViewHolder>{
        public Context context;
        public Cursor cursor;

    public  MyAdaper(Context context,Cursor cursor){
        this.context=context;
        this.cursor=cursor;

    }

      public class ViewHolder extends RecyclerView.ViewHolder {
          public TextView amountt;
          public  TextView itemname1;
          public ViewHolder(View itemView) {
              super (itemView);
              amountt= itemView.findViewById (R.id.tv_item);
              itemname1=itemView.findViewById (R.id.tv_itemname);
          }
      }
    {

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from (context).inflate (R.layout.itemlist,parent,false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        if (!cursor.moveToPosition ( position)){
            return;
        }
        String name=cursor.getString (cursor.getColumnIndex (GroceryData.DataEntry.ColumnName));
        int  amountt=cursor.getInt (cursor.getColumnIndex (GroceryData.DataEntry.amount));
        long id=cursor.getLong (cursor.getColumnIndex (GroceryData.DataEntry._ID));
        holder.itemView.setTag (id);
        holder.itemname1.setText (name);
        holder.amountt.setText (String.valueOf (amountt));

    }

    @Override
    public int getItemCount() {
        return cursor.getCount ();
    }

    public void swapcursor(Cursor mcursor){
        if (cursor !=null){
            cursor.close ();
        }
        cursor=mcursor;
        if (mcursor != null){
            notifyDataSetChanged ();
        }

    }


}
