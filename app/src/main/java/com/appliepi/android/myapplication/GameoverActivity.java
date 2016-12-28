package com.appliepi.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameoverActivity extends AppCompatActivity {
    private static final String EXTRA_FINAL_SCORE = "com.applepi.android.myapplication";

    private int finalScore;

    public static Intent newIntent(Context packageContext, int score){
        Intent i = new Intent(packageContext, GameoverActivity.class);
        i.putExtra(EXTRA_FINAL_SCORE, score);
        return i;
    }

    TextView mFinalScore;
    Button mPlayAgainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        mFinalScore = (TextView) findViewById(R.id.final_score);
        mPlayAgainBtn = (Button) findViewById(R.id.play_again_btn);

        finalScore = getIntent().getIntExtra(EXTRA_FINAL_SCORE, 0);
        mFinalScore.setText(String.valueOf(finalScore));

        mPlayAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameoverActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
