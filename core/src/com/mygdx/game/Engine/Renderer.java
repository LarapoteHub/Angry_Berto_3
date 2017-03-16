/**
 * Esta parte deberia de encargarse de:
 * 			- Ver si hay que dibujar cada objeto (Osea, si esta dentro de la pantalla o no)
 * 			- Animar explosiones
 *			- Dibujar botones
 *			- Dibujar vidas
 *			- (BETA) Devolver canales alpha para la colision Pixel Perfect
 *			- (Recommended) Dibujar TODO lo que haya que dibujar
 * 			-
 */

package com.mygdx.game.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Buttons.Button;
import com.mygdx.game.Buttons.PowerUp_Button;
import com.mygdx.game.Entities.Enemies.Bossses.Boss;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.PlainAnimations.PlainAnimation;
import com.mygdx.game.Entities.Text;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Projectiles.Projectile;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Red Mercy on 9/26/2016.
 * 
 * Nota: Se puede optimizar mas... Haciendo uso del ArrayList
 * "entitiesToRender"...
 */
public class Renderer extends GameEngine {

	public Renderer() {
		printer = new BitmapFont(Gdx.files.internal("fonts/anime_ace2.fnt"));

		// Inicializamos el SpriteBatch (lo que nos dibuja los graficos en si)


	}

	public void draw() {
		updateCamera();
		batch.begin();

		drawBackground();
		drawOther();

		if ((gameState.isPlaying() || gameState.isPaused()) && player != null) {
			player.draw();
			
			drawEnemies();
			drawBosses();
			drawBullets();

			drawPlainAnimations();
			
			// Hay que llamar estas funciones en el orden correcto
			drawHUD();
			
		}

		
		if (MyGdxGame.DEBUG_MODE)
			System.out.println(buttons.size());

		drawButtons();

		drawOtherImages();
		drawText();


		batch.end();

	}

	private void drawBullets() {
		// Dibujar las balas aliadas
		for (Projectile p : bullets_Player) {
			p.draw();
		}
		// Dibujar las balas enemigas
		for (Projectile p : bullets_Enemy) {
			p.draw();
		}
	}

	private void drawText() {
		for (Text t : texts) {
			t.draw();
		}
	}

	private void drawButtons() {
		for (Button b : buttons.values()) {
			if (b.isPowerUp
					&& player.getCharge() >= ((PowerUp_Button) b).getCost()) {
				b.draw(1);
			} else if (b.isTouched(touchPos) && !b.isPowerUp) {
				b.draw(1);
			} else
				b.draw(0);

		}
	}

	// Dibujar las "Sólo imágenes"
	private void drawOtherImages() {
		for (Sprite spr : images) {
			batch.draw(spr.getTexture(), spr.getX(), spr.getY(),
					spr.getWidth(), spr.getHeight());
		}
	}

	private void drawEnemies() {
		for (Enemy e : enemies) {
			e.draw();
		}
	}

	private void drawBosses() {
		for (Boss b : bosses) {
			b.draw();
		}
	}

	// Dibujar el fondo acorde al estado actual del juego.
	private void drawBackground() {

		if (gameState.isInMainMenu()) {

			batch.draw(Backgrounds.backgroundMenuImage, 0, 0);

		} else if (gameState.isIntro()) {
			batch.draw(Backgrounds.backgroundIntro, 0, 0);
		} else if (gameState.isLevelSelection()) {
			batch.draw(Backgrounds.backgroundLevelSelection, 0, 0);
		} else if (gameState.isInEndGame()) {
			batch.draw(Backgrounds.backgroundGameOver, 0, 0);
		} else if (gameState.isPlaying() || gameState.isPaused()) {
			// Estirar el fondo en altura si no llega.
			if (uni.getBackground().getHeight() < MyGdxGame.HEIGHT)
				batch.draw(uni.getBackground(), 0, 0, uni.getBackground().getWidth(), MyGdxGame.HEIGHT);
			else
				batch.draw(uni.getBackground(), 0, 0);
		}

	}

	private void drawPlainAnimations() {
		Iterator<PlainAnimation> it = plainAnimations.iterator();
		while (it.hasNext()) {
			PlainAnimation pa = it.next();
			pa.draw();

			if (pa.isFinished()) {
				it.remove();
			}

		}
	}

	private void drawOther() {
		Iterator<Entity> it = otherEntities.iterator();
		while (it.hasNext()) {
			Entity e = it.next();
			e.draw();

		}
	}

	private void drawHUD() {
		batch.draw(Backgrounds.backgroundPowerUps, 0, 0);
		drawPlayerLives();
		drawPlayerCharge();
		//drawPlayerScore();
	}

	private void updateCamera() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		cam.update();

		// Lo que Dani dice que es importante:
		batch.setProjectionMatrix(cam.combined);
		sRenderer.setProjectionMatrix(cam.combined);
	}

}
