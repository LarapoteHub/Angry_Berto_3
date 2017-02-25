package com.mygdx.game.Engine;

import com.mygdx.game.Multimedia.Musics;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 9/26/2016.
 */
public class GameStateManager {
	
	private GameState state;
	private boolean changed;
	private boolean saveState;

	public GameStateManager() {
		state = GameState.LOADING;
		saveState = false;
		changed = true;
	}
	
	public void pause() {
		state = GameState.PAUSED;
		saveState = true;
		changed = true;
	}
	
	public void unpause() {
		state = GameState.GAMEPLAY;
		saveState = true;
		changed = true;
	}
	
	/*public void score() {
		state = GameState.HIGH_SCORE;
	}
	*/
	
	public void play() {
		state = GameState.GAMEPLAY;
		//TODO 100vol estuvo aqui
		MyGdxGame.musicManager.setMusic(Musics.backgroundMusic);
		saveState = false;
		changed = true;
	}
	
	public void mainMenu() {
		state = GameState.MAIN_MENU;
		//TODO 100vol estuvo aqui
		MyGdxGame.musicManager.setMusic(Musics.backgroundMenuMusic);
		saveState = false;
		changed = true;
	}

	public void showIntro() { 
		state = GameState.INTRO;
		saveState = false;
		changed = true;
	}

	public void finishGame() { 
		state = GameState.GAME_END;
		saveState = false;
		changed = true;
	}

	public void loadGame() {
		state = GameState.LOADING;
		saveState = false;
		changed = true;
	}

	public void goToLevelSelection() {
		state = GameState.LEVEL_SELECTION;
		//TODO 100vol estuvo aqui
		MyGdxGame.musicManager.setMusic(Musics.backgroundLevelSelectionMusic);
		saveState = false;
		changed = true;
	}

	public GameState getState() {
		return state;
	}
	
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	
	public boolean isChanged() {
		return changed;
	}
	
	public boolean saveEntities() {
		return saveState;
	}

	public boolean isPlaying() { return state == GameState.GAMEPLAY;}
	public boolean isPaused() { return state == GameState.PAUSED;}
	public boolean isInMainMenu() { return state == GameState.MAIN_MENU;}
	public boolean isInEndGame() { return state == GameState.GAME_END;}
	public boolean isIntro() { return state == GameState.INTRO;}
	public boolean isLoading() { return state == GameState.LOADING; }
	public boolean isLevelSelection() {return state == GameState.LEVEL_SELECTION; }
	
	
}
