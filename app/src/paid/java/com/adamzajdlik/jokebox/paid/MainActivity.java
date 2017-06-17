package com.adamzajdlik.jokebox.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adamzajdlik.activitylibrary.Constants;
import com.adamzajdlik.activitylibrary.JokeActivity;
import com.adamzajdlik.jokebox.EndpointsAsyncTask;
import com.adamzajdlik.jokebox.R;

/**
 * Created by adamzajdlik on 2017-06-16.
 */

public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.AsyncResponse{
    private Intent intent;
    private ProgressBar progressBar;
    private TextView instructionsView;
    private FloatingActionButton showJokeButton;
    private TextView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        instructionsView = (TextView) findViewById(R.id.tv_instructions);
        showJokeButton = (FloatingActionButton) findViewById(R.id.button_joke);
        loadingView = (TextView) findViewById(R.id.tv_loading);

        intent = new Intent(MainActivity.this, JokeActivity.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    synchronized public void tellJoke(View view) {
        showLoading();

            EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
            asyncTask.delegate = this;
            asyncTask.execute();

    }

    @Override public void processResponse(String response) {
        intent.putExtra(Constants.TAG_JOKE_INTENT_EXTRA, response);
            showJoke();

    }

    private void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.VISIBLE);
        showJokeButton.setVisibility(View.GONE);
        instructionsView.setVisibility(View.GONE);
    }

    private void showButton(){
        progressBar.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        showJokeButton.setVisibility(View.VISIBLE);
        instructionsView.setVisibility(View.VISIBLE);
    }

    private void showJoke(){
        startActivity(intent);
    }

    @Override protected void onResume() {
        super.onResume();
        showButton();
    }

}
