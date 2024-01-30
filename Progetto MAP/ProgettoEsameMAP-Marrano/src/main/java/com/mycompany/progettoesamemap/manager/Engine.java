/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.progettoesamemap.manager;

import static com.mycompany.progettoesamemap.manager.GameManager.printRooms;
import com.mycompany.progettoesamemap.utilities.JsonReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author gabry
 */
public class Engine {

    public static void main(String[] args) {
        FileManager.fileCheck();
        GameManager.launcher();
    }

}
