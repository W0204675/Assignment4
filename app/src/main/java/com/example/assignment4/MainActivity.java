package com.example.assignment4;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.content.DialogInterface;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    GridViewAdapter gridViewAdapter;
    ArrayList<model> databaseResults;
    FloatingActionButton addTrailerBtn;
    private Integer id;
    private String title;
    private String description;
    private String url;
    private String rating;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Database db = new Database(this);
        db.run();
        addTrailerBtn = findViewById(R.id.addTrailerBtn);
        databaseResults = db.getAllMovies();
        gridView = findViewById(R.id.gridView);
        gridViewAdapter = new GridViewAdapter(this, R.layout.item_layout, databaseResults);
        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model movies = (model) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, movie.class);
                intent.putExtra("title", movies.getTitle());
                intent.putExtra("url", movies.getUrl());
                intent.putExtra("descr", movies.getDescr());
                intent.putExtra("rating", movies.getRating());
                startActivity(intent);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, final long id) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select option");
                builder.setPositiveButton("Update Rating", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                        builder2.setTitle("Enter Rating");
                        final EditText ratingInput = new EditText(MainActivity.this);
                        ratingInput.setHint("Enter a number");
                        builder2.setView(ratingInput);
                        builder2.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(ratingInput.getText().toString().matches("^[0-9]*$")) {
                                    int movie = Integer.valueOf(db.getAllMovies().get(position).ID);
                                    Database.dbHelper.updateRating(movie, ratingInput.getText().toString());
                                    Toast.makeText(MainActivity.this, db.getAllMovies().get(position).title + "'s rating updated to: " + ratingInput.getText().toString(),
                                            Toast.LENGTH_LONG).show();
                                    Database db = new Database(MainActivity.this);
                                    db.run();
                                    gridView.setAdapter(new GridViewAdapter(MainActivity.this, R.layout.item_layout, db.getAllMovies()));
                                }
                                else{Toast.makeText(MainActivity.this, "Error! Rating not updated. Please try again with a number",
                                        Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder2.show();
                    }
                });

                builder.setNegativeButton("Remove Trailer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final AlertDialog.Builder builder3 = new AlertDialog.Builder(MainActivity.this);
                        builder3.setTitle("Are you sure you would like to remove " + db.getAllMovies().get(position).title + " from the database?\n");
                        builder3.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int movie = Integer.valueOf(db.getAllMovies().get(position).ID);
                                Database.dbHelper.deleteData(movie);
                                Database db = new Database(MainActivity.this);
                                db.run();
                                gridView.setAdapter(new GridViewAdapter(MainActivity.this, R.layout.item_layout, db.getAllMovies()));
                            }
                        });

                        builder3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder3.show();
                    }
                });
                builder.show();
                return true;
            }
        });

        addTrailerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = addTrailerBtn.getContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add Trailer\n");
                final EditText titleInput = new EditText(MainActivity.this);
                titleInput.setHint("Enter Title");
                titleInput.setInputType(InputType.TYPE_CLASS_TEXT);
                layout.addView(titleInput);
                final EditText descriptionInput = new EditText(MainActivity.this);
                descriptionInput.setHint("Enter Description");
                titleInput.setInputType(InputType.TYPE_CLASS_TEXT);
                layout.addView(descriptionInput);
                final EditText urlInput = new EditText(MainActivity.this);
                urlInput.setHint("Enter Youtube URL");
                titleInput.setInputType(InputType.TYPE_CLASS_TEXT);
                layout.addView(urlInput);
                final EditText ratingInput = new EditText(MainActivity.this);
                ratingInput.setHint("Enter Rating");
                titleInput.setInputType(InputType.TYPE_CLASS_TEXT);
                layout.addView(ratingInput);
                builder.setView(layout);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Database db = new Database(MainActivity.this);
                        id = db.getAllMovies().size() + 1;
                        title = titleInput.getText().toString();
                        description = descriptionInput.getText().toString();
                        if(urlInput.getText().toString().length() > 11)
                        {
                            url = urlInput.getText().toString().substring(urlInput.getText().toString().length() - 11);
                        }
                        else if(urlInput.getText().toString().length() == 11) {
                            url = urlInput.getText().toString();
                        }
                        rating = ratingInput.getText().toString();
                        Database.dbHelper.updateData(id, title, description, url, rating);
                        db.run();
                        gridView.setAdapter(new GridViewAdapter(MainActivity.this, R.layout.item_layout, db.getAllMovies()));
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }
}