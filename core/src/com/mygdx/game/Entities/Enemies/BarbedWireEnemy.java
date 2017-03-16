package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameEngine;
import com.mygdx.game.GameEngine.EnemyType;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

import java.util.Random;

/**
 * Created by LordXamon on 09/08/2016.
 */
public class BarbedWireEnemy extends Enemy {

    /* DESCRIPCIÓN: PENDENTE DE IMPLEMENTACIÓN

    es un enemigo rectangular (16:9 o 16:10?) que spawnea en orientación vertical u horizontal, sin diagonales.
    Inmortal, que causa daño moderado y puede spawnear en sus extremos otro enemigo del mismo tipo, con orientación
    vertical u horizontal. los hijos no spawnean más
    */

    private Behavior.BarbedWireEnemy behavior;
    private Sprite spr;

    public BarbedWireEnemy(float x, float y, Behavior.BarbedWireEnemy behavior) {

        super(x, y);
        this.vSpeed = -100; //velocidad vertical (hSpeed= velocidad horizontal)
        this.width = 96;//Ancho
        this.height = 24; //Alto
        this.behavior=behavior;//nin zorra idea do que fai isto

        //las balas los atraviesan
        this.isTrascendental = true;

        damage=1;//dano que realiza ao xogador

        type = EnemyType.BARBEDWIRE;
        spr=new Sprite(Sprites.getSpriteByName("enemy_barbedWire")[0]);
        spr.setBounds(x, y, width, height);

        BarbedWireEnemy barbedWireChild=null;
        switch (new Random().nextInt(4)){//sí, xa sei que isto é confuso de carallo //PENDENTE DE OPTIMIZACIÓN>xamon
            case 0: barbedWireChild= new BarbedWireEnemy(x,y+24-96,Behavior.BarbedWireEnemy.DEFAULT,true);break;
            case 1: barbedWireChild= new BarbedWireEnemy(x,y,Behavior.BarbedWireEnemy.DEFAULT,true);break;
            case 2: barbedWireChild= new BarbedWireEnemy(x+96-24,y,Behavior.BarbedWireEnemy.DEFAULT,true);break;
            case 3: barbedWireChild= new BarbedWireEnemy(x+96-24,y+24-96,Behavior.BarbedWireEnemy.DEFAULT,true);break;
        }
        GameEngine.getEnemies().add(barbedWireChild);

    }

    public BarbedWireEnemy(float x, float y, Behavior.BarbedWireEnemy behavior,boolean isFirstChild) {//adaptar a partir de lo de arriba
        super(x, y);
        this.vSpeed = -100; //-50
        this.behavior=behavior;
        this.damage=1;
        this.type = EnemyType.BARBEDWIRE;

        //las balas los atraviesan
        this.isTrascendental = true;

        if (isFirstChild) {
            setWidth(24);
            setHeight(96);//sí, están invertidos. the magic of the SCIENCE!! bitch!
        }else {
            setWidth(96);
            setHeight(24);
        }

        spr=new Sprite(Sprites.getSpriteByName("enemy_barbedWire")[0]);
        spr.setBounds(x, y, width, height);

        if (isFirstChild) {
            spr.rotate90(true);
            BarbedWireEnemy barbedWireChild=null;
            switch (new Random().nextInt(4)){//sí, xa sei que isto hé confuso de carallo
                case 0: barbedWireChild= new BarbedWireEnemy(x+24-96,y,Behavior.BarbedWireEnemy.DEFAULT,false);break;
                case 1: barbedWireChild= new BarbedWireEnemy(x,y,Behavior.BarbedWireEnemy.DEFAULT,false);break;
                case 2: barbedWireChild= new BarbedWireEnemy(x,y+96-24,Behavior.BarbedWireEnemy.DEFAULT,false);break;
                case 3: barbedWireChild= new BarbedWireEnemy(x+24-96,y+96-24,Behavior.BarbedWireEnemy.DEFAULT,false);break;
            }
            GameEngine.getEnemies().add(barbedWireChild);
        }

    }


    public void draw () {

        spr.setPosition(x,y);
        spr.draw(GameEngine.batch);

    }

    @Override
    public void initAnimation() {

    }

    @Override
    public void runBehavior() {

    }

    //SOBRESCRITO PARA QUE SEA INMORTAL
    @Override
    public void kill() {
    }

    //SOBRESCRITO PARA QUE SEA INMORTAL
    @Override
    public void decreaseLives(float lives) {
    }

    @Override
    public boolean canShoot() {
        return false;
    }

    @Override
    public void shoot() {
        // Se deja vacio, ya que son alambradas. NO DISPARAN... por ahora
    }

}
