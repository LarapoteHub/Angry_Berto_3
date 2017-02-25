package com.mygdx.game.Entities.PowerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Projectiles.Projectile;

import java.util.Iterator;

/**
 * Created by 100VOL on 20/08/2016.
 */
public class RotaryHoe extends Projectile {
    Iterator<Enemy> collisionEnemys;
    GameEngine gameEngineInstance;
    Enemy enContainer;
    Sprite spr;
    // TODO Esto no funca bien...
    private float targetX, targetY, accelX = 0, step;

    float angle = 0;
    private double a, b, c;

    public RotaryHoe(float x, float y) {
        this.x = x;
        this.y = y;
        initSprite();
        initCurve();

        this.setWidth(64);
        this.setHeight(64);
        this.damage = 3;
        this.angle = 0;


        Sounds.rotaryHoeSound.play();

        vSpeed = 1000;

    }

    private void initSprite() {
        this.spr = new Sprite(Sprites.powerUp_rotaryHoe);
        spr.rotate(MathUtils.random(0, 350));

        x -= 6;
        y -= 6;
        spr.setPosition(x, y);
    }

    private void initCurve() {
        targetX = this.x;
        targetY = y + 500;

        //angle = MathUtils.random(10.0f, 13f);
        //angle = MathUtils.random(10, 13);
        angle = 10;
        System.out.println(angle);
        if (MathUtils.randomBoolean()) {
            angle = -angle;
        }


        double tg = Math.tan(angle);
        double yCuad = y * y;
        double targetYCuad = targetY * targetY;
        double div = yCuad - 2 * y - targetYCuad + 2 * targetY;


        a = -y*tg + targetY*tg + x - targetX;
        a /= div;

        b = yCuad * tg - targetYCuad*tg - 2 * x + 2 * targetX;
        b /= div;

        c = yCuad * targetY * tg + yCuad *(-targetX) - y*targetYCuad*tg;
        c += 2 * y * targetX + targetYCuad * x - 2 * targetY * x;
        c /= -div;

        //region FORMULAS CURVA

        //y=a*x^2+b*x+c

        /*
        punto1=x(1,y1)
        dx/dt=1=======cos(alpha)
        dy/dt=2*a+b======sen(alpha)
        ******2*a+b=tan(alpha)


        b = tan(alpha) - 2*a

        *****y2=a*x2^2+b*x2+c
        * y2 = a*x2^2 + (tan(alpha) - 2*a) + c
        * y2 - c = a*x2^2 + (tan(alpha) - 2*a)
        * c = -(a*x2^2 + (tan(alpha) - 2*a) - y2)
        *
        *
        *****y1=a*x1^2+b*x1+c
        *
        * t = tan(alpha)
        * x3 = x1^2
        * x4 = x2^2
        *
        * y1 = a*x1^2 + (tan(alpha) - 2*a) + (- (a*x2^2 + (tan(alpha) - 2*a) -y2)
        * y1 - a*x1^2 = (tan(alpha) - 2*a) + ( -(a*x2^2 + (tan(alpha) - 2*a) -y2)
        *
        * y1 - a*x3 = (t - 2*a) + (- (a*x4 + (t - 2*a) -y2)
        * -a*x3 = t - 2*a + ( -a*x4 - t + 2*a +y2) - y1
        * a = - (t - 2a + ( -a*x4 -t +2a +y2) -y1) / x3
        * a = (-t +2a -( -a*x4 -t +2a +y2)  -y1) / x3
        * a - 2a/x3 = (-t -( -a*x4 -t +2a +y2) -y1) / x3
        *
        *
        * a*x3 - 2a = -(t + ( -a*x4 - t + 2a + y2) -y)
        * a*x3 - 2a =
        *
        x=t
        y=a*t^2+b*t+c
        (cosa,sena)

        angulo a = 30;
        x = cos(30) * t;
        y = sen(30) * (a * t^2 + b * t + c);

         */

        //endregion

/*
        // TODO CORREGIR ESTO!!
        int direccion = (int) Math.round(Math.random());

        accelX = MathUtils.random(10, 50);

        if (direccion == 1)
            accelX = -accelX;
        step = accelX * 0.005f;
        System.out.println(accelX);*/
    }

    @Override
    public void destroy() {
        // Override hecho para que no se destruya al chocar
    }


    public void action(Player player, OrthographicCamera camera) {
    } //end action()

    public void draw() {
        // TODO Reñir a Diego por no usar la clase "Sprite"
        spr.rotate(MathUtils.random(15, 45));

        spr.draw(GameEngine.batch);
        //GameEngine.batch.draw(Sprites.rotaryHoePowerUpImage[0])

    }

    @Override
    public void decreaseLives(float lives) {

    }

    @Override
    public void move() {
        /*
        this.x += (hSpeed + accelX)* accelX * Gdx.graphics.getDeltaTime();
        this.y += vSpeed * Gdx.graphics.getDeltaTime();

        */
        this.y += vSpeed * Gdx.graphics.getDeltaTime();
        //y=a*x^2+b*x+c
        this.x =(float) (a * y * y + b * y + c);


        spr.setPosition(x - 6, y - 6);
    }
}
