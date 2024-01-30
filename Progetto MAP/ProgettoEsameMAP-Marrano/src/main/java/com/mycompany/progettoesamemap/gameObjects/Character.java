/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettoesamemap.gameObjects;

/**
 *
 * @author gabry
 */
public abstract class Character {
    private final String name;
    private final int id;
    private final int room;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getRoom() {
        return room;
    }

    public Character(int id, int room, String name) {
        this.name = name;
        this.id = id;
        this.room = room;
    }


    public abstract void eseguiAzione();
}
