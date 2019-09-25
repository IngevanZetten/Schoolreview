/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datalayer;


import Model.SchoolSoortReview;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Klasse die zorgt voor de opdrachten naar de database die horen bij het object SchoolSoortReview.
 * @author Inge
 */
public class SchoolSoortReviewDAO {
    
    dbConnector dbConnector = new dbConnector();

    /**
     * Haalt alle scholen met een beoordeling op uit de database.
     * @return ArrayList SchoolSoortReview Lijst met scholen die beoordeeld zijn door de gebruiker
     */
    public ArrayList<SchoolSoortReview> geefOverzichtScholen() {

        Connection conn = null;
        ArrayList<SchoolSoortReview> scholenoverzicht = new ArrayList<>();
        ResultSet resultSet;

        try {

            conn = dbConnector.connect();
            Statement statement = conn.createStatement();
            String sql = "SELECT school.onderwijssoort, review.schoolnaam, AVG(totaalscore) AS gemScore FROM review JOIN School ON review.schoolnaam = school.schoolnaam GROUP BY onderwijssoort, schoolnaam";

            if (statement.execute(sql)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {

                    String schoolnaam = resultSet.getString("schoolnaam");
                    String schoolsoort = resultSet.getString("onderwijssoort");
                    double gemScore = resultSet.getDouble("gemScore");

                    SchoolSoortReview ssr = new SchoolSoortReview(schoolnaam, schoolsoort, gemScore);
                    scholenoverzicht.add(ssr);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return scholenoverzicht;
    }

    /**
     * Haalt de schoolinformatie uit de database op van de school die als parameter meegegeven is.
     * @param schoolnaam Naam van de school waar informatie over gevraagd wordt.
     * @return SchoolSoortReview Object waarin de schoolinformatie wordt teruggegeven.
     */
    public SchoolSoortReview geefOverzichtPerSchool(String schoolnaam) {

        Connection conn = null;
        ResultSet resultSet;
        SchoolSoortReview ssr = null;
        

        try {

            conn = dbConnector.connect();
            Statement statement = conn.createStatement();
            String sql = "SELECT school.onderwijssoort, review.schoolnaam, AVG(totaalscore) AS gemScore FROM review JOIN School ON review.schoolnaam = school.schoolnaam WHERE review.schoolnaam = '" + schoolnaam + "'" + "GROUP BY onderwijssoort, schoolnaam";

            if (statement.execute(sql)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {

                    String schoolnm = resultSet.getString("schoolnaam");
                    String schoolsoort = resultSet.getString("onderwijssoort");
                    double gemScore = resultSet.getDouble("gemScore");

                    ssr = new SchoolSoortReview(schoolnm, schoolsoort, gemScore);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ssr;

    }

}
