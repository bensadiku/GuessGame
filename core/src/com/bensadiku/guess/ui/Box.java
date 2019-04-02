package com.bensadiku.guess.ui;

public class Box {

    protected   float x;
    protected   float y;
    protected   float width;
    protected   float height;

    public  boolean contains (float x, float y){
        return x > this.x  - width /2 &&
                x < this.x + width /2 &&
                y > this.y  - height /2 &&
                y < this.y + height /2;

    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
