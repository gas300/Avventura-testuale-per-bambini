/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettoesamemap.parser;

import com.mycompany.progettoesamemap.gui.MapFrame;
import com.mycompany.progettoesamemap.gui.MusicFrame;
import com.mycompany.progettoesamemap.utilities.Loader;
import com.mycompany.progettoesamemap.manager.GameManager;
import static com.mycompany.progettoesamemap.manager.GameManager.cambioStanza;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.mycompany.progettoesamemap.gui.Help;

/**
 *
 * @author gabry
 */
public class Parser {

    private ArrayList<String> stopWord;
    private Map<String, List<String>> directions;
    private Map<String, List<String>> noObjAction;
    private Map<String, List<String>> ObjAction;
    //Se si avessero più oggetti che permettono  azioni si potrebbe usare
    //Una lista di costanti
    private final String STUFF_FOR_ACTIONS = "mappa";
    private final String NPC_BOSS = "Pignatta"; 

    public Parser() {
        this.stopWord = Loader.loadList("stopWords");
        this.directions = Loader.loadDictionary("directions");
        this.noObjAction = Loader.loadDictionary("noObjAction");
        this.ObjAction = Loader.loadDictionary("objAction");
    }

    public static void main(String[] args) {
        Parser p = new Parser();
    }

    public void parserGame(String input) {
        convertiInMinuscolo(input);
        ArrayList<String> inputList = ottieniListaParole(input);
        rimuoviStopword(inputList);
        checkWords(inputList);
    }

    public Map<String, List<String>> getNoObjAction() {
        return noObjAction;
    }

    public void setNoObjAction(Map<String, List<String>> noObjAction) {
        this.noObjAction = noObjAction;
    }

    public Map<String, List<String>> getObjAction() {
        return ObjAction;
    }

    public void setObjAction(Map<String, List<String>> ObjAction) {
        this.ObjAction = ObjAction;
    }

    private void convertiInMinuscolo(String input) {
        input.toLowerCase();
    }

    private ArrayList<String> ottieniListaParole(String frase) {
        String[] arrayParole = frase.split("\\s+");
        ArrayList<String> listaParole = new ArrayList<>(Arrays.asList(arrayParole));
        return listaParole;
    }

    private void rimuoviStopword(ArrayList<String> listaParole) {
        listaParole.removeAll(stopWord);
    }

    public void checkWords(List<String> inputWords) {
        /*
        presa una chiave e la lista di valori associata, controllo se l'input
        e' una dei sinonimi della chiave, restituisco la chiave.
         */
        boolean find = false;
        //check spostamento direzione
        for (Map.Entry<String, List<String>> entry : directions.entrySet()) {
            String directionKey = entry.getKey();
            List<String> directionValues = entry.getValue();

            for (String inputWord : inputWords) {
                if (directionValues.contains(inputWord)) {
                    cambioStanza(inputWord);
                    find = true;
                    return;
                }
            }
        }
        
        //check se è una objAction
        for (Map.Entry<String, List<String>> entry : ObjAction.entrySet()) {
            String objActionKey = entry.getKey();
            List<String> objActionValues = entry.getValue();

            for (String inputWord : inputWords) {
                if (objActionValues.contains(inputWord)) {
                    //l'azione passata è la chiave in modo che il metodo al 
                    //parsing basti riconoscere la parola e non i suoi sinonimi.
                    objActionParsing(objActionKey, inputWords);
                    find = true;
                    return;

                }
            }

        }
        //check noObjAction
        for (Map.Entry<String, List<String>> entry : noObjAction.entrySet()) {
            String noObjActionKey = entry.getKey();
            List<String> noObjActionValues = entry.getValue();
            for (String inputWord : inputWords) {
                if (noObjActionValues.contains(inputWord)) {
                    //chiamare metodo per attivare il noObj comand 
                    //e gli passo solo la key
                    noObjActionParsing(noObjActionKey);
                    find = true;
                    return;
                }
            }
        }

        System.out.println("ma come parli pirata?");
    }

    private void noObjActionParsing(String action) {
        switch (action) {
            case "osserva":
                System.out.println(GameManager.getOsserva(GameManager.getCurrentRoom()));
                break;
            case "inventario":
                GameManager.printInventory();
                break;
            case "mappa":
                apriMappa();
                break;
            case "musica":
                apriMusica();
                break;
            case "help":
                apriHelp();
                break;
        }
    }

