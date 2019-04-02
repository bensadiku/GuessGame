package com.bensadiku.guess.ui;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bensadiku.guess.GuessGame;

public class Tile extends Box {

    protected TextureRegion light;
    protected TextureRegion dark;

    private  boolean selected;//lit up

    protected   float totalWidth;
    protected float totalHeight; // tW, tH, timer to create loading animation
    protected   float timer;
    protected float maxTime = 0.5f; // time it takes to load the tiles

    private boolean wrong;

    public Tile (float x, float y, float width, float height){
     this.x = x; // x,y pozicioni
     this.y  = y;
     this.totalWidth = width -8; // width,height, madhesia
     this.totalHeight = height -8;

     light = GuessGame.res.getAtlas("pack.png").findRegion("light");
     dark = GuessGame.res.getAtlas("pack.png").findRegion("dark");
    }

    public  void setSelected(boolean b){ // to set the selected tiles to light, by default they're all black
        selected =b;
    }

    public  void  setTimer (float t){
        timer =t;
    }

    public  void setMaxTime (float t){
        maxTime = t;
    }

    public  boolean isSelected (){
        return selected;
    }

    public  void  setWrong(){
        wrong = true;// sets the animation when you guess the wrong tile
    }

    public  void update(float dt){
        if (width < totalWidth && height < totalHeight) {
            timer += dt;
            width = (timer/ maxTime) * totalWidth;
            height = (timer/ maxTime) * totalHeight;

            if(width <0) width =0;
            if(height < 0) height =0;

            if(width > totalWidth)
                width = totalWidth;

            if(height > totalHeight)
                height = totalHeight;

        }
    }

    public  void render(SpriteBatch sb){
        if(selected){
            if(wrong){
                sb.setColor(1,0,0,1);
            }
            sb.draw(light, x - width / 2, y - height / 2, width, height);
            sb.setColor(1,1,1,1);
        }
        else{
            sb.draw(dark, x - width/2, y-height/2,width,height);

        }


    }

}
