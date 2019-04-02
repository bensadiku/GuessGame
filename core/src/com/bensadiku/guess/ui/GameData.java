package com.bensadiku.guess.ui;


import java.io.Serializable;

public class GameData implements Serializable {

    private static final long serialVersionUID =1;

    private final int MAX_SCORES =10;//TOP 10 highscoret
    private long[] highScores;
    private String[] names;

    private long tempHighScore; //possible high score, nese ka pike me shume se highscoret

    public GameData (){

        highScores = new long[MAX_SCORES];
        names = new String[MAX_SCORES];

    }

    //sets up an empty high score table
    public void init(){
        for(int i=0; i<MAX_SCORES;i++){
            highScores[i]=0;
            names[i] = "x";
        }
    }

    public  long [] getHighScores (){
        return highScores;
    }

    public String[] getNames() {
        return names;
    }

    public long getTempHighScore() {
        return tempHighScore;
    }

    public void setTempHighScore( int i){
        tempHighScore = i;
    }

    //metod me kqyr nese tempHightscore mrrin ne top 10
    public boolean isHighScore(long score ){
        return score > highScores[MAX_SCORES-1]; // temp score duhet me kan ma e madhe se highscore e fundit [MAXSCORE-1] =9
    }

    public void addHighScore(long newScore){
        if(isHighScore(newScore))//thirr metoden paraprake edhe krahason score
        {
            highScores[MAX_SCORES-1] = newScore;
            sortHighScores();
        }
    }

    public void sortHighScores(){ //bubble sort algoritmi,moves each score up and checks if it's correct
        for (int i=0; i<MAX_SCORES;i++) {
            long score = highScores[i];
            int j;
            for(j=i-1; j>=0 && highScores[j]<score;j--){
                highScores[j+1] = highScores[j];
            }
            highScores[j+1] = score;
        }
    }
}
