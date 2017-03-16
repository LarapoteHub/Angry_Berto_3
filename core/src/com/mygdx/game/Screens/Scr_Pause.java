package com.mygdx.game.Screens;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Buttons.Btn_Exit;
import com.mygdx.game.Buttons.Btn_Mute;
import com.mygdx.game.GameEngine;

/**
 * Created by 100VOL on 16/08/2016.
 */
public class Scr_Pause implements Screen {

    public Scr_Pause() {
    }

	@Override
	public void initComponents() {
		GameEngine.addButton(new Btn_Exit());
	}
}
