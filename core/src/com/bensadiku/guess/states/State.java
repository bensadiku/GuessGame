package com.bensadiku.guess.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.bensadiku.guess.GuessGame;

public abstract class State {

    protected GSM gsm;
    protected OrthographicCamera cam;
    protected Vector3 mouse;

    protected State(GSM gsm){
        this.gsm =gsm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, GuessGame.WIDTH,GuessGame.HEIGHT);
        mouse = new Vector3();
    }

    public  abstract  void  handleInput();
    public  abstract  void update(float dt);
    public abstract void render(SpriteBatch sb);
}
