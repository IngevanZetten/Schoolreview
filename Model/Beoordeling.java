/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Object waarin informatie horend bij een beoordeling in opgeslagen wordt.
 * @author Inge
 */
public class Beoordeling {

    private String beoordelingscategorie;
    private double score;
    private String schoolnaam;

    /**
     * Constructor
     * @param beoordelingscategorie Categorie waarop een school beoordeeld wordt.
     * @param score sterscore die door de gebruiker gegeven is aan een categorie.
     * @param schoolnaam Naam van de school.
     */
    public Beoordeling(String beoordelingscategorie, double score, String schoolnaam) {

        this.beoordelingscategorie = beoordelingscategorie;
        this.score = score;
        this.schoolnaam = schoolnaam;
    }

    /**
     * Geeft de waarde van de variabele beoordelingscategorie
     * @return String beoordelingscategorie
     */
    public String getBeoordelingscategorie() {
        return beoordelingscategorie;
    }

    /**
     * Stelt de waarde van variabele beoordelingscategorie gelijk aan de parameter
     * variabele.
     * @param beoordelingscategorie Categorie waarop een school beoordeeld wordt.
     */
    public void setBeoordelingscategorie(String beoordelingscategorie) {
        this.beoordelingscategorie = beoordelingscategorie;
    }

    /**
     * Geeft de waarde van de variabele score
     * @return double score
     */
    public double getScore() {
        return score;
    }

    /**
     * Stelt de waarde van variabele score gelijk aan de parameter
     * variabele.
     * @param score De sterscore die door de gebruiker aan een categorie gegeven wordt.
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Geeft de waarde van de variabele schoolnaam
     * @return String schoolnaam
     */
    public String getSchoolnaam() {
        return schoolnaam;
    }

    /**
     * Stelt de waarde van variabele schoolnaam gelijk aan de parameter
     * variabele.
     * @param schoolnaam Naam van de school
     */
    public void setSchoolnaam(String schoolnaam) {
        this.schoolnaam = schoolnaam;
    }

}
