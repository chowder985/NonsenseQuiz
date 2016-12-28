package com.appliepi.android.myapplication;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "Game";
    private static final int MILLISINFUTURE = 31 * 1000;
    private static final int COUNT_DOWN_INTERVAL = 1000;

    private EditText mUserAnswer;
    private Button mSubmit;
    private ImageView mQuestionImage;
    private TextView mScoreText;
    private TextView mTimer;
    private CountDownTimer mCountDownTimer;
    private TextView mLife;

    private Game[] mQuestionBank = new Game[]{
            new Game(R.drawable.pic1, "자녀"),
            new Game(R.drawable.pic2, "창세기"),
            new Game(R.drawable.pic3, "열매"),
            new Game(R.drawable.pic4, "피고인"),
            new Game(R.drawable.pic5, "양반"),
            new Game(R.drawable.pic6, "거북선"),
            new Game(R.drawable.pic7, "소방관"),
            new Game(R.drawable.pic8, "포크레인"),
            new Game(R.drawable.pic9, "갓길"),
            new Game(R.drawable.pic10, "춤사위"),
    };

    private int mCurrentIndex = 0;
    private int Score = 0;
    private int cnt = 30;
    private int life = 3;

    private void updateGame(){
        mQuestionImage.setImageResource(mQuestionBank[mCurrentIndex].getImageResId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mUserAnswer = (EditText) findViewById(R.id.user_answer);
        mSubmit = (Button) findViewById(R.id.submit);
        mQuestionImage = (ImageView) findViewById(R.id.question_image);
        mScoreText = (TextView) findViewById(R.id.score);
        mLife = (TextView) findViewById(R.id.life);

        mTimer = (TextView) findViewById(R.id.timer);
        mCountDownTimer();
        mCountDownTimer.start();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mQuestionBank[mCurrentIndex].getAnswer().equals(mUserAnswer.getText().toString())){
                    Score++;
                    mScoreText.setText(String.valueOf(Score));
                }else{
                    life--;
                    mLife.setText(String.valueOf(life));
                    if(life <= 0){
                        Intent intent = GameoverActivity.newIntent(GameActivity.this, Score);
                        startActivity(intent);
                        finish();
                    }
                }
                Log.d(TAG, String.valueOf(Score));
                mUserAnswer.setText(null);
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
                updateGame();
            }
        });
    }

    public void mCountDownTimer(){
        mCountDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long l) {
                mTimer.setText(String.valueOf(cnt));
                cnt--;
            }

            @Override
            public void onFinish() {
                Intent intent = GameoverActivity.newIntent(GameActivity.this, Score);
                startActivity(intent);
                finish();
            }
        };
    }
}
