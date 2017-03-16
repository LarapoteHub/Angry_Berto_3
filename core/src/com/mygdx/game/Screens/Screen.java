package com.mygdx.game.Screens;

/**
 * Created by Red Mercy on 10/24/2016.
 */
public interface Screen {
	/**
	 * Para cargar una pantalla simplemente hay que usar esta función. Ni siquiera hace falta guardar
	 * la Screen en una variable. Hecho asi y no estática por problemas con Java al intentar hacerlo.
	 */
	public void initComponents();
}
