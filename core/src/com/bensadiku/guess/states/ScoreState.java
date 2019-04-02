package com.bensadiku.guess.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bensadiku.guess.GuessGame;
import com.bensadiku.guess.handler.Save;
import com.bensadiku.guess.ui.Score;
import com.bensadiku.guess.ui.TextImage;

public class ScoreState extends  State {

    private TextImage image;
    private TextImage newHighScoreImg;

    //Audio
    private Sound sound;
    private Score score;



    private boolean newHighScore;//if it made it intohigh score table

    public ScoreState (GSM gsm, int score){
        super(gsm);
        init();
        image = new TextImage(Integer.toString(score), GuessGame.WIDTH/2, GuessGame.HEIGHT/2);
       // newHighScoreImg = new TextImage("New high score",GuessGame.WIDTH/2, GuessGame.HEIGHT/4);//debug

         sound=   GuessGame.assetManager.get("sound/jazzychords.mp3", Sound.class);

        sound.setLooping(1,false);
        sound.setVolume(1,1f);
      if(MenuState.musicState && score ==0){
          sound.play();
      }
    }



    @Override
    public void handleInput() {

        if(Gdx.input.justTouched()){

            gsm.set(new TransitionState(gsm,this,new MenuState(gsm), TransitionState.Type.BLACK_FADE));
        }
    }

    public void init(){
        newHighScore = Save.gameData.isHighScore(Save.gameData.getTempHighScore());
        if(newHighScore){
            Save.gameData.addHighScore(Save.gameData.getTempHighScore());
            Save.save();
        }
    }


    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        image.render(sb);

        if(!newHighScore){
            sb.end();
            return;
        }
       // newHighScoreImg.render(sb);
        sb.end();
    }
}
