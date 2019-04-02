package com.bensadiku.guess;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bensadiku.guess.handler.Content;
import com.bensadiku.guess.states.GSM;
import com.bensadiku.guess.states.MenuState;
import com.bensadiku.guess.states.PlayState;

public class GuessGame extends ApplicationAdapter {
	SpriteBatch sb;
	Texture img;


	public  static  final  int WIDTH = 480;
	public  static  final  String TITLE = "Guess Game";
	public  static  final  int HEIGHT = 800;

	public static Content res;
	public  static AssetManager assetManager;

	private GSM gsm;

	@Override
	public void create () {
		Gdx.gl.glClearColor(0.1f, 0.2f, 0.2f, 1);

		res = new Content();
		res.loadAtlas("pack.pack","pack.png");
		sb= new SpriteBatch();
		assetManager = new AssetManager();
		assetManager.load("sound/menumusic.mp3", Music.class);
		assetManager.load("sound/jazzychords.mp3", Sound.class);
		assetManager.finishLoading();

		gsm = new GSM();
		//gsm.push(new PlayState(gsm, PlayState.Difficulty.EASY));
		gsm.push(new MenuState(gsm));

	}

	@Override
	public void render () { // Game loop, 60fps
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime()); // thirr metoden e gsm update e cila bon update
		gsm.render(sb);
	}
	
	@Override
	public void dispose () {
		assetManager.dispose();
	}
}
