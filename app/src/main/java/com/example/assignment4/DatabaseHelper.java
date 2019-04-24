package com.example.assignment4;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.*;
import android.database.sqlite.*;
import static com.example.assignment4.Database.dbHelper;
import static com.example.assignment4.model.DatabaseEntry.COL_DESCRIPTION;
import static com.example.assignment4.model.DatabaseEntry.COL_ID;
import static com.example.assignment4.model.DatabaseEntry.COL_RATING;
import static com.example.assignment4.model.DatabaseEntry.COL_TITLE;
import static com.example.assignment4.model.DatabaseEntry.COL_URL;
import static com.example.assignment4.model.DatabaseEntry.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 9;
    private static final String DATABASE_NAME = "movieTrailers";

    private static final String CREATE_TABLE = "" +
            "CREATE TABLE " + TABLE_NAME + "(" +
            model.DatabaseEntry.COL_ID + " TEXT PRIMARY KEY," +
            model.DatabaseEntry.COL_TITLE + " TEXT," +
            model.DatabaseEntry.COL_DESCRIPTION + " TEXT," +
            model.DatabaseEntry.COL_URL + " TEXT," +
            model.DatabaseEntry.COL_RATING + " TEXT)";

    private static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean updateData(int id, String title,String description,String url, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(COL_ID, Integer.toString(id));
        content.put(COL_TITLE, title);
        content.put(COL_DESCRIPTION, description);
        content.put(COL_URL, url);
        content.put(COL_RATING, rating);
        db.insert(TABLE_NAME, null,content);

        return true;
    }

    public void updateRating(int id, String rating)
    {
        ContentValues values = new ContentValues();
        values.put(COL_RATING, rating);
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
        dbWrite.update(TABLE_NAME, values,COL_ID + "=" + id, null);
        dbWrite.close();
    }

    public void deleteData(int id)
    {
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();
        dbWrite.execSQL("DELETE FROM MovieTrailers WHERE ID="+id+"");
    }

}