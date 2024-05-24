package org.example;

import org.config.Config;
import org.kartu.Kartu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    public int val = 11;
    public void Greeting(){
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        Config config = new Config();
        config.loadConfig();
    }
}