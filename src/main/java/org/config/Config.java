package org.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.kartu.Kartu;
import org.kartu.harvestable.hewan.Herbivora;
import org.kartu.harvestable.hewan.Hewan;
import org.kartu.harvestable.hewan.Karnivora;
import org.kartu.harvestable.hewan.Omnivora;
import org.kartu.item.*;
import org.kartu.product.Product;
import org.kartu.harvestable.tumbuhan.Tumbuhan;

public class Config {
    private static HashMap<String, Item> listItem;
    private static HashMap<String, Tumbuhan> listTumbuhan;
    private static HashMap<String, Product> listProductAnimal;
    private static HashMap<String, Product> listProductPlant;
    private static HashMap<String, Karnivora> listKarnivora;
    private static HashMap<String, Herbivora> listHerbivora;
    private static HashMap<String, Omnivora> listOmnivora;

    private String testfileDirectory = "testfile";

    public Config() {
        Config.listItem = new HashMap<>();
        Config.listTumbuhan = new HashMap<>();
        Config.listProductAnimal = new HashMap<>();
        Config.listProductPlant = new HashMap<>();
        Config.listKarnivora = new HashMap<>();
        Config.listHerbivora = new HashMap<>();
        Config.listOmnivora = new HashMap<>();
    }

    public void loadConfig(){
        try {
            String currentPath = new java.io.File(".").getCanonicalPath();
            System.out.println("Current dir:" + currentPath);

            File file = new File(testfileDirectory + File.separator + "config.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] split = line.split(" ");
                System.out.println(split[0]);
                if(split[0].equals("Hewan")){
                    String name = split[3];
                    if(split.length > 4){
                        name = name + " " + split[4];
                    }

                    int weightToHarvest = Integer.parseInt(split[2]);

                    if(split[1].equals("Karnivora")){

                        Config.listKarnivora.put(name, new Karnivora(name, "Karnivora", "..", weightToHarvest));
                    }
                    else if(split[1].equals("Herbivora")){
                        Config.listHerbivora.put(name, new Herbivora(name, "Herbivora", "..", weightToHarvest));
                    }
                    else if(split[1].equals("Omnivora")){
                        Config.listOmnivora.put(name, new Omnivora(name, "Omnivora", "..", weightToHarvest));
                    }
                }
                else if(split[0].equals("Produk")){
                    String name = split[4];
                    if(split.length > 5){
                        name = name + " " + split[5];
                    }

                    int weightAdded = Integer.parseInt(split[2]);
                    int price = Integer.parseInt(split[3]);

                    if(split[1].equals("Hewan")){
                        Config.listProductAnimal.put(name, new Product(name, "ProdukHewan", "..", price, weightAdded));
                    }
                    else if(split[1].equals("Tanaman")){
                        Config.listProductPlant.put(name, new Product(name, "ProdukTanaman", "..", price, weightAdded));
                    }
                }
                else if(split[0].equals("Tanaman")){
                    String name = split[2];
                    if(split.length > 3){
                        name = name + " " + split[3];
                    }

                    int dayToHarvest = Integer.parseInt(split[1]);

                    Config.listTumbuhan.put(name, new Tumbuhan(name, "Tumbuhan", "..", dayToHarvest));
                }
                else if(split[0].equals("Item")){
                    String name = split[1];
                    Config.listItem.put(name, new Item(name, "Item", ".."));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Error");
        }
    }

    public static Product buildProduct(String name) {
        Product p;
        if(Config.listProductAnimal.containsKey(name)){
            p = Config.listProductAnimal.get(name);
        }
        else {
            p = Config.listProductPlant.get(name);
        }
        return new Product(p.getNama(), p.getKategori(), p.getImageURL(), p.getHarga(), p.getBerat());
    }

    public static Omnivora buildOmnivora(String name){
        Omnivora p = Config.listOmnivora.get(name);
        return new Omnivora(p.getNama(), p.getKategori(), p.getImageURL(), p.getValuePanen());
    }

    public static Karnivora buildKarnivora(String name){
        Karnivora p = Config.listKarnivora.get(name);
        return new Karnivora(p.getNama(), p.getKategori(), p.getImageURL(), p.getValuePanen());
    }

    public static Herbivora buildHerbivora(String name){
        Herbivora p = Config.listHerbivora.get(name);
        return new Herbivora(p.getNama(), p.getKategori(), p.getImageURL(), p.getValuePanen());
    }

    public static Tumbuhan buildTumbuhan(String name){
        Tumbuhan p = Config.listTumbuhan.get(name);
        return new Tumbuhan(p.getNama(), p.getKategori(), p.getImageURL(), p.getValuePanen());
    }

    public static Item buildItem(String name){
        Item p = Config.listItem.get(name);
        return new Item(p.getNama(), p.getKategori(), p.getImageURL());
    }

    public static void main(String[] args){
        Config conf = new Config();
        conf.loadConfig();
    }
}
