/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progettoesamemap.db;

import java.sql.*;
import java.util.Properties;
import com.mycompany.progettoesamemap.manager.GameManager;

/**
 *
 * @author gabry
 */
public class DBSystem {

    public static final String CREATE_ROOMS = "CREATE TABLE IF NOT EXISTS rooms (id int PRIMARY KEY, name VARCHAR(100), desc VARCHAR(1000), look VARCHAR(1000), descReturn VARCHAR(1000))";
    public static final String CREATE_ITEM = "CREATE TABLE IF NOT EXISTS items (id int PRIMARY KEY, name VARCHAR(100), desc VARCHAR(1000))";

    public static final String SELECT1 = "SELECT id FROM rooms WHERE id=?";
    public static final String SELECT2 = "SELECT id FROM items WHERE id=?";

    public static final String INSERT1 = "INSERT INTO rooms VALUES (?,?,?,?,?)";
    public static final String INSERT2 = "INSERT INTO items VALUES (?,?,?)";

    private static Connection conn;
    private Properties prop;

    public DBSystem() {
        try {
            prop = properties();
            conn = connection(prop);
            createTable();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ":" + ex.getMessage());
        }
    }

    public void closeConnection() throws SQLException {
        getConnection().close();
    }

    public static Connection getConnection() throws SQLException {
        return conn;
    }

    public static Properties properties() {
        Properties prop = new Properties();
        prop.setProperty("user", "Gabry");
        prop.setProperty("password", "1234");
        return prop;
    }

    public static Connection connection(Properties prop) throws SQLException {
        return DriverManager.getConnection("jdbc:h2:./db", prop);
    }

    public static ResultSet readFromDb(String select, int idStatement) throws SQLException {
        PreparedStatement pstm = getConnection().prepareStatement(select);
        pstm.setInt(1, idStatement);
        ResultSet rs = pstm.executeQuery();
        return rs;
    }

    public void init(String select, int id, String queryInsert, String[] array) throws SQLException {
        ResultSet rs = readFromDb(select, id);
        if (!exists(rs, id)) {
            insertStringIntoTheTable(id, queryInsert, array);
        }
    }

    public Boolean exists(ResultSet resultSet, int id) throws SQLException {
        Boolean toReturn = false;
        while (resultSet.next()) {
            if (resultSet.getInt(1) == id) {
                toReturn = true;
            }
        }
        return toReturn;
    }

    public void insertStringIntoTheTable(int id, String insert, String[] array) throws SQLException {
        PreparedStatement pstm = getConnection().prepareStatement(insert);
        switch (array.length) {
            case 4:
                pstm.setInt(1, id);
                pstm.setString(2, array[0]);
                pstm.setString(3, array[1]);
                pstm.setString(4, array[2]);
                pstm.setString(5, array[3]);
                pstm.executeUpdate();
                break;
            case 2:
                pstm.setInt(1, id);
                pstm.setString(2, array[0]);
                pstm.setString(3, array[1]);
                pstm.executeUpdate();
                break;
            default:
                System.out.println("Errore su insertStringIntoTheTable" + id);
                break;
        }
        pstm.close();
    }

    public void createTable(String table) throws SQLException {
        try (Statement stat = getConnection().createStatement()) {
            stat.executeUpdate(table);
        }
    }

    public void createTable() throws SQLException {
        createTable(CREATE_ROOMS);
        createTable(CREATE_ITEM);
        populationTable();
    }

    public void populationTable() throws SQLException {

        String[] room1 = {"\n-----Avamposto-----",
            "Un piccolo accampamento con un attracco per le navi",
            GameManager.getOsserva(1),
            ""};
        init(SELECT1, 1, INSERT1, room1);

        String[] room2 = {"\n-----Ponte-----",
            "C'è un ponte.",
            GameManager.getOsserva(2),
            ""};
        init(SELECT1, 2, INSERT1, room2);

        String[] room3 = {"\n-----Campagne-----",
            "Un piccolo villaggio con terreni coltivati",
             GameManager.getOsserva(3),
            ""};
        init(SELECT1, 3, INSERT1, room3);

        String[] room4 = {"\n-----Elburg-----",
            "Il leggendario regno di Elburg",
            GameManager.getOsserva(4),
            ""};
        init(SELECT1, 4, INSERT1, room4);
        
        String[] room5 = {"\n-----Foresta fitta-----",
            "Una grande foresta, così fitta da non passarci nemmeno un raggio di sole",
            GameManager.getOsserva(4),
            ""};
        init(SELECT1, 5, INSERT1, room5);

        String[] object1 = {"mappa", "con questa potrò orientarmi meglio"};
        init(SELECT2, 1, INSERT2, object1);

        String[] object2 = {"spada", "ora sì che prima no!"};
        init(SELECT2, 2, INSERT2, object2);

    }
    
    public static void printInitialDescription(){
        System.out.println("------------------\t The Greedy Pirate \t----------------\n\n"
                + "\t   Quel goloso di un pirata ha saputo di una festa\n"
                + "\t      nel regno di Elburg! ci saranno sicuramente\n"
                + "\t tanti dolciumi, è proprio l'avventura che fa per lui!\n"
                + "\t  E allora tutti in viaggio verso l'isola di Elburg!\n\n"
                + "------------------------------------------------------------------");
    }
    
    public static void printFinalDescription(){
        System.out.println("------------------\t THE END \t------------------\n\n"
                + "\t       Festeggi e stai mangiando i dolciumi\n"
                + "\t    sei contento dell'avventura appena vissuta\n"
                + "\t    e pensi \"..magari su Steam c'è il sequel..\"\n\n"
                + "----------------------------------------------------------");
    }
}
