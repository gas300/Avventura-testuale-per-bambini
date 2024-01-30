/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettoesamemap.manager;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gabry
 */
public class FileManager {

    private static final String RISORSE_FILE = "./src/main/java/com/mycompany/progettoesamemap/resources/gameObjectsJson";
    private static final String ROOM_PATH = "./src/main/java/com/mycompany/progettoesamemap/resources/gameObjectsJson/room.json";
    private static final String NPCS_PATH = "./src/main/java/com/mycompany/progettoesamemap/resources/gameObjectsJson/npcs.json";
    private static final String STUFF_PATH = "./src/main/java/com/mycompany/progettoesamemap/resources/gameObjectsJson/stuff.json";

    private static ArrayList<String> toDownload = new ArrayList();

    private static DataInputStream dataInputStream = null;

    private static void directoryCreator() {
        File directory = new File(RISORSE_FILE);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public static void fileCheck() {

        directoryCreator();

        File room = new File(ROOM_PATH);
        File npc = new File(NPCS_PATH);
        File stuff = new File(STUFF_PATH);
        
        if (!room.exists()) {
            toDownload.add(ROOM_PATH);
            System.out.println("file > osserva not found" + toDownload.get(0));
        }
        if (!npc.exists()) {
            toDownload.add(NPCS_PATH);
            System.out.println("file > osserva not found" + toDownload.get(1));
        }
        if (!stuff.exists()) {
            toDownload.add(STUFF_PATH);
            System.out.println("file > osserva not found" + toDownload.get(2));
        }

        if (toDownload.size() > 0) {
            System.out.println("rilevati " + toDownload.size() + " file mancanti o corrotti , ripristino in corso...");
            for (int i = 0; i < toDownload.size(); i++) {
                try (Socket socket = new Socket("localhost", 5000)) {
                    dataInputStream = new DataInputStream(socket.getInputStream());
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                    String filePath = toDownload.get(i);
                    out.println(filePath);
                    System.out.println("Richiesto downlaod >" + filePath);
                    receiveFile(filePath, dataInputStream);
                    System.out.println("scaricati " + i + "file");

                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private static void receiveFile(String filePath, DataInputStream dataInputStream) throws Exception {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        long size = dataInputStream.readLong();
        byte[] buffer = new byte[4 * 1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes;
        }

    }

    public static ArrayList<String> getToDownlaod() {
        System.out.println("dimensione array toDownload -->" + toDownload.size());
        return toDownload;
    }
}

