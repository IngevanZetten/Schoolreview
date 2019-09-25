/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datalayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Klasse die zorgt voor de opdrachten naar de database die horen bij het object Beoordelingstype.
 * @author Inge
 */
public class BeoordelaarstypeDAO {
    
    dbConnector dbConnector = new dbConnector();    
    
    /**
     * Haalt alle beoordelaarstypen op uit de database.
     * @return ArrayList String. Lijst die alle beoordelaarstypen die opgehaald zijn uit de database bevat.
     */
    public ArrayList<String> geefBeoordelingstypen(){
        
        Connection conn = null;
        ArrayList<String> typen = new ArrayList<>();
        ResultSet resultSet;
        
        try {
            
            conn = dbConnector.connect();
            Statement statement = conn.createStatement();
            String sql = "SELECT beoordelaarstype FROM beoordelaarstype";
            if(statement.execute(sql)){
                
                resultSet = statement.getResultSet();
                while(resultSet.next()){
                    typen.add(resultSet.getString("beoordelaarstype"));
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return typen;
    }
    
}
