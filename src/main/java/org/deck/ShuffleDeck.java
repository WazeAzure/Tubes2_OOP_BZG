package org.deck;

import org.config.Config;
import org.kartu.Kartu;

import java.util.*;

public class ShuffleDeck{
    private List<Kartu> listKartu;

    public ShuffleDeck(int n){
        listKartu = new ArrayList<>();
        Random random = new Random();
        Map<String, ? extends Kartu > map;
        List<String> list;
        int index;
        int max;
        for(int i = 0; i < n; i++){
            int randomInt = random.nextInt(7);
            switch (randomInt){
                case 0:
                    map = Config.getHerbivora();
                    list = new ArrayList<>(map.keySet());
                    max = map.size();
                    index = random.nextInt(max);
                    String herbivore = list.get(index);
                    listKartu.add(Config.buildHerbivora(herbivore));
                    break;
                case 1:
                    map = Config.getKarnivora();
                    list = new ArrayList<>(map.keySet());
                    max = map.size();
                    index = random.nextInt(max);
                    String carnivore = list.get(index);
                    listKartu.add(Config.buildKarnivora(carnivore));
                    break;
                case 2:
                    map = Config.getOmnivora();
                    list = new ArrayList<>(map.keySet());
                    max = map.size();
                    index = random.nextInt(max);
                    String omnivore = list.get(index);
                    listKartu.add(Config.buildOmnivora(omnivore));
                    break;
                case 3:
                    map = Config.getListTumbuhan();
                    list = new ArrayList<>(map.keySet());
                    max = map.size();
                    index = random.nextInt(max);
                    String plant = list.get(index);
                    listKartu.add(Config.buildTumbuhan(plant));
                    break;
                case 4:
                    map = Config.getListItem();
                    list = new ArrayList<>(map.keySet());
                    max = map.size();
                    index = random.nextInt(max);
                    String item = list.get(index);
                    listKartu.add(Config.buildItem(item));
                    break;
                case 5:
                    map = Config.getListProductAnimal();
                    list = new ArrayList<>(map.keySet());
                    max = map.size();
                    index = random.nextInt(max);
                    String productAnimal = list.get(index);
                    listKartu.add(Config.buildProduct(productAnimal));
                    break;
                case 6:
                    map = Config.getListProductPlant();
                    list = new ArrayList<>(map.keySet());
                    max = map.size();
                    index = random.nextInt(max);
                    String productPlant = list.get(index);
                    listKartu.add(Config.buildProduct(productPlant));
                    break;
            }
        }
    }
    public List<Kartu> getShuffleKartu(int jumlah){
        Random random = new Random();
        int randomInt;
        List<Kartu> list = new ArrayList<>();
        for(int i = 0; i < jumlah; i++){
            randomInt = random.nextInt(listKartu.size());
            list.add(listKartu.get(randomInt));
        }
        return list;
    }

    public int getRemainingCard(){
        return listKartu.size();
    }

    public void removeFromDeck(List<Kartu> lk){
        for(Kartu k : lk){
            listKartu.remove(k);
        }
    }
}
