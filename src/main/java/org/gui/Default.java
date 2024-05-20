package org.gui;

public class Default {

    // Size 
    private int screen_width = 1100;
    private int screen_height = 700;
    
    // Color 
    private String color1 = "#F1E4C3";
    private String color2 = "#614124";

    protected int getWidth(){
        return screen_width;
    }

    protected int getHeight(){
        return screen_height;
    }

    protected String getColor1(){
        return color1;
    }

    protected String getColor2(){
        return color2;
    }
}
