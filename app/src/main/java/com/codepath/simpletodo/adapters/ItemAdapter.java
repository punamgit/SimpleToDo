package com.codepath.simpletodo.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.simpletodo.Item;
import com.codepath.simpletodo.R;

/**
 * Created by Punam on 9/28/2016.
 */

public class ItemAdapter extends CursorAdapter {
    Context context;

    public ItemAdapter(Context context, Cursor items) {
        super(context, items, 0);
        items.moveToFirst();
        this.context = context;

    }



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.item_card_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor.isBeforeFirst() || cursor.isAfterLast()) {
            return;
        }
        TextView itemNameTextView = (TextView) view.findViewById(R.id.txt_item_name);
        TextView itemPriorityTextView = (TextView) view.findViewById(R.id.txt_item_priority);
        itemNameTextView.setText(cursor.getString(cursor.getColumnIndex("itemName")));
        switch  (cursor.getInt(cursor.getColumnIndex("priority"))){
            case Item.Priority.NONE:
                itemPriorityTextView.setText("");
                break;
            case Item.Priority.LOW:
                itemPriorityTextView.setText("LOW");
                break;
            case Item.Priority.MEDIUM:
                itemPriorityTextView.setText("MEDIUM");
                break;
            case Item.Priority.HIGH:
                itemPriorityTextView.setText("HIGH");
        }

    }
}
