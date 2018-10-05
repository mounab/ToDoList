package com.training.todo_list.activities.todo_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String mTAG = "DatabaseHelper";

    private static final String mTABLE_NAME = "people_table";
    private static final String mCOL1 = "ID";
    private static final String mCOL2 = "name";


    public DatabaseHelper(Context context) {
        super(context, mTABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + mTABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                mCOL2 +" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int pIi, int pIi1) {
        db.execSQL("DROP IF TABLE EXISTS " + mTABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String pSitem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mCOL2, pSitem);

        Log.d(mTAG, "addData: Adding " + pSitem + " to " + mTABLE_NAME);

        long result = db.insert(mTABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String tquery = "SELECT * FROM " + mTABLE_NAME;
        Cursor rdata = db.rawQuery(tquery, null);
        return rdata;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param pSname
     * @return
     */

    public Cursor getItemID(String pSname){
        SQLiteDatabase tdb = this.getWritableDatabase();
        String tquery = "SELECT " + mCOL1 + " FROM " + mTABLE_NAME +
                " WHERE " + mCOL2 + " = '" + pSname + "'";
        Cursor rdata = tdb.rawQuery(tquery, null);
        return rdata;
    }

    /**
     * Updates the name field
     * @param pSnewName
     * @param pIid
     * @param pSoldName
     */
    public void updateName(String pSnewName, int pIid, String pSoldName){
        SQLiteDatabase tdb = this.getWritableDatabase();
        String tquery = "UPDATE " + mTABLE_NAME + " SET " + mCOL2 +
                " = '" + pSnewName + "' WHERE " + mCOL1 + " = '" + pIid + "'" +
                " AND " + mCOL2 + " = '" + pSoldName + "'";
        Log.d(mTAG, "updateName: query: " + tquery);
        Log.d(mTAG, "updateName: Setting name to " + pSnewName);
        tdb.execSQL(tquery);
    }

    /**
     * Delete from database
     * @param pIid
     * @param pSname
     */
    public void deleteName(int pIid, String pSname){
        SQLiteDatabase tdb = this.getWritableDatabase();
        String tquery = "DELETE FROM " + mTABLE_NAME + " WHERE "
                + mCOL1 + " = '" + pIid + "'" +
                " AND " + mCOL2 + " = '" + pSname + "'";
        Log.d(mTAG, "deleteName: query: " + tquery);
        Log.d(mTAG, "deleteName: Deleting " + pSname + " from database.");
        tdb.execSQL(tquery);
    }

}
























