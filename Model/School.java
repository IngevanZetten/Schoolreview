/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Objects;

/**
 * Object waarin informatie horend bij een school uit data.overheid in
 * opgeslagen wordt.
 *
 * @author Inge
 */
public class School {

    private String schoolnaam;
    private String schoolsoort;
    private String websiteURL;

    /**
     * Constructor
     * @param schoolnaam Naam van de school
     * @param schoolsoort Soort onderwijs dat de school geeft.
     * @param websiteURL Website url van de school
     */
    public School(String schoolnaam, String schoolsoort, String websiteURL) {
        this.schoolnaam = schoolnaam;
        this.schoolsoort = schoolsoort;
        this.websiteURL = websiteURL;
    }

    /**
     * Default constructor
     */
    public School() {
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
     * @param schoolnaam Naam van de school
     */
    public void setSchoolnaam(String schoolnaam) {
        this.schoolnaam = schoolnaam;
    }

    /**
     * Geeft de waarde van de variabele schoolsoort
     * @return String schoolsoort
     */
    public String getSchoolsoort() {
        return schoolsoort;
    }

    /**
     * Stelt de waarde van variabele schoolsoort gelijk aan de parameter variabele.
     * @param schoolsoort Soort onderwijs dat de school geeft.
     */
    public void setSchoolsoort(String schoolsoort) {
        this.schoolsoort = schoolsoort;
    }

    /**
     * Geeft de waarde van de variabele WebsiteUrl
     * @return String WebsiteUrl
     */
    public String getWebsiteURL() {
        return websiteURL;
    }

    /**
     * Stelt de waarde van variabele WebsiteUrl gelijk aan de parameter variabele.
     * @param websiteURL Website url van de school.
     */
    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

}
