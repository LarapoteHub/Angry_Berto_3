package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Buttons.Btn_Exit;
import com.mygdx.game.Buttons.Btn_Pause;
import com.mygdx.game.Buttons.Button;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by 100VOL on 16/08/2016.
 */
public class Scr_Pause implements Screen {

    Vector3 touchPos;
    Rectangle exitButtonBox;


    public Scr_Pause() {
    }
//    public void draw(SpriteBatch batch, BitmapFont font) {
//
////        gameEngineInstace.drawBackground(batch, false);
//
//        //*******************************************************************************************************
//        //******************* DIBUJAMOS LOS OBJETOS PERO SIN INTERACTUAR CON ELLOS ******************************
//        //*******************************************************************************************************
//        // TODO Create "DRAW QUEUE" for the Renderer ????
//        //gameEngineInstace.drawObjects(batch, true, camera);
//
//        GameEngine.drawPlayerLives(batch);
//        // TODO Fix this
//        //gameEngineInstace.drawPlayerScore(batch, MyGdxGame.printer);
//
////        gameEngineInstace.drawPausePlayButton(batch, true);
//        batch.draw(Sprites.btn_exit[0], exitButtonBox.x, exitButtonBox.y, 64, 64);
//
//        //*******************************************************************************************************
//        //*******************************************************************************************************
//        //*******************************************************************************************************
//
//        if (Gdx.input.isTouched()) {
//            touchPos = new Vector3();
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            // TODO FCK!!
//            //camera.unproject(touchPos);
//
//
//            /*if (MyGdxGame.stateManager.isPaused() && btn_pause.isTouched(touchPos)
//                    && (TimeUtils.nanoTime() - MyGdxGame.lastPaused > 500000000)) {
//
//                playTouched = true;
//                exitTouched = false;
//
//
//            }
//*/
//            if (MyGdxGame.stateManager.getState() == com.mygdx.game.Engine.GameState.PAUSED && touchPos.x >= exitButtonBox.x && touchPos.x <= (exitButtonBox.x + 32) &&
//                    touchPos.y >= exitButtonBox.y && touchPos.y <= (exitButtonBox.y + 32)
//                    && (TimeUtils.nanoTime() - MyGdxGame.lastPaused > 500000000)) {
//
//                exitTouched = true;
//                playTouched = false;
//
//
//            }
//
//
//        }
//
//        if (!Gdx.input.isTouched()) {
//
//            if (exitTouched) {
//
//                exitTouched = false;
//                playTouched = false;
//                System.exit(0);
//
//            }
//
//        }
//
//    } //end pause()
	@Override
	public void initComponents() {
		GameEngine.addButton(new Btn_Exit());
		
	}
}
