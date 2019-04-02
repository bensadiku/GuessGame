package com.bensadiku.guess.handler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.bensadiku.guess.ui.GameData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Save {


    public  static GameData gameData;
    public Save (){

    }


    public static void save(){
        if(Gdx.app.getType()== Application.ApplicationType.Desktop)

        {

            try{
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("score/highscores.save"));
                outputStream.writeObject(gameData);
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
                Gdx.app.exit();
            }
        }
        else if(Gdx.app.getType() == Application.ApplicationType.Android){

            try{
                ObjectOutputStream outputStream = new ObjectOutputStream(new com.bensadiku.guess.handler.FileHandle("score/highscores.save", Files.FileType.Local));
                outputStream.writeObject(gameData);
                outputStream.close();

                //System.out.println (gameData.getNames().toString() + "" + gameData.getHighScores().toString());
            }catch (Exception e){
                e.printStackTrace();
                Gdx.app.exit();
            }
        }
    }



    public  static void load(){
        if(Gdx.app.getType() == Application.ApplicationType.Desktop ){
            try{
                if(!saveFileExists()){

                    innit();
                    return;
                }
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("score/highscores.save"));
                gameData = (GameData) in.readObject();
                in.close();
            }catch (Exception e){
                e.printStackTrace();
                Gdx.app.exit();
            }
        }
        else if (Gdx.app.getType() == Application.ApplicationType.Android){
            try{
                if(!saveFileExists()){

                    innit();
                    return;
                }
               // FileHandle fileHandle = Gdx.files.local("score/highscores.save");
                //fileHandle.read();

                ObjectInputStream in = new ObjectInputStream(new FileInputStream("score/highscores.save"));
                gameData = (GameData) in.readObject();
                in.close();

            }catch (Exception e){
                e.printStackTrace();
                Gdx.app.exit();
            }
        }


    }

    public static  boolean saveFileExists()  {

            File f = new File ("score/highscores.save");

            return f.exists();



    }

    public  static void innit (){
        gameData = new GameData();
        gameData.init();
        save();

    }

/*

save

 else if(Gdx.app.getType() == Application.ApplicationType.Android){

            try{

                ObjectOutputStream outputStream = new ObjectOutputStream(new com.bensadiku.guess.handler.FileHandle("score/highscores.save", Files.FileType.Local));

                outputStream.writeObject(gameData);
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
                Gdx.app.exit();
            }
        }

load

 else if (Gdx.app.getType() == Application.ApplicationType.Android){
            try{
                if(!saveFileExists()){

                    innit();
                    return;
                }
                FileHandle fileHandle = Gdx.files.local("score/highscores.save");
                fileHandle.read();
                 //ObjectInputStream in = new ObjectInputStream(new FileInputStream(("score/highscores.save")));
                // gameData = (GameData) in.readObject();
                // in.close();



            }catch (Exception e){
                e.printStackTrace();
                Gdx.app.exit();
            }
        }


 */


}
