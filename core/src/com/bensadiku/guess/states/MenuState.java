package com.bensadiku.guess.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.bensadiku.guess.GuessGame;
import com.bensadiku.guess.ui.Graphic;
import com.bensadiku.guess.ui.TextImage;

public class MenuState extends  State {

    private Graphic title;
    private TextImage play;
    private TextImage highscore;


    private Array<Graphic> musicIcon;// 0 on, //1 OFF
    public static  boolean musicState=false; //false - music off, true music on
    //Audio
    private static Music music;
    private  float musicVol=0.5f;
    public  MenuState (GSM gsm){
        super(gsm);

        title = new Graphic(GuessGame.res.getAtlas("pack.png").findRegion("gg"),
                GuessGame.WIDTH/2,
                GuessGame.HEIGHT/2 +200);

        musicIcon = new Array<Graphic>(2);

        musicIcon.add(new Graphic(GuessGame.res.getAtlas("pack.png").findRegion("on"),
                GuessGame.WIDTH-30,
                GuessGame.HEIGHT -50));

        musicIcon.add(new Graphic(GuessGame.res.getAtlas("pack.png").findRegion("off"),
                GuessGame.WIDTH-30,
                GuessGame.HEIGHT -50));


        play = new TextImage("play",GuessGame.WIDTH/2, GuessGame.HEIGHT/2 -50);
        highscore = new TextImage("score",GuessGame.WIDTH/2, GuessGame.HEIGHT/2-200);

        music = GuessGame.assetManager.get("sound/menumusic.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(musicVol);
        if(musicState){
            music.play();
        }

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();
            cam.unproject(mouse);
            if(play.contains(mouse.x,mouse.y)){
                gsm.set(new TransitionState(gsm,this,new DifficultyState(gsm), TransitionState.Type.BLACK_FADE));
            }
            if(highscore.contains(mouse.x,mouse.y)){
                gsm.set(new TransitionState(gsm,this,new HighscoreState(gsm), TransitionState.Type.BLACK_FADE));
            }
            if(musicState && musicIcon.get(0).contains(mouse.x, mouse.y)){
                musicState = false;
               music.pause();

            }else if(!musicState && musicIcon.get(1).contains(mouse.x,mouse.y)){
                musicState =true;
                music.play();
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
       title.render(sb);
        play.render(sb);
        highscore.render(sb);
        if(musicState){
            musicIcon.get(0).render(sb); //for loop
        }
        else
            musicIcon.get(1).render(sb);


        sb.end();
    }

    public static  void  setMusic(boolean m){
        if(m){
            music.play();
        }else
            music.pause();

    }
}
