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
import com.mygdx.game.Entities.Enemies.BarbedWireEnemy;
import com.mygdx.game.Entities.Enemies.Bossses.Boss;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.Star;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Levels.Level;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Projectiles.Projectile;
import com.mygdx.game.Screens.Scr_GameEnd;
import com.mygdx.game.Screens.Scr_Introduction;
import com.mygdx.game.Screens.Scr_LevelSelection;
import com.mygdx.game.Screens.Scr_Loading;
import com.mygdx.game.Screens.Scr_MainMenu;
import com.mygdx.game.Screens.Scr_Pause;
import com.mygdx.game.Screens.Scr_Play;

import java.util.ArrayList;
import java.util.Iterator;

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

        // si se pulsa el boton de atras salimos del juego
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

        if (GameEngine.gameState.isLoading()) {

            if (MathUtils.random(0,100) >= 99) {
                texts.get(0).setText(Scr_Loading.getRandomText());
            }

            texts.get(1).setText((int)(loader.getProgress() * 100) + "%");
            if (loader.update()) {
                // Inicializar el musicManager despues de cargar todo, ya que usa una cancion...
                MyGdxGame.musicManager  = new MusicManager();
                initComponents();
                GameEngine.gameState.mainMenu();
            }
        }
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
                player.shoot();
            } else {
                player.incrementCooldown();
            }

            moveBullets();
            cargarNivel();
            player.move();
        }

        checkButtonPress();
        if (gameState.isInEndGame()) {
            handleEndGame();
        }
        // }

    }

    private void handleEndGame() {
        //basicamente, nsi la animacion de gameOver ha llegado a su fin, se podra pasar de pantalla.
        if (Gdx.input.justTouched() && Scr_GameEnd.textAdded) {
            gameState.mainMenu();
        }
    }

    private void updateScore() {
        texts.get(0).setText("SCORE: " + player.getScore());
    }

    private void generateStars() {
        //System.out.println(starCount);
        if (starCount < starLimit) {
            // 10% probabilidad de spawnear estrella.
            if (MathUtils.random(0, 100) < 20) {
                Star s = new Star();

                addEntity(s, EntityType.OTHER);
                starCount++;
            }
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

    private void moveOther() {
        Iterator<Entity> it = otherEntities.iterator();

        while (it.hasNext()) {
            Entity e = it.next();
            e.move();
            if (e.remove) {
                it.remove();
                // SI quitas este IF luego acaba en una lluvia de estrellas. Cosas de Serrano para boss.
                // Equivalente seria hacer starCount -= 20 (mas o menos).
                // P.D Probado -= 20, NO funciona...
                // P.D2 > probado tambien controlando el 0, sigue sin funcionar bien
                // Quizas alterando el starLimit
                if (e.getClass().equals(Star.class)) {
                    starCount--;

                }
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

            player.setVSpeed(((player.getY() + player.getHeight() / 2) - touchPos.y));
            player.setHSpeed(((player.getX() + player.getWidth() / 2) - touchPos.x));

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
        // Invertido el bucle. Se evita recorrer el array de "bullets_Player" 2 veces. (Culpa de los bosses)
        // TODO Meter threads para esto si es posible.
        for (Projectile p : bullets_Player) {
            for (Enemy enem : enemies) {
                if (enem.isColliding(p)) {
                    enem.decreaseLives(p.getDamage());
                    if (!enem.isTrascendental()) {
                        p.destroy();
                    }
                    if (enem.getLives() <= 0) {
                        enem.kill();
                    }
                }
            }
            for (Boss boss : bosses) {
                if (boss.isColliding(p)) {
                    boss.decreaseLives(p.getDamage());
                    p.destroy();
                    if (boss.getLives() <= 0.0f) {
                        boss.kill();
                    }
                }
            }
        }

        for (Enemy enem : enemies) {
            if (enem.isColliding(player)) {
                if (!player.isHit()) {

                    enem.kill();

                    if (enem.getType().equals(EnemyType.SPIKE_BALL))
                        player.decreaseLives(3);        // Las minas tienen mas daño, no??
                    else if (enem.getType().equals(EnemyType.HEAVY_ENEMY))
                        player.decreaseLives(2);        // -2 por Heavy
                    else
                        player.decreaseLives(1);

                }
            }
        }

        for (Boss b : bosses) {
            if (b.isColliding(player)) {

                if (!player.isHit()) {
                    player.decreaseLives(15);
                    // No se si la funcion kill() tiene un comportamiento adecuado para Player. Por eso el -15
                    //player.kill();
                }
            }
        }
        for (Projectile p : bullets_Enemy) {
            if (player.isColliding(p)) {
                player.increaseCharge(p.getCharge());

                if (!player.isHit()) {
                    player.decreaseLives(p.getDamage());
                }

                p.destroy();
            }
        }

        if (player.getLives() <= 0.0f) {
            gameState.finishGame(false);
        }

    }

    private void checkButtonPress() {
        if (Gdx.input.justTouched())
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
                    }

                    if (buttons.get("btn_exit") != null) {
                        buttons.remove("btn_exit");
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
                case GAME_END_WIN:
                    new Scr_GameEnd(true).initComponents();
                    break;
                case GAME_END_LOSE:
                    new Scr_GameEnd(false).initComponents();
                    break;
                case INTRO:
                    new Scr_Introduction().initComponents();
                    break;
                case LOADING:
                    new Scr_Loading().initComponents();
                    break;
                case LEVEL_SELECTION:
                    new Scr_LevelSelection().initComponents();
                    break;
            }
        }
    }

    private void cargarNivel() {

        levelManager.getCurrentLevel().runLevel();

    }

    @Override
    public void run() {
        tick();
    }

}
