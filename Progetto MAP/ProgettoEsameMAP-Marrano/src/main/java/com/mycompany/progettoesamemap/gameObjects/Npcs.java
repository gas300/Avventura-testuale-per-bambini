/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettoesamemap.gameObjects;

/**
 *
 * @author gabry
 */
public class Npcs extends Character {
    boolean alive = true;
    String toSay;

    public Npcs(String toSay, int id, int room, String name) {
        super(id, room, name);
        this.toSay = toSay;
    }
        
    public boolean isAlive() {
        return this.alive;
    }
    
    public void setDead() {
        this.alive = false;
    }
    
    public String getToSay() {
        return toSay;
    }

    //metodo per uccidere un npc
    @Override
    public void eseguiAzione() {
        this.alive = false;
    }

}
