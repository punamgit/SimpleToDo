package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.codepath.simpletodo.adapters.ItemAdapter;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    FlowCursorList<Item> items;
    ItemAdapter itemsAdapter;
    ListView lvItems;
    private int positionclicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView)findViewById(R.id.lvItems);
        items =  new FlowCursorList.Builder<>(Item.class)
                .modelQueriable(SQLite.select().from(Item.class))
                .build();

        itemsAdapter = new ItemAdapter(this, items.cursor());
        lvItems.setAdapter(itemsAdapter);

        setUpListViewListener();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null){
            return;
        }
        items.refresh();
        itemsAdapter.swapCursor(items.cursor());
        //itemsAdapter.notifyDataSetChanged();

    }

    // Listener for long click on ListView to delete item
    private void setUpListViewListener() {
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = items.getItem(position);
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra(EditActivity.EXTRA_ITEM, (Serializable) item);
                startActivityForResult(intent,0);
                //items.refresh();
                //itemsAdapter.notifyDataSetChanged();
            }
        }
        );

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               Item item = items.getItem(position);
                item.delete();
                items.refresh();
                itemsAdapter.swapCursor(items.cursor());

                return true;
            }
        });
    }

    public void onAddItem(View v){
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        if(itemText != null) {
            Item item = new Item();
            item.setName(itemText);
            item.setPriority(Item.Priority.NONE);
            item.save();

            items.refresh();
            itemsAdapter.swapCursor(items.cursor());
          //  itemsAdapter.notifyDataSetChanged();
        }
        etNewItem.setText("");
    }


}
