package com.adamzajdlik.activitylibrary;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    View rootView;
    public interface Response {
        void viewLoaded();
    }

    public static Response responder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(null);
        }

        TextView jokeView = (TextView) findViewById(R.id.tv_joke);

        String joke = getIntent().getStringExtra(Constants.TAG_JOKE_INTENT_EXTRA);

        if (joke == null || joke.equals("")) {
            joke = Constants.DISPLAY_JOKE_UNAVAILABLE;
        }

        jokeView.setText(joke);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            rootView = findViewById(R.id.root_view);
            rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    rootView.removeOnLayoutChangeListener(this);
                    int centerX = rootView.getWidth() / 2;
                    int centerY = rootView.getHeight() / 2;
                    float startRad = 0f;
                    float endRad = (float) Math.hypot(centerX, centerY);
                    Animator anim = ViewAnimationUtils.createCircularReveal(rootView, centerX, centerY, startRad, endRad);
                    anim.start();
                }
            });
        }
    }


    @Override public void onBackPressed() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int centerX = rootView.getWidth() / 2;
            int centerY = rootView.getHeight() / 2;
            float startRad = 0f;
            float endRad = (float) Math.hypot(centerX, centerY);
            Animator anim = ViewAnimationUtils.createCircularReveal(rootView, centerX, centerY,endRad, startRad);
            anim.start();
            anim.addListener(new Animator.AnimatorListener() {
                @Override public void onAnimationStart(Animator animation) {

                }

                @Override public void onAnimationEnd(Animator animation) {
                    rootView.setVisibility(View.INVISIBLE);
                   JokeActivity.this.finish();
                }

                @Override public void onAnimationCancel(Animator animation) {

                }

                @Override public void onAnimationRepeat(Animator animation) {

                }
            });
        } else {
            super.onBackPressed();
        }
    }

    @Override protected void onStop() {
        super.onStop();
    }

    @Override protected void onResume() {
        super.onResume();
        if (responder != null) {
            responder.viewLoaded();
        }
    }
}
