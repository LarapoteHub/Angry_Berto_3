/**
 * Esta parte deberia de encargarse de:
 * - Colisiones
 * - Movimiento
 * - Comprobar si hay que destruir algun objeto
 * - Mirar si esta pausado o no
 * - Solicitar una nueva oleada??
 * - Calcular si hay colision entre frame y frame
 * - Independiente del tiempo que se tarda en renderizar (usar deltaTime)
 */

package com.mygdx.game.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Buttons.Button;
import com.mygdx.game.Entities.Enemies.Bossses.Boss;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.Explosion;
import com.mygdx.game.Entities.Star;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Levels.Level;
import com.mygdx.game.Levels.Level0;
import com.mygdx.game.Levels.LevelTest;
import com.mygdx.game.Projectiles.PlayerShoot;
import com.mygdx.game.Projectiles.Projectile;
import com.mygdx.game.Screens.Scr_GameOver;
import com.mygdx.game.Screens.Scr_Introduction;
import com.mygdx.game.Screens.Scr_MainMenu;
import com.mygdx.game.Screens.Scr_Pause;
import com.mygdx.game.Screens.Scr_Play;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Red Mercy on 9/26/2016.
 */
public class Logic extends GameEngine implements Runnable {

    // TODO Implement this...
    private static ArrayList<Level> levelList = new ArrayList<Level>();
    ;
    private static int levelIndex;

    private static long lastTime = System.nanoTime();

    public Logic() {

    }

