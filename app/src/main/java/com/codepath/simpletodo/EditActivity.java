package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    protected static final String EXTRA_ITEM_NAME = "com.codepath.simpletodo.item_name";
    protected static final String EXTRA_EDITED_NAME = "com.codepath.simpletodo.edited_name";
    private EditText editText;
    private String editedName;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editText = (EditText) findViewById(R.id.etEditedName);
        btnSave = (Button) findViewById(R.id.btnSaveEdit);
        editedName = getIntent().getStringExtra(EXTRA_ITEM_NAME);
        if(editedName != null){
            editText.setText(editedName);
        }
        btnSave.setActivated(false);
        setListenerForEditText();
        setListenerForSaveBtn();
    }

    private void setListenerForSaveBtn() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_EDITED_NAME, editedName);
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
                editedName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
