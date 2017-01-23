package com.mygdx.game.Multimedia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by 100VOL on 20/08/2016.
 */

/*
    TODO Intentar usar un Texture[][] para todas las texturas y un método "getTexture" con un
    TODO enum a ver si optimiza el rendimiento.
    TODO Intentarlo con un HashMap, mas facil de administrar todo, sin tener que declararlo en 2 lados.
 */

public class Sprites {
    // Nota: btn = Sprite que se usa para un boton
    // Si no contiene nada es la imagen (o imagenes) que se usan en si para el juego. (No clickeables)
    // Botones
    public static Sprite[] btn_exit;
    public static Sprite[] btn_init;
    public static Sprite[] btn_pause;

    public static Sprite[] btn_rotaryHoe;
    public static Sprite[] btn_dog;
    public static Sprite[] btn_plusHP;

    public static Sprite[] btn_achievements;
    public static Sprite[] btn_options;
    public static Sprite[] btn_scores;


    // Otros    -   Lo que no cuadra en ninguna categoria y son llaneros solitarios...
    // Efectos
    public static Sprite star;
    public static Sprite[] explosion;
    // Demás
    public static Sprite life;      // live = vivir (de "Dónde vives?")
    // Logos
    public static Sprite logo;
    public static Sprite gameOver;


    // Personajes
    public static Sprite berto;
    public static Sprite lina;

    // Naves ---
    public static Sprite[] player;
    public static Sprite[] player_propulsion;


    // Enemigos
    public static Sprite[] enemy_std;
    public static Sprite[] enemy_dodging;
    public static Sprite[] enemy_spikeBall;
    public static Sprite[] enemy_heavy;

    // Balas - Bullets
    public static Sprite bullet_player;
    public static Sprite bullet_enemy;
    public static Sprite[] bullet_heavy_enemy;

    // Esteroides...
    // TODO No olvidarse de tapar con un rectangulo la carga a la que corresponde en la pantalla de juego
    public static Sprite powerUp_charge_bar;
    public static Sprite powerUp_charge_increase;
    public static Sprite powerUp_rotaryHoe;
    public static Sprite powerUp_attackCow;

    public static Sprite boss_1;

    public static void load() {
        // TODO Se podría pasar todo para aquí en realidad....
    }

    // TODO Eliminar comentario si se va entregar...
    // OLD AND DEPRECATED!!!	#VICENTE
    // Solo usar de referencia en caso de necesidad.
    //BOTONES
/*
    public static Texture exitButtonMenuImage[];

    public static Texture initButtonMenuImage[];

    public static Texture liveImage;

    public static Texture pausePlayButtonImage [];

    //PERSONAJES

    public static Texture bertoImage;
    public static Texture linaImage;

    //ENEMIGOS

    public static Texture[] standardEnemyImage;

    public static Texture[] evadingEnemyImage;

    public static Texture[] spikeBallEnemyImage;

    public static Texture[] heavyEnemyImage;

    public static Texture standardEnemyShootImage;

    public static Texture[] heavyEnemyShootImage;


    //LOGOS

    public static Texture angryBertoLogoImage;

    //OTROS

    public static Texture gameOverImage;

    public static Texture starImage;

    public static Texture[] explodeImage;

    //JUGADOR:

    public static Texture playerImage[];

    public static Texture playerShootImage;

    public static Texture playerFireImage[];

    //POWERUPS

    public static Texture chargePowerUpImage;

    public static Texture[] livePowerUpImage;

    public static Texture[] hoePowerUpBtnImage;


    public static Texture[] rotaryHoePowerUpImage;

    public static Texture[] dogPowerUpBtnImage;

    public static Texture attackCowPowerUpImage;

    public static Texture[] chargeBarImage;

    public static Texture chargeBarContainerImage;



    public static Texture[] archievementsButtonMenuImage;

    public static Texture[] optionsButtonMenuImage;

    public static Texture[] scoresButtonMenuImage;

    /*

    Sprites.explodeImage = {new Texture(Gdx.files.internal("sprites/others/explosion/explode0.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode1.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode2.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode3.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode4.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode5.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode6.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode7.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode8.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode9.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode10.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode11.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode12.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode13.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode14.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode15.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode16.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode17.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode18.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode19.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode20.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode21.png")),
					new Texture(Gdx.files.internal("sprites/others/explosion/explode22.png"))};

     */
}
