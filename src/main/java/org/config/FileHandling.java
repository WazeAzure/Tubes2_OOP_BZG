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
import org.plugins.InfoItemShop;
import org.plugins.InfoKartuAktif;
import org.plugins.InfoKartuLadang;

public class FileHandling {
    private HashMap<String, Class> pluginMap;
    private GameEngine gameEngine;

    public FileHandling(){
        this.pluginMap = new HashMap<>();

        // initiate txt reader
        try {
            Class c = Class.forName("org.plugins.LoaderTXT");
            this.pluginMap.put("txt", c);

            Class d = Class.forName("org.plugins.LoaderXML");
            this.pluginMap.put("xml", d);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public FileHandling(GameEngine gameEngine){
        this.pluginMap = new HashMap<>();
        this.gameEngine = gameEngine;

        // initiate txt reader
        try {
            Class c = Class.forName("org.plugins.LoaderTXT");
            this.pluginMap.put("txt", c);
            Class d = Class.forName("org.plugins.LoaderXML");
            this.pluginMap.put("xml", d);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void save() {

    }

    public void load(String folderPath, String extention){
        // ASUMSIKAN folderPath ABSOLUTE PATH

        // TODO: BERSIHKAN SELURUH STATE - LADANG - DEK AKTIF

        try {
            if(!this.pluginMap.containsKey(extention)){
                System.out.println(this.pluginMap);
                System.out.println("Plugin Not Exist!");
                return;
            }

            FileLoader fl = (FileLoader) this.pluginMap.get(extention).getConstructor().newInstance();

            // load file
            fl.loadFile(folderPath);

            // TODO: RESET GAME STATE - PLAYER 1 - PLAYER 2

            /* set Game State info */
            gameEngine.resetGame();
            gameEngine.setTurn(fl.getCurrentTurn());
            for (InfoItemShop i : fl.getItemAndQty()) {
                gameEngine.setItemInToko(i.nama, i.qty);
            }

            /* set Pemain info */
            for (int i = 0; i < 2; i++) {
                gameEngine.setPemainGulden(i, fl.getGulden(i));
                gameEngine.setPemainJumlahDeck(i, fl.getJumlahDeck(i));
                for (InfoKartuAktif ka : fl.getKartuDeckAktif(i)) {
                    gameEngine.setKartuDeckAktif(i, ka.lokasi, ka.nama);
                }
                for (InfoKartuLadang ka : fl.getListKartuLadang(i)) {
                    gameEngine.setKartuLadang(i, ka.lokasi, ka.nama, ka.umurBerat, ka.itemAktif);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadPlugin(String filePath) {
        // TODO: Hapus hard code filePath
        filePath = "C:\\Users\\Asus Tuf Gaming\\IdeaProjects\\Tubes2_OOP_BZG\\testfile\\LoaderXML.jar";

        try {
            URL jarUrl= new File(filePath).toURI().toURL();
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{jarUrl});

            JarFile jarFile = new JarFile(filePath);
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
                        if (itf.getName().endsWith(".FileLoader")) {
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