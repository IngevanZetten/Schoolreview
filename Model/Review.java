/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 * Object waarin de informatie horend bij een review opgeslagen wordt.
 *
 * @author Inge
 */
public class Review {

    private Date revDatum;
    private String reviewTekst;
    private double totaalscore;
    private String schoolnaam;
    private String beoordelaarstype;

    /**
     * Constructor
     *
     * @param revDatum Datum waarop de review gegeven wordt.
     * @param reviewTekst Opmerking die geschreven wordt door gebruiker
     * @param totaalscore Ster score die de gebruiker geeft.
     * @param schoolnaam Naam van de school
     * @param beoordelaarstype Type beoordelaar, student, ouder, overig
     */
    public Review(Date revDatum, String reviewTekst, double totaalscore, String schoolnaam, String beoordelaarstype) {
        this.revDatum = revDatum;
        this.reviewTekst = reviewTekst;
        this.totaalscore = totaalscore;
        this.schoolnaam = schoolnaam;
        this.beoordelaarstype = beoordelaarstype;
    }

    /**
     * Geeft de waarde van de variabele review datum
     *
     * @return Date review datum
     */
    public Date getRevDatum() {
        return revDatum;
    }

    /**
     * Stelt de waarde van variabele review datum gelijk aan de parameter
     * variabele.
     *
     * @param revDatum datum waarop de review gegeven wordt.
     */
    public void setRevDatum(Date revDatum) {
        this.revDatum = revDatum;
    }

    /**
     * Geeft de waarde van de variabele reviewtekst
     *
     * @return String reviewtekst
     */
    public String getReviewTekst() {
        return reviewTekst;
    }

    /**
     * Stelt de waarde van variabele reviewtekst gelijk aan de parameter
     * variabele.
     *
     * @param reviewTekst Opmerking die de gebruiker bij een review heeft
     * geschreven.
     */
    public void setReviewTekst(String reviewTekst) {
        this.reviewTekst = reviewTekst;
    }

    /**
     * Geeft de waarde van de variabele totaalscore
     *
     * @return double totaalscore
     */
    public double getTotaalscore() {
        return totaalscore;
    }

    /**
     * Stelt de waarde van variabele totaalscore gelijk aan de parameter
     * variabele.
     *
     * @param totaalscore Ster score die de gebruiker heeft gegeven als
     * totaalscore
     */
    public void setTotaalscore(double totaalscore) {
        this.totaalscore = totaalscore;
    }

    /**
     * Geeft de waarde van de variabele schoolnaam
     *
     * @return String schoolnaam
     */
    public String getSchoolnaam() {
        return schoolnaam;
    }

    /**
     * Stelt de waarde van variabele schoolnaam gelijk aan de parameter
     * variabele.
     *
     * @param schoolnaam Naam van de school
     */
    public void setSchoolnaam(String schoolnaam) {
        this.schoolnaam = schoolnaam;
    }

    /**
     * Geeft de waarde van de variabele beoordelaarstype
     *
     * @return String beoordelaarstype
     */
    public String getBeoordelaarstype() {
        return beoordelaarstype;
    }

    /**
     * Stelt de waarde van variabele beoordelaarstype gelijk aan de parameter
     * variabele.
     *
     * @param beoordelaarstype Type beoordelaar
     */
    public void setBeoordelaarstype(String beoordelaarstype) {
        this.beoordelaarstype = beoordelaarstype;
    }

}
