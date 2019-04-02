package com.bensadiku.guess.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Expand extends Tile {

    private  boolean expanding;
    private  boolean contracting;

    public  Expand (float x, float y, float width, float height){
        super(x,y,width,height);
        totalWidth +=8;
        totalHeight += 8;
        expanding =true;
        //contracting =false;
    }

    public boolean isDoneExpanding(){
            return  width == totalWidth && height == totalHeight;
    }
    public  boolean isDoneContracting(){
        return width == 0 &&height ==0;
    }

    public  void setContracting(float timer){
        this.timer = timer;
        expanding = false;
        contracting = true;
    }

    @Override
    public void update(float dt) {
        if(expanding){
            super.update(dt);
        }
        else if (contracting){
            timer += dt;
            width =totalWidth * ( 1- (timer / maxTime)) ;
            height =totalHeight * (1- (timer / maxTime)) ;

            if(width <0) width =0;
            if(height < 0) height =0;

            if(width > totalWidth)
                width = totalWidth;

            if(height > totalHeight)
                height = totalHeight;  if(width <0) width =0;

        }
    }

    public void  render(SpriteBatch sb){
        sb.setColor(0,0,0,1);
        sb.draw(dark, x-width /2,y -height/2, width,height);
        sb.setColor(1,1,1,1);
    }
}
