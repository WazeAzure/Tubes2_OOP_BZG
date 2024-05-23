package org.config;

import org.gameEngine.GameEngine;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.io.BufferedReader;
import java.io.FileReader;

import org.pemain.Pemain;
import org.plugins.FileLoader;

public class FileHandling {
    private HashMap<String, Class> pluginMap;
    private GameEngine gameEngine;

    public FileHandling(GameEngine gameEngine){
        this.pluginMap = new HashMap<>();
        this.gameEngine = gameEngine;
    }

    public void load(String folderPath, String extention){
        // ASUMSIKAN folderPath ABSOLUTE PATH

        System.out.println(folderPath);
        File file = new File(folderPath);

        File gamestate = new File(folderPath + "\\gamestate" + extention);
        File player1 = new File(folderPath + "\\player1" + extention);
        File player2 = new File(folderPath + "\\player2" + extention);

        /* Handle GameState */
        try {
            BufferedReader brGamestate = new BufferedReader(new FileReader(gamestate));
            // read current turn
            int turn = Integer.parseInt(brGamestate.readLine());
            gameEngine.setTurn(turn);

            // set shop items
            int n = Integer.parseInt(brGamestate.readLine());
            for(int i=0; i<n; i++){
                String[] pair = brGamestate.readLine().trim().split(" ");
                gameEngine.setItemInToko(pair[0], Integer.parseInt(pair[1]));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        /* Handle Player 1 */
        try {
            BufferedReader brPlayer1 = new BufferedReader(new FileReader());
            // read gold
            int gold = Integer.parseInt(brPlayer1.readLine());
            gameEngine.setPemainGulden(0, gold);

            int deck = Integer.parseInt(brPlayer1.readLine());
            gameEngine.setP

            // set shop items
            int n = Integer.parseInt(brPlayer1.readLine());
            for(int i=0; i<n; i++){
                String[] pair = brPlayer1.readLine().trim().split(" ");
                gameEngine.setItemInToko(pair[0], Integer.parseInt(pair[1]));
            }
        } catch (Exception e){
            e.printStackTrace();
        }



//        if(fileName.contains("player1")){
//            // untuk file player
//            // catatan: file saved memiliki nama tetao
//
//        }
//        else if(fileName.contains("player2")){
//
//        }
//        else if(fileName.contains("gamestate")){
//
//        }
    }

    public void loadPlugin(String fileName) throws Exception {
        fileName = "C:\\Users\\Asus Tuf Gaming\\IdeaProjects\\Tubes2_OOP_BZG\\testfile\\LoaderXML.jar";

        try {
            URL jarUrl= new File(fileName).toURI().toURL();
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{jarUrl});

            JarFile jarFile = new JarFile(fileName);
            Enumeration<JarEntry> entries = jarFile.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class") && !entry.getName().equals("FileLoader.class")) {
                    String className = entry.getName().replace('/', '.').replaceAll(".class$", "");
                    System.out.println(className);

                    Class<?> c = urlClassLoader.loadClass(className);

                    final List<Class<?>> interfaces = Arrays.asList(c.getInterfaces());
                    interfaces.forEach(itf -> {
                        System.out.println(itf.getName());
                        if (itf.getName().equals("FileLoader")) {
                            try {
                            Method t = c.getMethod("getSupportedExtension");
                            Object o = c.getDeclaredConstructor().newInstance();
                            String ext = (String) t.invoke(o);
                            System.out.println("Supported Extention: " + ext);
                            pluginMap.put(ext, c);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } // else, abaikan kelas ini
                    });
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

//        try {
////            URL jarUrl= (new File(fileName).toURI().toURL());
//            URL jarUrl= new File(fileName).toURI().toURL();
//            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{jarUrl});
//
//            Class c = urlClassLoader.loadClass("LoaderXML");
//            final List<Class> interfaces = Arrays.asList(c.getInterfaces());
//            System.out.println(c);
//            interfaces.forEach(itf -> {
//                System.out.println(itf.getName());
//                if (itf.getName().equals("FileLoader")) {
//                    try {
//                        // proses kelas ini...
//                        System.out.println("FOUND INTERFACE");
//                    } catch (Exception e) {
//                        // tidak bisa instansiasi, plugin tidak usah diproses
//                        throw new RuntimeException(e);
//                    }
//                } // else, abaikan kelas ini
//            });
//
//            FileLoader a = c.getDeclaredConstructor().newInstance();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try (JarFile jarFile = new JarFile(fileName)) {
//            Enumeration<JarEntry> entries = jarFile.entries();
//
//            while (entries.hasMoreElements()) {
//                JarEntry entry = entries.nextElement();
//                if (entry.getName().endsWith(".class")) {
//                    String className = entry.getName().replace('/', '.').replaceAll(".class$", "");
//                    System.out.println(className);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}