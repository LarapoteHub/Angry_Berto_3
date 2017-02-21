package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.Multimedia.Musics;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by 100VOL on 19/08/2016.
 */
public class Scr_GameOver implements Screen {

	// Texture background;
	// Texture gameOverImage;
	int gameOverX;
	int gameOverY;
	boolean touched;
	boolean selected;

	public Scr_GameOver() {

		// gameOverImage = new
		// Texture(Gdx.files.internal("sprites/others/gameOver.png"));
		gameOverX = 90;
		gameOverY = 500;
		touched = true;
		selected = false;
		// background = new
		// Texture(Gdx.files.internal("backgrounds/backgroundGameOver.png"));

	}

	public void draw() {

	}

	@Override
	public void initComponents() {

		try {
			GameEngine.addText(new Text("TOUCH THE SCREEN", 85, 200, Color.WHITE));

			Sprite spr = new Sprite(Sprites.gameOver);
			spr.setPosition(90, 500);
			spr.setSize(300, 200);

			GameEngine.addImage(spr);

			GameEngine.addText(new Text("SCORE: " + GameEngine.getLastScore(), 100, 400, Color.RED));

		} catch (Exception ex) {
			System.out.println("Excepción en Scr_GameOver.");
			ex.printStackTrace();
		}

	}
}
