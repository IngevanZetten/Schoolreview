/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datalayer;

import Model.Beoordeling;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Klasse die zorgt voor de opdrachten naar de database die horen bij het object
 * Beoordeling.
 *
 * @author Inge
 */
public class BeoordelingDAO {

    dbConnector dbConnector = new dbConnector();

    /**
     * Voegt een beoordeling toe aan de database.
     *
     * @param beoordeling Beoordeling object die toegevoegd moet worden aan de
     * database
     */
    public boolean voegBeoordelingToe(Beoordeling beoordeling) {

        Connection conn = null;
        Beoordeling rprt = beoordeling;

        String categorie = rprt.getBeoordelingscategorie();
        double score = rprt.getScore();
        String school = rprt.getSchoolnaam();

        try {

            conn = dbConnector.connect();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO beoordeling (beoordelingscategorie, sterscore, schoolnaam) VALUES('" + categorie + "',"
                    + score + ",'" + school + "')";

            statement.executeUpdate(sql);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Haalt gemiddelde scores per beoordelingscategorie op uit de database en
     * zet deze in een hashmap.
     *
     * @param schoolnaam Naam van de school waarvan de gemiddelde scores per
     * categorie gevraagd worden.
     * @return HashMap String, Double. HashMap die alle categorieën en
     * bijbehorende gemiddelde scores bevat die opgehaald zijn uit de database.
     */
    public HashMap<String, Double> geefScores(String schoolnaam) {

        Connection conn = null;
        ResultSet resultSet;
        HashMap<String, Double> scores = new HashMap<>();

        try {

            conn = dbConnector.connect();
            Statement statement = conn.createStatement();
            String sql = "SELECT beoordelingscategorie, AVG(sterscore) FROM `beoordeling` WHERE schoolnaam = '" + schoolnaam + "'" + " GROUP BY beoordelingscategorie";

            if (statement.execute(sql)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {

                    String cat = resultSet.getString("beoordelingscategorie");
                    double gemscore = resultSet.getDouble("AVG(sterscore)");

                    scores.put(cat, gemscore);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return scores;

    }

}
