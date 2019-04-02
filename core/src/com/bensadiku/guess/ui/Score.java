package com.bensadiku.guess.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score extends TextImage{

    private  int score; // vlerat momentale
    private  int destScore; //vlera e ardhshme
    private  int speed = 100; // shpejtesia/koha ne te cilen score do te rrit

    public Score( float x, float y) {
        super("0", x, y);
    }

    public  void  incrementScore(int i ){
        destScore = score + i;
        //score +=i;
        if(destScore <0){
            destScore =0;
        }
    }

    public  void  update(float dt){
        if(score < destScore){
            score += (int) (speed *dt);
            if(score > destScore){
                score = destScore;
            }
        }
        else if(score < destScore){
            score -= (int) (speed * dt);
            if(score < destScore){
                score = destScore;
            }
        }
            setText(Integer.toString(score));
    }


    public int getScore(){
        return score;
    }
    public boolean isDoneIncrementing(){return  score == destScore;}
}
