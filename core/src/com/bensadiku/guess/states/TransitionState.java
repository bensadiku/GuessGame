package com.bensadiku.guess.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensadiku.guess.GuessGame;
import com.bensadiku.guess.ui.Expand;

public class TransitionState extends State {

    public  enum Type {
        BLACK_FADE,
        EXPAND;
    };

    private  State prev;
    private  State next;
    private  Type type;

    private TextureRegion dark;

    //black fade
      private float maxTime;
    private  float timer;
    private  float alpha;

    //expand fade
    private Expand[] [] expands;
    private  boolean doneExpanding;
    private boolean doneContracting;

    public TransitionState( GSM gsm,State prev, State next, Type type) {
        super(gsm);
        this.prev = prev;
        this.next = next;
        this.type = type;

        dark = GuessGame.res.getAtlas("pack.png").findRegion("dark");

        if(type == Type.BLACK_FADE){
            maxTime =1;
        }
        else if(type ==Type.EXPAND){
            int size =80;
            expands = new Expand[10][6];
            for(int row =0;row < expands.length; row++ ){
                for (int col =0; col < expands[0].length; col++){
                    expands[row][col] =  new Expand(col *size +size/2,
                            row *size +size/2,size,size);

                    expands[row][col].setTimer((-(expands.length -row)- col )* 0.05f);

                }
            }
        }
    }

    public void handleInput() {

    }


    public void update(float dt) {
        timer += dt;
        if(type == Type.BLACK_FADE){
            if(timer >= maxTime){
                gsm.set(next);
            }
        } else if (type == Type.EXPAND){
            if(!doneExpanding){
                boolean okay =true;
                for(int row =0;row < expands.length; row++ ) {
                    for (int col = 0; col < expands[0].length; col++) {
                        expands[row][col].update(dt);
                        if(!expands[row][col].isDoneExpanding()){
                            okay = false;
                        }
                    }
                }
                if(okay && !doneExpanding){
                    doneExpanding =true;
                    for(int row =0;row < expands.length; row++ ) {
                        for (int col = 0; col < expands[0].length; col++) {
                            expands[row][col].setContracting(-((expands.length -row) + col )* 0.05f);
                        }
                    }
                }
            }else {
                boolean okay =true;
                for(int row =0;row < expands.length; row++ ) {
                    for (int col = 0; col < expands[0].length; col++) {
                        expands[row][col].update(dt);
                        if(!expands[row][col].isDoneContracting()){
                            okay =false;
                        }
                    }
                }
                if(okay && !doneContracting){
                    doneContracting = true;
                    gsm.set(next);
                }
            }
        }
    }

    public void render(SpriteBatch sb) {
        if(type == Type.BLACK_FADE){
            if(timer <maxTime /2 ){
                alpha = timer / (maxTime /2);
                prev.render(sb);
            }
            else {
                alpha = 1- (timer / (maxTime /2));
                next.render(sb);
            }
            sb.setColor(0,0,0,alpha);
            sb.setProjectionMatrix(cam.combined);
            sb.begin();
            sb.draw(dark,0,0,GuessGame.WIDTH, GuessGame.HEIGHT);
            sb.end();
            sb.setColor(1,1,1,1);
        }
        else if(type == Type.EXPAND){
            if(!doneExpanding){
                prev.render(sb);
            }
            else {
                next.render(sb);
            }
            sb.setProjectionMatrix(cam.combined);
            sb.begin();
            for(int row =0;row < expands.length; row++ ){
                for (int col =0; col < expands[0].length; col++) {
                    expands[row][col].render(sb);
                }
            }
            sb.end();
        }
    }
}
