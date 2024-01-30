/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettoesamemap.gameObjects;

/**
 *
 * @author gabry
 */
public class Room {
    private int id;
    private String name;
    private String description;
    private int nord;
    private int sud;
    private int ovest;
    private int est;

    public Room(int id, String name, String description) {
        this.id = id;
        this.description = description;
        this.name = name;   
    }
    
    public void addDirections(int north, int east, int weast, int sud) {
        this.nord = north;
        this.est = east;
        this.ovest = weast;
        this.sud = sud;
    }

    public void checkCreatedRoom(){
        System.out.println("id: " + this.id +
                "name: " + this.name +
                "description" + this.description);
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNord() {
        return nord;
    }

    public int getSud() {
        return sud;
    }

    public int getOvest() {
        return ovest;
    }

    public int getEst() {
        return est;
    }
    
    
    
    
}
