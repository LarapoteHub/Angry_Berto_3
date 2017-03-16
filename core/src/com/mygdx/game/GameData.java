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
    private static TreeMap<String, Object[]> levels_map;

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
                levels_map = (TreeMap<String, Object[]>) ois.readObject();
            } catch (NullPointerException ex) {
                levels_map = new TreeMap<String, Object[]>();
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public static void insertHighscore(String levelName, long stars, long score) {

        if (!existLevel(levelName)) {
            long[] scores = new long[10];
            scores[0] = score;
            levels_map.put(levelName, new Object[]{stars, scores});
        } else {

            Object[] values = levels_map.get(levelName);
            if (values[0] == null || stars > (Long) values[0]) {
                values[0] = stars;
            }

            long[] scores = (long[]) values[1];

            for (int i = 0 ; i < scores.length ; i++) {
                if (score > scores[i]) {

                    for (int j = scores.length-1 ; j > i ; j--) {
                        scores[j] = scores[j-1];
                    }

                    scores[i] = score;
                    break;
                }
            }
        }

        //para DEBUG
        //imprimirScores(levelName);




    }

    public static long getStars(String levelName) {

        Long stars = (Long) levels_map.get(levelName)[0];

        return stars == null ? 0 : stars;

    }

    public static long getHighscore(String levelName) {


        long[] scores = (long[]) levels_map.get(levelName)[1];

        long highscore = 0l;

        for (int i = 0 ; i < scores.length ; i++) {
            if (scores[i]>highscore) {
                highscore = scores[i];
            }
        }

        return highscore;

    }

    public static long[] getScores(String levelName) {
        if (existLevel(levelName)) {
            return (long[]) levels_map.get(levelName)[1];
        } else {
            return new long[10];
        }

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

    private static void imprimirScores(String levelName) {
        Object[] values = levels_map.get(levelName);
        long[] scores = (long[]) values[1];

        System.out.print("ARRAY: ");

        //Visualizar array
        for (int i = 0 ; i < scores.length ; i++) {
            System.out.print("["+scores[i]+"]");
        }

        System.out.println("\n");
    }

}
