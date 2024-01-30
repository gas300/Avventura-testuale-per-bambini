/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettoesamemap.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author gabry
 */
public class Loader {

    public static void main(String[] args) {
        Map<String, List<String>> noObjAction = new HashMap<>();
        noObjAction = Loader.loadDictionary("noObjAction");
        printMap(noObjAction);
    }

    @SuppressWarnings("unchecked")
    //carica il dizionario dalla directory, poi con Map.class lo deserializza
    //come oggetto Map
    public static Map<String, List<String>> loadDictionary(String dictionary) {
        try {
            return new Gson().fromJson(new FileReader(
                    "src\\main\\java\\com\\mycompany\\progettoesamemap\\resources\\dictionaries\\" + dictionary + ".json"),
                    Map.class);
        } catch (IOException e) {
            System.out.println("errore nel caricamento del dizionario " + dictionary);
            return null;
        }
    }

    public static ArrayList<String> loadList(String fileName) {
        ArrayList<String> wordsList = new ArrayList<>();
        String filePath = "src\\main\\java\\com\\mycompany\\progettoesamemap\\resources\\fileTxt\\" + fileName + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.lines().flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .forEach(wordsList::add);
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato: " + filePath);
        } catch (IOException e) {
            System.out.println("Errore durante il caricamento del file: " + filePath);
        }
        return wordsList;
    }

    //checker per il caricamente delle mappe
    public static void printMap(Map<String, List<String>> map) {
        if (map == null) {
            System.out.println("La mappa è null.");
            return;
        }

        if (map.isEmpty()) {
            System.out.println("La mappa è vuota.");
            return;
        }

        System.out.println("Contenuti della mappa:");
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String chiave = entry.getKey();
            List<String> listaDiValori = entry.getValue();

            System.out.println("Chiave: " + chiave);

            if (listaDiValori != null && !listaDiValori.isEmpty()) {
                System.out.println("  Valori:");
                for (String valore : listaDiValori) {
                    System.out.println("    " + valore);
                }
            } else {
                System.out.println("  Lista di valori è vuota o null.");
            }
        }
    }
    
       public static String getHelpText() {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\java\\com\\mycompany\\progettoesamemap\\resources\\fileTxt\\help.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}

