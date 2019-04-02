package com.bensadiku.guess.states;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.bensadiku.guess.GuessGame;
import com.bensadiku.guess.handler.Save;
import com.bensadiku.guess.ui.TextImage;

public class HighscoreState extends State {

    private TextImage backBtn;
    private TextImage highscoreText;


    private  long [] highscores;
    private String [] names;


    private Array<TextImage> textImageNames;
    private Array<TextImage> textImageScores;

    protected HighscoreState(GSM gsm) {
        super(gsm);
        init();
        backBtn = new TextImage("back", GuessGame.WIDTH/2, GuessGame.HEIGHT/6-100);
        highscoreText = new TextImage("score", GuessGame.WIDTH/2, GuessGame.HEIGHT-50);


        textImageNames = new Array<TextImage>(9);
        textImageScores = new Array<TextImage>(9);
        for(int i=0;i<10;i++){
          // textImageNames.add(new TextImage(i+1 +"",GuessGame.WIDTH/2-100,GuessGame.HEIGHT-700 +i*60));
           textImageNames.add(new TextImage(10-i +"",GuessGame.WIDTH/2-180,GuessGame.HEIGHT-680 +i*60));
            textImageScores.add(new TextImage(highscores[9-i]+"",GuessGame.WIDTH-100, GuessGame.HEIGHT-680 +i*60));
        }
    }

    public void  init(){
        Save.load();
        highscores = Save.gameData.getHighScores();
//        names = Save.gameData.getNames();


    }


    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();
            cam.unproject(mouse);

            if(backBtn.contains(mouse.x,mouse.y)){
                gsm.set(new TransitionState(gsm,this,new MenuState (gsm), TransitionState.Type.BLACK_FADE));
            }
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
        backBtn.render(sb);
        highscoreText.render(sb);


        for(int i=0;i<10;i++){
            textImageNames.get(i).render(sb);
            textImageScores.get(i).render(sb);
        }

        sb.end();

    }
}
