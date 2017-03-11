package com.mygdx.game.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 10/25/2016.
 */
public class Btn_Pause extends Button {
    // Cosas antiguas
//    pausePlayButtonBox.width = 64;
//    pausePlayButtonBox.height = 64;
//    pausePlayButtonBox.x = (480 - pausePlayButtonBox.width) - 5;
//    pausePlayButtonBox.y = (800 - pausePlayButtonBox.height) - 5;

    public Btn_Pause() {
        super(MyGdxGame.WIDTH - 64 - 5, MyGdxGame.HEIGHT - 64 - 5, 64, 64, "btn_pause");
        name = "btn_pause";
    }

    @Override
    public void onTouch() {
    	if (GameEngine.gameState.isPlaying()) {
            //esto hace que si el player se ha pasado el nivel, no se pueda pasar hasta que termine.
            if (!GameEngine.getPlayer().getVictory()) {
                GameEngine.gameState.pause();
            }
        }
    	else if (GameEngine.gameState.isPaused()) {
    		GameEngine.gameState.unpause();
    	}
    }
    
    @Override
    public boolean isTouched(Vector3 touchCoords) {
        if (Gdx.input.justTouched()) {
            if (touchCoords.x >= x - margin && touchCoords.x <= x + width + margin
                    && touchCoords.y >= y - margin && touchCoords.y <= y + height + margin){
            	onTouch();
                return true;
            }
        }

        return false;
    }
    
    @Override
    public void draw(Integer texIndex) {
    	if (GameEngine.gameState.isPaused())
    		GameEngine.batch.draw(textures[1].getTexture(), x, y, width, height);
    	else if (GameEngine.gameState.isPlaying())
    		GameEngine.batch.draw(textures[0].getTexture(), x, y, width, height);
    }

}
