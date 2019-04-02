package com.bensadiku.guess.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.bensadiku.guess.GuessGame;
import com.bensadiku.guess.handler.Save;
import com.bensadiku.guess.ui.Glow;
import com.bensadiku.guess.ui.Score;
import com.bensadiku.guess.ui.TextImage;
import com.bensadiku.guess.ui.Tile;

public class PlayState extends  State {

    private  final int MAX_FINGERS =2;//max amount of touches

    public enum  Difficulty{
        EASY,
        NORMAL,
        HARD,
        INSANE;
    }

    private int level;
    private int maxLevel;
    private  Difficulty difficulty;
    private  int[] args;

    private Score score;
    private  float scoreTimer;

    private  Tile[] [] tiles;
    private int tileSize;
    private  float boardOffset;
    private int boardHeight;

    private Array<Tile> finished; // solution
    private Array<Tile> selected; // attempt to finish

    private  boolean showing;
    private  float timer;

    //level
    public TextureRegion light;
    public TextureRegion dark;

    //glow
    private Array<Glow> glows;

    //wrong tile selected
    private  boolean done;
    private  float wrongTileTimer;

    //back btn
    private TextImage backBtn;

    public PlayState(GSM gsm, Difficulty difficulty){
        super(gsm);

        level =4;
        this.difficulty  = difficulty;
        finished = new Array<Tile>();
        selected = new Array<Tile>();


         args = getArgs(); // numers of rows and cols and guess tiles
        createBoard(args[0],args[1]);

        createFinished(args[2]);

        score = new Score(GuessGame.WIDTH /2, GuessGame.HEIGHT -50);

        light = GuessGame.res.getAtlas("pack.png").findRegion("light");
        dark = GuessGame.res.getAtlas("pack.png").findRegion("dark");

        glows = new Array<Glow>();

        backBtn = new TextImage("back",GuessGame.WIDTH/2, 100);
        //MenuState.setMusic(false);

    }


    private  int [] getArgs (){
        int [] ret = new int[3];
        if(difficulty == Difficulty.EASY){
            ret[0] =3;
            ret[1] =3;
            if(level >=1 && level <= 3){ /// SWITCH STATEMENT
                ret[2] =3;
            }
            else if(level ==4 || level ==5){
                ret[2] =4;
            }
                maxLevel =5;
        }
        if(difficulty == Difficulty.NORMAL){
            ret[0] =4; // 4x4 RECT
            ret[1] =4;
            if(level ==1 || level ==2){
                ret[2] =4;// increases tiles to 4
            }
            if(level ==3 || level ==4){
                ret[2] =5;
            }
            if(level ==5 || level ==6  ){
                ret[2] = 6;
            }
                maxLevel =6;
        }
        if(difficulty == Difficulty.HARD){
            ret[0] =5;
            ret[1] =5;
            if(level ==1 || level ==2){
                ret[2] =6;// increases tiles to 4
            }
            if(level ==3 || level ==4){
                ret[2] =7;
            }
            if(level ==5 || level ==6  ){
                ret[2] = 8;
            }
            if(level ==7 || level ==8  ){
                ret[2] = 9;
            }
            maxLevel =8;
        }
        if(difficulty == Difficulty.INSANE){
            ret[0] =6;
            ret[1] =6;
            if(level ==1 || level ==2){
                ret[2] =7;// increases tiles to 4
            }
            if(level ==3 || level ==4){
                ret[2] =8;
            }
            if(level ==5 || level ==6  ){
                ret[2] = 9;
            }
            if(level ==7 || level ==8  ){
                ret[2] = 10;
            }
            if(level ==9 || level ==10  ){
                ret[2] = 11;
            }
            maxLevel =10;
        }
        return  ret;
        //0 number of rows, 1 number of colums, 2 number of tiles to guess
    }


    private  void createBoard(int numRows, int numCols){
        //480 width
        //800 height
        tiles  = new Tile[numRows][numCols]; // 4x4
        tileSize = GuessGame.WIDTH / tiles[0].length;
        boardHeight = tileSize * tiles.length;
        boardOffset = (GuessGame.HEIGHT - boardHeight) /2;
        for (int row =0; row < tiles.length ; row ++){
            for(int col =0; col< tiles[0].length; col++){
                tiles[row][col] =

                        new Tile(col * tileSize + tileSize/2,
                                row * tileSize +boardOffset + tileSize/2,
                                tileSize, tileSize  );

                tiles[row][col].setTimer((-(tiles.length -row)- col )* 0.1f);
            }
        }
    }

