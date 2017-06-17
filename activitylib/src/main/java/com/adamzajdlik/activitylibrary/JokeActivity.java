package com.adamzajdlik.activitylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeView = (TextView) findViewById(R.id.tv_joke);

        String joke = getIntent().getStringExtra(Constants.TAG_JOKE_INTENT_EXTRA);

        if (joke == null || joke.equals("")) {
            joke = Constants.DISPLAY_JOKE_UNAVAILABLE;
        }

        jokeView.setText(joke);
    }
}
