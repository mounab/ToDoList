package com.training.todo_list.activities.todo_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.training.todo_list.R;

public class ActivityAddToList extends AppCompatActivity {

    private static final String TAG = "ActivityAddToList";

    DatabaseHelper mDatabaseHelper;
    private Button mbtnAdd, mbtnViewData;
    private EditText maddText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);
        maddText = (EditText) findViewById(R.id.addText);
        mbtnAdd = (Button) findViewById(R.id.btnAdd);
        mbtnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        mbtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tSnewEntry = maddText.getText().toString();
                if (maddText.length() != 0) {
                    AddData(tSnewEntry);
                    maddText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        mbtnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tintent = new Intent(ActivityAddToList.this, ActivityTodoList.class);
                startActivity(tintent);
            }
        });
        maddText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    maddText.setHint("");
                else
                    maddText.setHint("Add your data");
            }
        });
    }

    public void AddData(String pSnewEntry) {
        boolean tBinsertData = mDatabaseHelper.addData(pSnewEntry);

        if (tBinsertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param pSmessage
     */
    private void toastMessage(String pSmessage){
        Toast.makeText(this,pSmessage, Toast.LENGTH_SHORT).show();
    }
}
