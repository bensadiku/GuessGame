package com.bensadiku.guess.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Glow extends Tile {

    private  float alpha;
    private  float speed =200;


    private  boolean remove;
    public Glow(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.height = height;
        this.width = width;

    }

    public  boolean shouldRemove (){
        return  remove;
    }

    public   void  update(float dt){
        timer += dt;
        width +=dt * speed;
        height += dt * speed;
        if(timer >= maxTime){
            remove = true;
        }
    }

    public void  render(SpriteBatch sb){
        alpha = 1- timer/ maxTime;
        sb.setColor(1,1,1,alpha);
        sb.draw(light,x -width/2, y-height/2,width,height);
        sb.setColor(1,1,1,1);
    }


}
