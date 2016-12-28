package com.appliepi.android.myapplication;

/**
 * Created by ilhoon on 28/12/2016.
 */

public class Game {
    private int mImageResId;
    private String mAnswerTrue;

    public Game(int imageResId, String answerTrue){
        mImageResId = imageResId;
        mAnswerTrue = answerTrue;
    }

    public int getImageResId(){
        return mImageResId;
    }

    public String getAnswer(){
        return mAnswerTrue;
    }
}