    /*il metodo controlla preso il comando se il character (o sottoclassi) 
      presenti nella stanza sono nella frase
     */
    private void objActionParsing(String action, List<String> inputWords) {
        Scanner scan = new Scanner(System.in);

        switch (action) {
            case "parla":
                parla(inputWords, scan);
                break;
            case "raccogli":
                raccogli(inputWords, scan);
                break;
            case "uccidi":
                uccidi(inputWords, scan);
                break;

        }

    }

    private void parla(List<String> inputWords, Scanner scan) {
        String npcInRoomName = GameManager.getNpcNameInRoom();
        if (npcInRoomName.equals("null")) {
            System.out.println("Non c'è nessuno con cui parlare qui");
        } else {
            if (inputWords.contains(npcInRoomName)) {
                if (GameManager.getNpcAliveInRoom()) {
                    System.out.println(GameManager.getNpcToSayInRoom());
                } else {
                    System.out.println("Guarda che hai ucciso " + npcInRoomName + ".. (guarda che non è una cosa bella)");
                }
            } else {
                if (GameManager.getNpcAliveInRoom()) {
                    System.out.println("Vedo " + npcInRoomName
                            + ". Vuoi parlare con lui? \nDigita s per il si, "
                            + "oppure n per il no.");
                    if (scan.nextLine().equals("s")) {
                        System.out.println(GameManager.getNpcToSayInRoom());
                    }
                } 
            }
        }
    }
    
    private void uccidi(List<String> inputWords, Scanner scan) {
        String npcInRoomName = GameManager.getNpcNameInRoom();
        if (npcInRoomName.equals("null")) {
            System.out.println("Non c'è nessuno con cui da attaccare qui");
        } else {
            if (inputWords.contains(npcInRoomName)) {
                if (GameManager.getNpcAliveInRoom()) {
                    if (npcInRoomName.equals(NPC_BOSS)){
                        GameManager.setDeadNpcInRoom();
                        System.out.println("Hai ucciso " + npcInRoomName + " ora puoi goderti tutti i "
                            + "dolciumi contenuti al suo interno");
                    } else {
                        GameManager.setDeadNpcInRoom();
                        System.out.println("Ma che hai fatto? hai ucciso " + npcInRoomName);
                    }
                } else {
                    System.out.println("Guarda che hai già ucciso " + npcInRoomName + ".. (guarda che non è una cosa bella)");
                }
            } else {
                if (GameManager.getNpcAliveInRoom()) {
                    System.out.println("Vedo " + npcInRoomName
                            + ". Vuoi attaccarlo? \n digita s per il si, "
                            + "oppure n per il no.");
                    if (scan.nextLine().equals("s")) {
                        if (npcInRoomName.equals(NPC_BOSS)){
                            GameManager.setDeadNpcInRoom();
                            System.out.println("Hai ucciso " + npcInRoomName + " ora puoi goderti tutti i "
                            + "dolciumi contenuti al suo interno");
                        } else {
                            GameManager.setDeadNpcInRoom();
                            System.out.println("Ma che hai fatto? hai ucciso " + npcInRoomName);
                        }
                    }
                } 
            }
        }
    }

    private void raccogli(List<String> inputWords, Scanner scan) {
        String stuffInRoom = GameManager.getStuffNameInRoom();
        if (stuffInRoom.equals("null")) {
            System.out.println("Non posso raccogliere niente qui");
        } else {
            if (inputWords.contains(stuffInRoom)) {
                GameManager.raccogli();
            } else {
                System.out.println("Vedo " + stuffInRoom
                        + ". Vuoi raccoglierlo? s\n");
                if (scan.nextLine().equals("s")) {
                    GameManager.raccogli();
                }
            }
        }
    }

    private void apriMappa() {
        if (GameManager.stuffInInventory("mappa")){
            MapFrame.Mappa();
        } else{
            System.out.println("\nNon hai una mappa nel tuo inventario");
        }
        
    }

    private void apriMusica() {
        MusicFrame.avviaMusica();
    }
    
    private void apriHelp(){
        Help.openHelpWindow();
    }
}

