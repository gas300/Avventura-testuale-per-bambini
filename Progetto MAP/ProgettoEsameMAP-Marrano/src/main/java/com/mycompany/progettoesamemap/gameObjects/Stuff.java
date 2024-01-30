/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettoesamemap.gameObjects;

/**
 *
 * @author gabry
 */
public class Stuff extends Character {

    private boolean takable;
    private boolean inventory = false;
    private final String description;

    public Stuff(boolean takable, String description, int id, int room, String name) {
        super(id, room, name);
        this.takable = takable;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean getTakable() {
        return takable;
    }

    public boolean getInventory() {
        return inventory;
    }
    
    //metodo di raccolta oggetto
    @Override
    public void eseguiAzione() {
        if (takable) {
            inventory = true;
            System.out.println("inserisci nel tuo inventario " + getName()
                    + ", \nla osservi e noti:" + getDescription());
        }
    }

}
