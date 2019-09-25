/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 * Object waar een beoordelaarstype in opgeslagen wordt.
 *
 * @author Inge
 */
public class BeoordelaarsType {

    private String beoordelaarstype;

    /**
     * Constructor
     *
     * @param beoordelaarstype Type van de beoordelaar
     */
    public BeoordelaarsType(String beoordelaarstype) {
        this.beoordelaarstype = beoordelaarstype;
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
     * @param beoordelaarstype Type van de beoordelaar
     */
    public void setBeoordelaarstype(String beoordelaarstype) {
        this.beoordelaarstype = beoordelaarstype;
    }

}
