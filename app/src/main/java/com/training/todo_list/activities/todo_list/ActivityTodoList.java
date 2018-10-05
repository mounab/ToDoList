package com.training.todo_list.activities.todo_list;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.training.todo_list.R;

import java.util.ArrayList;

public class ActivityTodoList extends ListActivity {
    private static final String TAG = "ActivityTodoList";

    DatabaseHelper mDatabaseHelper;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_act_todo_list);
        mListView = (ListView) findViewById(android.R.id.list);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }


    public void askAddTodo(View pView) {
        Intent editScreenIntent = new Intent(ActivityTodoList.this, ActivityAddToList.class);
        startActivity(editScreenIntent);
        Toast.makeText(this, "Ask add todo", Toast.LENGTH_SHORT).show();
    }
    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor tdata = mDatabaseHelper.getData();
        ArrayList<String> tSlistData = new ArrayList<>();
        while(tdata.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            tSlistData.add(tdata.getString(1));
        }
        //create the list adapter and set the adapter
        ListAdapter tadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tSlistData);
        mListView.setAdapter(tadapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tSname = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + tSname);

                Cursor tdata = mDatabaseHelper.getItemID(tSname); //get the id associated with that name
                int tIitemID = -1;
                while(tdata.moveToNext()){
                    tIitemID = tdata.getInt(0);
                }
                if(tIitemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + tIitemID);
                    Intent teditScreenIntent = new Intent(ActivityTodoList.this, ActivityEditDataList.class);
                    teditScreenIntent.putExtra("id",tIitemID);
                    teditScreenIntent.putExtra("name",tSname);
                    startActivity(teditScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


    public void askSurprise(View pView) {
        AlertDialog.Builder tBuilder = new AlertDialog.Builder(this);
        tBuilder.setTitle(R.string.dialog_title_surprise);
        tBuilder.setMessage(R.string.dialog_message_surprise);
        tBuilder.setPositiveButton(R.string.confirm, null);
        tBuilder.show();
    }
}
