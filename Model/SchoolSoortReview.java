/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Object dat nodig is om het resultaat van een join in op te slaan.
 * @author Inge
 */
public class SchoolSoortReview {
    
    private String schoolnaam;
    private String schoolsoort;
    private double gemScore;

    /**
     * Constructor
     * @param schoolnaam naam van de school.
     * @param schoolsoort soort onderwijs dat de school geeft.
     * @param gemScore gemiddelde van alle totaalscores die de school heeft gekregen.
     */
    public SchoolSoortReview(String schoolnaam, String schoolsoort, double gemScore) {
        this.schoolnaam = schoolnaam;
        this.schoolsoort = schoolsoort;
        this.gemScore = gemScore;
    }

    /**
     * Geeft de waarde van de variabele schoolnaam
     * @return String schoolnaam
     */
    public String getSchoolnaam() {
        return schoolnaam;
    }

    /**
     * Stelt de waarde van variabele schoolnaam gelijk aan de parameter variabele.
     * @param schoolnaam naam van de school.
     */
    public void setSchoolnaam(String schoolnaam) {
        this.schoolnaam = schoolnaam;
    }

    /**
     * Geeft de waarde van de variabele schoolsoort.
     * @return String schoolsoort
     */
    public String getSchoolsoort() {
        return schoolsoort;
    }

    /**
     * Stelt de waarde van variabele schoolsoort gelijk aan de parameter variabele.
     * @param schoolsoort soort onderwijs dat de school geeft.
     */
    public void setSchoolsoort(String schoolsoort) {
        this.schoolsoort = schoolsoort;
    }

    /**
     * Geeft de waarde van de variabele gemiddelde score.
     * @return double Gemiddele Score
     */
    public double getGemScore() {
        return gemScore;
    }

    /**
     * Stelt de waarde van variabele gemiddelde score gelijk aan de parameter variabele.
     * @param gemScore gemiddelde van alle totaalscores die de school heeft gekregen.
     */
    public void setGemScore(double gemScore) {
        this.gemScore = gemScore;
    }
    
    
    
}
