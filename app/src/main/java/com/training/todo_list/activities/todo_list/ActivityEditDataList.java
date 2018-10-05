package com.training.todo_list.activities.todo_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.training.todo_list.R;

public class ActivityEditDataList extends AppCompatActivity {

    private static final String TAG = "ActivityEditDataList";

    private Button mbtnSave, mbtnDelete;
    private EditText meditable_item;

    DatabaseHelper mDatabaseHelper;

    private String mSselectedName;
    private int mIselectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        mbtnSave = (Button) findViewById(R.id.btnSave);
        mbtnDelete = (Button) findViewById(R.id.btnDelete);
        meditable_item = (EditText) findViewById(R.id.editable_item);
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent treceivedIntent = getIntent();

        //now get the itemID we passed as an extra
        mIselectedID = treceivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        mSselectedName = treceivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        meditable_item.setText(mSselectedName);

        mbtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pview) {
                String tSitem = meditable_item.getText().toString();
                if(!tSitem.equals("")){
                    mDatabaseHelper.updateName(tSitem, mIselectedID, mSselectedName);
                    Intent teditScreenIntent = new Intent(ActivityEditDataList.this, ActivityTodoList.class);
                    startActivity(teditScreenIntent);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });

        mbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(mIselectedID, mSselectedName);
                meditable_item.setText("");
                toastMessage("removed from database");
                Intent teditScreenIntent = new Intent(ActivityEditDataList.this, ActivityTodoList.class);
                startActivity(teditScreenIntent);
            }
        });

    }

    /**
     * customizable toast
     * @param pSmessage
     */
    private void toastMessage(String pSmessage){
        Toast.makeText(this,pSmessage, Toast.LENGTH_SHORT).show();
    }
}
























