/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datalayer;

import Model.Review;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Klasse die zorgt voor de opdrachten naar de database die horen bij het object
 * Review.
 *
 * @author Inge
 */
public class ReviewDAO {

    dbConnector dbConnector = new dbConnector();

    /**
     * Voegt een review toe aan de database
     *
     * @param review Review object dat toegevoegd moet worden aan de database
     */
    public boolean voegReviewToe(Review review) {

        Connection conn = null;
        Review review1 = review;

        Date date = review1.getRevDatum();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String rvtekst = review1.getReviewTekst();
        double tlscore = review1.getTotaalscore();
        String schoolnaam = review1.getSchoolnaam();
        String beoordelaar = review1.getBeoordelaarstype();

        try {

            conn = dbConnector.connect();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO review (datum, reviewtekst, totaalscore, schoolnaam, beoordelaarstype ) "
                    + "VALUES('" + df.format(date).toString() + "','"
                    + rvtekst + "'," + tlscore + ",'" + schoolnaam + "','" + beoordelaar + "')";

            statement.executeUpdate(sql);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Haalt alle reviews van de school die meegegeven wordt als parameter op
     * uit de database.
     *
     * @param schoolnaam Naam van de school waarvan de reviews opgehaald moeten
     * worden.
     * @return ArrayList met Review objecten die opgehaald zijn uit de database.
     */
    public ArrayList<Review> geefReviewsSchool(String schoolnaam) {

        Connection conn = null;
        ArrayList<Review> scholenReviews = new ArrayList<>();
        ResultSet resultSet;

        try {

            conn = dbConnector.connect();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM review WHERE schoolnaam = '" + schoolnaam + "'";

            if (statement.execute(sql)) {
                resultSet = statement.getResultSet();
                while (resultSet.next()) {

                    Date datum = resultSet.getDate("datum");
                    String reviewtekst = resultSet.getString("reviewtekst");
                    double tlscore = resultSet.getDouble("totaalscore");
                    String schoolnm = resultSet.getString("schoolnaam");
                    String bdlr = resultSet.getString("beoordelaarstype");

                    Review review = new Review(datum, reviewtekst, tlscore, schoolnaam, bdlr);
                    scholenReviews.add(review);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return scholenReviews;
    }
}
