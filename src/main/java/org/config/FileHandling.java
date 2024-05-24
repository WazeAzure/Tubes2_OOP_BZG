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

            /* get Game State info */
            int currentTurn = fl.getCurrentTurn();
            int nTokoItem = fl.getNTokoItem();
            List<InfoItemShop> li = fl.getItemAndQty();

            /* get Player 1 info */
            int player = fl.getCurrentPlayer();
            int gulden = fl.getGulden(0);
            int jumlahDeck = fl.getJumlahDeck(0);
            List<InfoKartuAktif> p = fl.getKartuDeckAktif(0);
            List<InfoKartuLadang> q = fl.getListKartuLadang(0);

            /* get Player 2 info */
            fl.getCurrentPlayer();
            fl.getGulden(1);
            fl.getJumlahDeck(1);
            fl.getKartuDeckAktif(1);
            fl.getListKartuLadang(1);



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