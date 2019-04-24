package com.example.assignment4;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;
import static com.example.assignment4.model.DatabaseEntry.COL_ID;
import static com.example.assignment4.model.DatabaseEntry.TABLE_NAME;

public class Database {

    public static DatabaseHelper dbHelper;

    // Constructor to get context
    public Database(Context context)
    {
        this.dbHelper = new DatabaseHelper(context);
    }

    public void insertData(String id, String title, String description, String url, String rating)
    {
        SQLiteDatabase dbWrite = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(model.DatabaseEntry.COL_TITLE, title);
        values.put(model.DatabaseEntry.COL_DESCRIPTION, description);
        values.put(model.DatabaseEntry.COL_URL, url);
        values.put(model.DatabaseEntry.COL_RATING, rating);

        // Insert the new row, returning the primary key value of the new row
        dbWrite.insert(TABLE_NAME, null, values);
    }

    public ArrayList<model> getAllMovies()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        ArrayList<model> moviesList = new ArrayList<>();
        SQLiteDatabase dbRead = dbHelper.getReadableDatabase();

        Cursor cursor = dbRead.rawQuery(query, null);

        while(cursor.moveToNext())
        {
            String id = cursor.getString(cursor.getColumnIndex(COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(model.DatabaseEntry.COL_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(model.DatabaseEntry.COL_DESCRIPTION));
            String url = cursor.getString(cursor.getColumnIndex(model.DatabaseEntry.COL_URL));
            String rating = cursor.getString(cursor.getColumnIndex(model.DatabaseEntry.COL_RATING));
            model movie = new model();
            movie.setId(id);
            movie.setTitle(title);
            movie.setDescr(description);
            movie.setUrl(url);
            movie.setRating(rating);
            moviesList.add(movie);
        }
        cursor.close();

        return moviesList;
    }

    ArrayList<model> run()
    {

        this.insertData(
                "1",
                "Iron Man",
                "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.",
                "8hYlB38asDY",
                "0"
        );

        this.insertData(
                "2",
                "Iron Man 2",
                "With the world now aware of his identity as Iron Man, Tony Stark must contend with both his declining health and a vengeful mad man with ties to his father's legacy.",
                "BoohRoVA9WQ",
                "0"
        );

        this.insertData(
                "3",
                "Iron man 3",
                "When Tony Stark's world is torn apart by a formidable terrorist called the Mandarin, he starts an odyssey of rebuilding and retribution.",
                "oYSD2VQagc4",
                "0"
        );

        this.insertData(
                "4",
                "Captain America: The First Avenger",
                "Steve Rogers, a rejected military soldier transforms into Captain America after taking a dose of a \"Super-Soldier serum\". But being Captain America comes at a price as he attempts to take down a war monger and a terrorist organization.",
                "JerVrbLldXw",
                "0"
        );

        this.insertData(
                "5",
                "Captain America: Winter Soldier",
                "As Steve Rogers struggles to embrace his role in the modern world, he teams up with a fellow Avenger and S.H.I.E.L.D agent, Black Widow, to battle a new threat from history: an assassin known as the Winter Soldier.",
                "tbayiPxkUMM",
                "0"
        );

        this.insertData(
                "6",
                "Captain America: Civil War",
                "Political involvement in the Avengers' affairs causes a rift between Captain America and Iron Man.",
                "dKrVegVI0Us",
                "0"
        );

        this.insertData(
                "7",
                "Thor",
                "The powerful, but arrogant god Thor, is cast out of Asgard to live amongst humans in Midgard (Earth), where he soon becomes one of their finest defenders.",
                "JOddp-nlNvQ",
                "0"
        );

        this.insertData(
                "8",
                "Thor: The Dark World",
                "When Dr. Jane Foster gets cursed with a powerful entity known as the Aether, Thor is heralded of the cosmic event known as the Convergence and the genocidal Dark Elves.",
                "npvJ9FTgZbM",
                "0"
        );

        this.insertData(
                "9",
                "Thor: Ragnarok",
                "Thor is imprisoned on the planet Sakaar, and must race against time to return to Asgard and stop Ragnarök, the destruction of his world, at the hands of the powerful and ruthless villain Hela.",
                "ue80QwXMRHg",
                "0"
        );

        this.insertData(
                "10",
                "The Avengers",
                "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                "hA6hldpSTF8",
                "0"
        );

        return this.getAllMovies();
    }
}