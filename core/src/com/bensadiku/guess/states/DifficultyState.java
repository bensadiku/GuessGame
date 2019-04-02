package com.bensadiku.guess.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.bensadiku.guess.GuessGame;
import com.bensadiku.guess.ui.TextImage;

public class DifficultyState extends  State{

    private Array<TextImage> buttons;
    private TextImage backBtn;


    public DifficultyState(GSM gsm) {
        super(gsm);

        String [] texts = {"easy","normal","hard","insane"};

        buttons = new Array<TextImage>();

        for(int i=0 ;i < texts.length; i++){
            buttons.add(new TextImage(texts[i], GuessGame.WIDTH/2, GuessGame.HEIGHT/2 +100 -70 *i));
        }
        backBtn = new TextImage("back", GuessGame.WIDTH/2,100);

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();
            cam.unproject(mouse);
            for(int i=0;i <buttons.size;i++){
                if(buttons.get(i).contains(mouse.x,mouse.y)){
                    gsm.set(new TransitionState(gsm,this,new PlayState(gsm, PlayState.Difficulty.values()[i]), TransitionState.Type.EXPAND));
                }
            }
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
        for(int i=0; i< buttons.size;i++){
            buttons.get(i).render(sb);
        }
        backBtn.render(sb);
        sb.end();
    }
}
