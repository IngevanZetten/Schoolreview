/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 * Object waar een beoordelingscategorie in opgeslagen wordt.
 * @author Inge
 */
public class Beoordelingscategorie {

    private String beoordelingscategorie;

    /**
     * Constructor
     * @param beoordelingscategorie Categorie waarop een school beoordeeld kan worden.
     */
    public Beoordelingscategorie(String beoordelingscategorie) {
        this.beoordelingscategorie = beoordelingscategorie;
    }

    /**
     * Geeft de waarde van de variabele totaalscore
     * @return String beoordelingscategorie
     */
    public String getBeoordelingscategorie() {
        return beoordelingscategorie;
    }

    /**
     * Stelt de waarde van variabele beoordelingscategorie gelijk aan de parameter
     * variabele.
     * @param beoordelingscategorie Categorie waarop een school beoordeeld kan worden
     */
    public void setBeoordelingscategorie(String beoordelingscategorie) {
        this.beoordelingscategorie = beoordelingscategorie;
    }

}
