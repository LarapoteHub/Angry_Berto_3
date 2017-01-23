package com.mygdx.game.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 06/09/2016.
 */
public class PlayerShoot extends Projectile {

    public PlayerShoot(Player player) {
        super();
        x=player.getX()+18;
        y=player.getY()+player.getHeight();

        this.setWidth(20);
        this.setHeight(10);

        vSpeed=800;

        damage = 1;

        remove=false;

}

    @Override
    public void draw() {
        GameEngine.batch.draw(Sprites.bullet_player, x, y, getWidth(), getHeight());
    }

    @Override
    public void decreaseLives(int lives) {

    }

    @Override
    public void move() {
        y += vSpeed * Gdx.graphics.getDeltaTime();
        if (y > MyGdxGame.HEIGHT + 10)
        	remove = true;
    }

    public void act(Array<Enemy> enemysList) {






    } //end action()

}
