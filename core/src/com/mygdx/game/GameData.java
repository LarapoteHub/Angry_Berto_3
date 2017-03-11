package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by xldan on 10/03/2017.
 */

public class GameData {

    private static FileHandle dataDir;

    private static FileHandle levels_file;
    //String=nombreDelNivel Long[0]stars, Long[1]highscore
    private static TreeMap<String, Long[]> levels_map;

    public static void initGameData() {

        try {

            dataDir = Gdx.files.external("angry_berto/data/");

            //si no existe la carpeta de Data...
            if (!dataDir.exists()) {
                //la creamos
                dataDir.mkdirs();
            }

            levels_file = Gdx.files.external(dataDir.path() + "/levels.ser");

            //si no existe el fichero de niveles...
            if (!levels_file.exists()) {
                //lo creamos
                levels_file.write(false);
            }

            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(levels_file.file()));

            } catch (EOFException ex) {
                System.out.println("Reached end of levels.ser");
            }

            try {
                levels_map = (TreeMap<String, Long[]>) ois.readObject();
            } catch (NullPointerException ex) {
                levels_map = new TreeMap<String, Long[]>();
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public static void insertHighscore(String levelName, long stars, long highscore) {

        Long[] values = new Long[2];

        System.out.println("DESBUG: STARS:"+stars+" BEFORE:"+getStars(levelName));
        System.out.println("DESBUG: SCORE:"+highscore+" BEFORE:"+getHighscore(levelName));

        if (stars > getStars(levelName)) {
            values[0] = stars;
        } else {
            values[0] = getStars(levelName);
        }

        if (highscore > getHighscore(levelName)) {
            values[1] = highscore;
        } else {
            values[1] = getHighscore(levelName);
        }

        levels_map.put(levelName, values);

    }

    public static long getStars(String levelName) {

        Long[] values = levels_map.get(levelName);

        return values == null ? 0 : values[0];

    }

    public static long getHighscore(String levelName) {

        Long[] values = levels_map.get(levelName);

        return values == null ? 0 : values[1];

    }

    public static void saveLevelsData() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(levels_file.file()));

            oos.writeObject(levels_map);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static boolean existLevel(String levelName) {
        return levels_map.get(levelName) != null;
    }

}
