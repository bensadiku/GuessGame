package com.bensadiku.guess.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bensadiku.guess.GuessGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GuessGame.WIDTH/2;
		config.height = GuessGame.HEIGHT/2;
		config.title = GuessGame.TITLE;
			new LwjglApplication(new GuessGame(), config);
	}
}
