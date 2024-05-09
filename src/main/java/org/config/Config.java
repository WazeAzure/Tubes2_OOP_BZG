package org.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.kartu.Kartu;
import org.kartu.hewan.Herbivora;
import org.kartu.hewan.Hewan;
import org.kartu.hewan.Karnivora;
import org.kartu.hewan.Omnivora;
import org.kartu.item.*;
import org.kartu.product.Product;
import org.kartu.tumbuhan.Tumbuhan;

public class Config {
    private HashMap<String, Item> listItem;
    private HashMap<String, Tumbuhan> listTumbuhan;
    private HashMap<String, Product> listProductAnimal;
    private HashMap<String, Product> listProductPlant;
    private HashMap<String, Karnivora> listKarnivora;
    private HashMap<String, Herbivora> listHerbivora;
    private HashMap<String, Omnivora> listOmnivora;

    private String testfileDirectory = "testfile";

    public Config() {
        this.listItem = new HashMap<>();
        this.listTumbuhan = new HashMap<>();
        this.listProductAnimal = new HashMap<>();
        this.listProductPlant = new HashMap<>();
        this.listKarnivora = new HashMap<>();
        this.listHerbivora = new HashMap<>();
        this.listOmnivora = new HashMap<>();
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
                        this.listKarnivora.put(name, new Karnivora());
                    }
                    else if(split[1].equals("Herbivora")){
                        this.listHerbivora.put(name, new Herbivora());
                    }
                    else if(split[1].equals("Omnivora")){
                        this.listOmnivora.put(name, new Omnivora());
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
                        this.listProductAnimal.put(name, new Product());
                    }
                    else if(split[1].equals("Tanaman")){
                        this.listProductPlant.put(name, new Product());
                    }
                }
                else if(split[0].equals("Tanaman")){
                    String name = split[2];
                    if(split.length > 3){
                        name = name + " " + split[3];
                    }

                    int dayToHarvest = Integer.parseInt(split[1]);

                    this.listTumbuhan.put(name, new Tumbuhan());
                }
                else if(split[0].equals("Item")){
                    String name = split[1];
                    this.listItem.put(name, new Item());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Error");
        }
    }

    public Product buildProduct(String name) {
        Product p;
        if(this.listProductAnimal.containsKey(name)){
            p = this.listProductAnimal.get(name);
        }
        else if(this.listProductPlant.containsKey(name)){
            p = this.listProductPlant.get(name);
        }
        return new Product();
    }

    public Omnivora buildOmnivora(String name){
        Omnivora p = this.listOmnivora.get(name);
        return new Omnivora();
    }

    public Karnivora buildKarnivora(String name){
        Karnivora p = this.listKarnivora.get(name);
        return new Karnivora();
    }

    public Herbivora buildHerbivora(String name){
        Herbivora p = this.listHerbivora.get(name);
        return new Herbivora();
    }

    public Tumbuhan buildTumbuhan(String name){
        Tumbuhan p = this.listTumbuhan.get(name);
        return new Tumbuhan();
    }

    public Item buildItem(String name){
        Item p = this.listItem.get(name);
        return new Item();
    }

    public static void main(String[] args){
        Config conf = new Config();
        conf.loadConfig();
    }
}
