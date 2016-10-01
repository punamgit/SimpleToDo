package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditActivity extends AppCompatActivity {
    protected static final String EXTRA_ITEM = "com.codepath.simpletodo.item";
    protected static final String EXTRA_EDITED_NAME = "com.codepath.simpletodo.edited_name";
    private EditText editText;
    private String editedName;
    private @Item.Priority int editedPriority;
    private Item itemForEdit;
    private Button btnSave;
    private Spinner spinPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editText = (EditText) findViewById(R.id.etEditedName);
        btnSave = (Button) findViewById(R.id.btnSaveEdit);
        spinPriority = (Spinner) findViewById(R.id.spinnerPriority);
        itemForEdit = (Item)getIntent().getSerializableExtra(EXTRA_ITEM);
        if(itemForEdit != null){
            editText.setText(itemForEdit.getItemName());
            if(itemForEdit.getDueDate()!=null){
                //Date entry
            }
            editedPriority = itemForEdit.getPriority();
            spinPriority.setSelection(itemForEdit.getPriority());
        }
        btnSave.setActivated(false);
        setListenerForEditText();
        setListenerForSaveBtn();
        setListenerForPrioritySpinner();
    }

    private void setListenerForSaveBtn() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemForEdit.save();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void setListenerForEditText() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setActivated(true);
                itemForEdit.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setListenerForPrioritySpinner(){
        spinPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemForEdit.setPriority(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
