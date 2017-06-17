package com.adamzajdlik.activitylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public interface Response {
        void viewLoaded();
    }

    public static Response responder = null;

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

    @Override protected void onStart() {
        super.onStart();
    }

    @Override protected void onResume() {
        super.onResume();
        if (responder != null) {
            responder.viewLoaded();
        }
    }
}
