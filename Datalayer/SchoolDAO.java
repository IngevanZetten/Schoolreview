/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datalayer;

import Model.School;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Klasse die zorgt voor de opdrachten naar de database die horen bij het object School.
 * @author Inge
 */
public class SchoolDAO {
    
    dbConnector dbConnector = new dbConnector();

    /**
     * Voegt een school toe aan de database
     * @param school School object dat toegevoegd moet worden aan de database
     */
    public boolean voegSchoolToe(School school) {

        Connection conn = null;
        School schl = school;

        String schoolnaam = schl.getSchoolnaam();
        String onderwijssoort = schl.getSchoolsoort();
        String website = schl.getWebsiteURL();

        try {

            conn = dbConnector.connect();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO school VALUES('" + schoolnaam + "','"
                    + onderwijssoort + "','" + website + "')";

            statement.executeUpdate(sql);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;

    }

    /**
     * Haalt alle schoolnamen op die opgeslagen zijn in de database
     * @return ArrayList String Lijst met schoolnamen die opgehaald zijn uit de database
     */
    public ArrayList<String> geefScholenOpgeslagen() {

        Connection conn = null;
        ArrayList<String> scholenOpgeslagen = new ArrayList<>();
        ResultSet resultSet;

        try {

            conn = dbConnector.connect();
            Statement statement = conn.createStatement();
            String sql = "SELECT Schoolnaam FROM school";

            if (statement.execute(sql)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    String schoolnaam = resultSet.getString("Schoolnaam");

                    scholenOpgeslagen.add(schoolnaam);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return scholenOpgeslagen;
    }

    /**
     * Haalt de website url van de school die meegegeven wordt als parameter op uit de database.
     * @param schoolnaam Naam van de school waarvan de website gevraagd wordt.
     * @return String WebsiteUrl 
     */
    public String geefWebsite(String schoolnaam) {

        Connection conn = null;
        ResultSet resultSet;
        String website = null;

        try {

            conn = dbConnector.connect();
            Statement statement = conn.createStatement();
            String sql = "SELECT WebsiteUrl FROM school WHERE schoolnaam = '" + schoolnaam + "'";

            if (statement.execute(sql)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {

                    website = resultSet.getString("WebsiteUrl");

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
            return website;
    }
}
