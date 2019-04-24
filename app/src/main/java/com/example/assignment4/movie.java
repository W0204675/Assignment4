package com.example.assignment4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class movie extends YouTubeBaseActivity {
    YouTubePlayerView videoViewTrailer;
    Button play;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    TextView title, description;
    String rating;
    TextView ratingBar;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_player);
        title = findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("title"));
        description = findViewById(R.id.trailerDescription);
        description.setText(getIntent().getStringExtra("descr"));
        final String url = getIntent().getStringExtra("url");
        videoViewTrailer = (YouTubePlayerView) findViewById(R.id.movieTrailer);
        play = findViewById(R.id.Play);
        rating = getIntent().getStringExtra("rating");
        ratingBar = findViewById(R.id.ratingTv);
        ratingBar.setText(ratingBar.getText().toString() + " " + rating);
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(url);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoViewTrailer.initialize(YoutubeConfig.getApiKey(), mOnInitializedListener);
            }
        });
    }
}