    public  void  createFinished(int numTilesToLight){
        showing = true;
        timer =0;
        scoreTimer =5;
        wrongTileTimer=0;
        selected.clear();
        finished.clear();

        for(int i =0; i< numTilesToLight; i++){
            int row=0;
            int col=0;
            do {
                row = MathUtils.random(tiles.length-1);
                col = MathUtils.random(tiles[0].length-1);
            }while (finished.contains(tiles[row][col],true));
            finished.add(tiles[row][col]);
            tiles[row][col].setSelected(true);
        }
    }

    public void checkShowing(float dt){
        if(showing){
            timer += dt;
            if(timer > 2){
                if( timer % 0.15f < 0.07f){
                    for(int i =0; i< finished.size;i++){
                        finished.get(i).setSelected(true);
                    }
                }
                else {
                    for(int i =0; i< finished.size;i++){
                        finished.get(i).setSelected(false);
                    }
                }
            }
            if(timer > 4){
                showing =false;
                for(int i =0; i< finished.size;i++){
                    finished.get(i).setSelected(false);
                }
            }
        }
    }

    public  boolean isFinished(){
        for(int i=0; i<finished.size;i++){
            Tile tf = finished.get(i);
            if(!selected.contains(tf, true)){
                return false;
            }
        }
        return true;
    }

    public  void done (){
        Save.gameData.setTempHighScore(score.getScore());
        gsm.set(new TransitionState(gsm,this,new ScoreState(gsm,score.getScore()), TransitionState.Type.BLACK_FADE));
        return;
    }

    @Override
    public void handleInput() {
        for(int i=0; i < MAX_FINGERS; i++){

            if(!showing &&!done && Gdx.input.isTouched(i)){
                mouse.x = Gdx.input.getX(i);
                mouse.y = Gdx.input.getY(i);
                cam.unproject(mouse); // game coordinates

                if(mouse.x > 0 && mouse.x < GuessGame.WIDTH && mouse.y > boardOffset && mouse.y < boardOffset + boardHeight) {
                int row = (int) ((mouse.y - boardOffset) / tileSize);
                int col = (int) (mouse.x / tileSize);
                    if(!tiles[row][col].isSelected ()){
                        tiles[row][col] .setSelected(true);
                        selected.add(tiles[row][col]);
                        glows.add(new Glow(tiles[row][col].getX(),tiles[row][col].getY(),tileSize,tileSize));
                        if(isFinished()){
                            done =true;
                            level ++;
                            int incr =(int) (scoreTimer *10); //points won per timer
                            int decr =(int) 5 *(selected.size -finished.size); // point lost per wrong tile && late timer
                            for(int t=0; t<selected.size;t++){
                                Tile tile = selected.get(t);
                                if(!finished.contains(tile,true)){
                                    tile.setWrong();
                                }
                            }
                            if(decr ==0){
                                wrongTileTimer =1;
                            }
                            score.incrementScore(incr-decr);
                        }
                    }
                }
            }
            if(Gdx.input.justTouched()){ //back btn, to be clicked even when not showing or dragging
                mouse.x = Gdx.input.getX();
                mouse.y = Gdx.input.getY();
                cam.unproject(mouse);

                if(backBtn.contains(mouse.x, mouse.y)){
                    gsm.set(new TransitionState(gsm,this,new DifficultyState(gsm), TransitionState.Type.BLACK_FADE));
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

        checkShowing(dt);

        score.update(dt);
        if(done){
            wrongTileTimer += dt;
            if(wrongTileTimer >=1 && glows.size ==0 && score.isDoneIncrementing()){
                args = getArgs();
                createBoard(args[0],args[1]);
                createFinished(args[2]);
                done =false;
                if(level > maxLevel){
                    done();
                }
            }
        }

        if(!showing){
            scoreTimer -=dt;
        }
        for(int i=0; i < glows.size;i++){
            glows.get(i).update(dt);
            if(glows.get(i).shouldRemove()){
                glows.removeIndex(i);
                i--;
            }
        }

        for (int row =0; row < tiles.length ; row ++) {
            for (int col = 0; col < tiles[0].length; col++) {
                tiles[row] [col].update(dt);
            }
        }


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        score.render(sb);
        backBtn.render(sb);
        int totalWidth = 10 * ( 2 *maxLevel -1);
        for(int i=0; i<maxLevel; i++){
            if(i < level){
                sb.draw(light,(GuessGame.WIDTH - totalWidth)/2 + i * 20, GuessGame.HEIGHT -100,10,10);
            }
            else{
                sb.draw(dark,(GuessGame.WIDTH - totalWidth)/2 + i * 20, GuessGame.HEIGHT -100,10,10);

            }
        }

        for (int row =0; row < tiles.length ; row ++){
            for(int col =0; col< tiles[0].length; col++){
                tiles[row] [col].render(sb);
            }
        }

        for(int i=0; i < glows.size;i++){
            glows.get(i).render(sb);
        }

        sb.end();
    }
}