    public void tick() {
        if (MyGdxGame.DEBUG_MODE) {
            System.out.println("_________________________");
            System.out.println("Counts:");
            System.out.println("Boss: " + bosses.size());
            System.out.println("Enemies: " + enemies.size());
            System.out.println("Bullets: " + bullets_Player.size() + "  " + bullets_Enemy.size());
            System.out.println("Other: " + otherEntities.size());
            System.out.println("Images: " + images.size());
            System.out.println("Texts: " + texts.size());
            System.out.println("_________________________");
        }

        /*
		 * Parte del Button de Hoe
		 * 
		 * if (chargeBar >= cost) {
		 * GameEngine.batch.draw(textures[1].getTexture(), x, y, width, height);
		 * } else { GameEngine.batch.draw(textures[0].getTexture(), x, y, width,
		 * height); }
		 */

        // si se pulsa el boton de atras salimso del juego
        // TODO Deberia haber un mensaje que pida otro Back!!
        updateTouchPos();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)
                && GameEngine.gameState.isPlaying()) {
            GameEngine.gameState.mainMenu();
        } /*
		 * else if (GameEngine.gameState.isInMainMenu()) { System.exit(0); }
		 */

        if (gameState.isChanged()) // && !Gdx.input.isTouched()) Efecto MUY
            // interesante si descomentas esta
            // condicion
            updateInterface();


        if (GameEngine.gameState.isPlaying()) {
            // TODO Completar esto
            checkCollisions();
            generateStars();
            moveOther();
            moveEnemies();
            moveBosses();
            checkPlayerChange();
            updateScore();
            if (player.canShoot()) {
                if (MyGdxGame.DEBUG_MODE)
                    System.out.println("pew!");
                bullets_Player.add(new PlayerShoot(player));
                player.setCanShoot(false);
            } else {
                player.incrementCooldown();
            }

            moveBullets();
            cargarNivel();
        }

        checkButtonPress();
        if (gameState.isInEndGame()) {
            handleEndGame();
        }
        // }

    }

    private void handleEndGame() {
        if (Gdx.input.justTouched()) {
            gameState.mainMenu();
        }
    }

    private void updateScore() {
        texts.get(0).setText("SCORE: " + player.getScore());
    }

    private void generateStars() {
        if (starCount <= 50) {
            Star s = new Star(10, 10);

            s.setX(MathUtils.random(68, 480 - 2)); // antes 0
            s.setY(800);
            s.setVSpeed(-MathUtils.random(1000, 1500));
            // Esto es opcional
            s.setHSpeed(-MathUtils.random(10, 30));

            addEntity(s, EntityType.OTHER);
            starCount++;
        }

    }

    private void updateTouchPos() {
        // TODO Como narices se mira el recientemente pulsado....
        // if (Gdx.input.isTouched() && Gdx.input.978978) {
        if (Gdx.input.isTouched()) {
            // creamos un vector tridimensional.

            // estos dos métodos nos devuelven las coordenadas donde se ha
            // recibido el click/toque, estas coordenadas no tienen porque estar
            // en el mismo sistema de unidades en el que se desenvuelven los
            // objetos de nuestro mundo, por lo tanto, debemos transformarlas a
            // tal "medida".
            // el tercer 0 sirve para convertir el vector tridimensional en algo
            // bidimensional (eje z = 0)
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            // para transformar las coordenadas recibidas a nuestro sistema,
            // llamamos al método camera.unproject() y le pasamos el Vector3
            // anteriormente creado.
            // tras esto, el vector ahora contendrá las coordenadas extraídas
            // del lugar del click/toque, en la medida en la que se desenvuelve
            // nuestro mundo.
            GameEngine.cam.unproject(touchPos);
        } else {
            touchPos.set(-10, -10, 0);
        }

    }

    private void moveEnemies() {
        // Si hay mas de 1. Probablemente añadido porque da algun NullPointer
        // sino
        if (enemies.size() > 0)
            for (Iterator<Enemy> it = enemies.iterator(); it.hasNext(); ) { // Iterando
                Enemy e = it.next();
                if (e.remove)
                    it.remove(); // Esta es la razon por la que estamos
                    // iterando, para poder quitar algo cuando
                    // nos sobra y seguir iterando sin que
                    // falle.
                else if (e.canShoot()) { // Si se puede disparar, elegir tipo de
                    // bala
                    e.shoot();
                } else {
                    e.incrementCooldown(); // El cooldown es un "temporizador"
                    // que empieza en 0 y cuando llega a
                    // lo que es el cooldown permite
                    // disparar.
                }
                e.move(); // Mover si no ha palmado aun.
                e.runBehavior();
            }
    }

    private void moveBosses() {
        // Si hay mas de 1. Probablemente añadido porque da algun NullPointer
        // sino
        if (bosses.size() > 0)
            for (Iterator<Boss> it = bosses.iterator(); it.hasNext(); ) { // Iterando
                Boss boss = it.next();
                if (boss.remove)
                    it.remove(); // Esta es la razon por la que estamos
                    // iterando, para poder quitar algo cuando
                    // nos sobra y seguir iterando sin que
                    // falle.
                else if (boss.canShoot()) { // Si se puede disparar, elegir tipo de
                    // bala
                    boss.shoot();
                } else {
                    boss.incrementCooldown(); // El cooldown es un "temporizador"
                    // que empieza en 0 y cuando llega a
                    // lo que es el cooldown permite
                    // disparar.
                }
                boss.move(); // Mover si no ha palmado aun.
                boss.runBehavior();
            }
    }

    // For testing purposes only!
    private void spawnTestEnemies() {
        // StandardEnemy e = new StandardEnemy(100, 200, 0);
        // addEntity(e, EntityType.ENEMY);
        //
        // HeavyEnemy e2 = new HeavyEnemy(150, 200, 0);
        // addEntity(e2, EntityType.ENEMY);
        //
        // EvadingEnemy e3 = new EvadingEnemy(300, 250, 0);
        // addEntity(e3, EntityType.ENEMY);

    }

    private void moveOther() {
        Iterator<Entity> it = otherEntities.iterator();

        while (it.hasNext()) {
            Entity e = it.next();
            e.move();
            if (e.remove) {
                it.remove();
                starCount--;
            }
        }
    }

    private void moveBullets() {
        // Movidas nazis para que no crashee por
        // "java.util.ConcurrentModificationException" !!
        for (Iterator<Projectile> it = bullets_Player.iterator(); it.hasNext(); ) {
            Projectile p = it.next();
            p.move();
            if (p.remove)
                it.remove();
        }
        if (MyGdxGame.DEBUG_MODE)
            System.out.println("Player: " + bullets_Player.size());

        for (Iterator<Projectile> it = bullets_Enemy.iterator(); it.hasNext(); ) {
            Projectile p = it.next();
            p.move();
            if (p.remove)
                it.remove();
        }
        if (MyGdxGame.DEBUG_MODE)
            System.out.println("Enemy: " + bullets_Enemy.size());

    }

    private void checkPlayerChange() {
        if (touchPos.y < MyGdxGame.HEIGHT / 2 && touchPos.x > 100) {

            // No necesariamente necesario :P
			/*
			 * if (touchPos.x >= player.x - 40 && touchPos.x <= player.x +
			 * player.width + 40 && touchPos.y >= player.y - 40 && touchPos.y <=
			 * player.y + player.height + 40) { // añadido que solo se mueva si
			 * se toca la nave // EL HECHO DE QUE ESTO VAYA EN EL ELSE EVITA QUE
			 * LA // NAVE SE MUEVA AL TOCAR EL BOTÓN DE PAUSA. // finalmente,
			 * cambiamos la posición de la nave a la // posición extraída del
			 * lugar del click/toque. player.setX(touchPos.x - player.getWidth()
			 * / 2); player.setY(touchPos.y - player.getHeight() / 2);
			 * 
			 * } else {
			 */

            player.setVSpeed(((player.getY() + player.getHeight() / 2) - touchPos.y));
            player.setHSpeed(((player.getX() + player.getWidth() / 2) - touchPos.x));
            /*if (player.getVSpeed() < 2)
                player.setVSpeed(0);

            if (player.getHSpeed() < 2)
                player.setHSpeed(0);*/
            // }

        }

        // ------------------------------------

        // ********* NOTAS *************

        // NOTA*: es muy malo instanciar un objeto una y otra vez como
        // hacemos con el Vector3,
        // en este caso lo haremos gracias al recolector de basura
        // "de Java".
        // En una aplicación de escritorio no suele ser un problema pero
        // en un sistema Android,
        // podría afectar al rendimiento. Para solucionarlo en este caso
        // podríamos declarar el Vector3 como una variable de la clase.

        // NOTA2*: el hecho de que utilicemos un vector 3D es porque
        // Orthographic Camera es una
        // cámara que trabaja en 3D. Abusamos de esto para crear
        // imágenes en 2D.

        // NOTA3*: Red Mercy ha hecho lo que especifica en la NOTA1
        // Asique aqui no hay nada que ver...

        // *****************************

        // Si quisieramos hacer el movimiento de la nave con un teclado,
        // haríamos lo siguiente:
        // Queremos que la nave se mueva horizontalmente a una velocidad
        // de 200 pixeles/unidades
        // por segundo, tardando 4 segundos en recorrer la pantalla.
        // Para esto, primero, combrobaremos si las teclas LEFT o RIGHT
        // están presionadas.
        // Después, le restaremos o sumaremos 200 unidades a la posición
        // X de la nave multiplicadas
        // por el tiempo que transcurre entre el último frame procesado
        // y el que se está procesando actualmente (conocido en libGDX
        // como DeltaTime).
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.setX(player.getX() - player.getHSpeed()
                    * Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.setX(player.getX() + player.getHSpeed()
                    * Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            player.setX(player.getY() - player.getVSpeed()
                    * Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            player.setX(player.getY() + player.getVSpeed()
                    * Gdx.graphics.getDeltaTime());

        // Comprobamos que la nave se quede entre los márgenes de la
        // pantalla

    }

    private void checkCollisions() {
        for (Enemy enem : enemies) {
            for (Projectile p : bullets_Player) {
                if (enem.isColliding(p)) {
                    enem.decreaseLives(p.getDamage());
                    p.destroy();
                    if (enem.getLives() <= 0) {
                        enem.kill();
                        addEntity(new Explosion(enem.getX(), enem.getY()),
                                EntityType.OTHER);
                        player.addScore(enem.getScore());
                        Random rnd = new Random(System.nanoTime()
                                * System.nanoTime() / 13);
                        if (rnd.nextInt(100) < enem.getPowerUpProbability())
                            spawnPowerUpCharge(enem.getX(), enem.getY());
                    }
                }
            }
        }
        for (Projectile p : bullets_Enemy) {
            if (player.isColliding(p)) {
                player.increaseCharge(p.getCharge());
                player.decreaseLives(p.getDamage());
                if (player.getLives() <= 0) {
                    gameState.finishGame();
                }
                p.destroy();
            }
        }

    }

    private void checkButtonPress() {
        if (Gdx.input.isTouched())
            for (Button b : buttons.values()) {
                if (b.isTouched(touchPos)) {
                    if (MyGdxGame.DEBUG_MODE)
                        System.out.println(b.getName());
                    if (b.isPowerUp) {
                        if (gameState.isPlaying())
                            b.onTouch();
                    } else {
                        b.onTouch();
                    }
                }
            }
    }

    private void updateInterface() {
        if (gameState.isChanged()) {
            gameState.setChanged(false);
            if (!gameState.saveEntities()) {
                clearAll();
            }
            switch (gameState.getState()) {
                case GAMEPLAY:
                    if (!gameState.saveEntities()) {
                        new Scr_Play().initComponents();
                        spawnTestEnemies();
                    }

                    if (buttons.get("btn_Exit") != null) {
                        buttons.remove("btn_Exit");
                    }
                    player.setDraw(true);
                    break;
                case PAUSED:
                    new Scr_Pause().initComponents();
                    player.setDraw(true);
                    break;
                case MAIN_MENU:
                    new Scr_MainMenu().initComponents();
                    break;
                case GAME_END:
                    new Scr_GameOver().initComponents();
                    break;
                case INTRO:
                    new Scr_Introduction().initComponents();
                    break;
            }
        }
    }

    private void cargarNivel() {
        if (levelList.size() == 0) {
            levelList = new ArrayList<Level>();
            levelList.add(new Level0());
            //levelList.add(new LevelTest());
        }
        // TODO Implementar esto algun dia xD
        // //levelList.add(new Level1(engine));
        // //levelList.add(new Level2(engine));
        // //levelList.add(new Level3(engine));
        // //levelList.add(new Level4(engine));
        //
        // levelIndex = 0;
        // }
        //
        // levelList.get(0).runLevel();

        try {
            if (!levelList.get(levelIndex).finished) {
                levelList.get(levelIndex % levelList.size()).runLevel();
                if (MyGdxGame.DEBUG_MODE)
                    System.out.println("NIVEL ACTUAL: " + levelIndex);
            } else {
                levelIndex++;
            }
        } catch (Exception ex) {

            System.out.println("Error al rular el nivel");
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        //System.out.println((long)(Gdx.graphics.getDeltaTime() * 1000000000000l) + " - " + lastTime);
        //if (lastTime >= 170315591l) {
        tick();

        //}

    }

}
