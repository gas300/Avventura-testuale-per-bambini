/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettoesamemap.manager;

import com.mycompany.progettoesamemap.db.DBSystem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gabry
 */
public class DBManager {

    public static void DBStart() {

        DBSystem db = new DBSystem();
        Connection connection = null;
        try {
            connection = db.connection(db.properties());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printInitialDescription(){
        DBSystem.printInitialDescription();
    }
    
    public static void printFinalDescription(){
        DBSystem.printFinalDescription();
    }
    
    
    public static void getRoomDescriptionFromDB() {
        int roomId = GameManager.getCurrentRoom();
        String selectQuery = "SELECT name, desc, look, descReturn FROM rooms WHERE id = ?";
        try {
            ResultSet resultSet = DBSystem.readFromDb(selectQuery, roomId);
            while (resultSet.next()) {
                String roomName = resultSet.getString("name");
                String roomDescription = resultSet.getString("desc");
                System.out.println(roomName);
                System.out.println(roomDescription);
            }
        } catch (SQLException e) {
            System.err.println("Errore nella lettura del db");
        }
    }
}

