/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datalayer;

import Model.School;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Klasse die een connectie maakt met internet om de json data van data.overheid op te halen. 
 * @author Inge
 */
public class Json {

    /**
     * Constructor
     */
    public Json() {
    }

    /**
     * Haalt de data van data.overheid via json op en maakt een school object voor ieder object uit de 
     * jsonArray. Deze school objecten worden in een ArrayList gezet en terugggeven om deze te gebruiken in 
     * HomePaginaView.
     * @return ArrayList van School objecten die opgehaald zijn van data.overheid.
     */
    public ArrayList<School> geefScholenLijst() {

        ArrayList<School> scholenLijst = new ArrayList<>();

        StringBuilder result = new StringBuilder();
        JSONObject jsonObject = new JSONObject();
        try {

            URL url = new URL("https://services1.arcgis.com/vxtcdWjsjPHYpR1J/arcgis/rest/services/Zeist_Onderwijsinstellingen/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json");
            URLConnection connection = (URLConnection) url.openConnection();

            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputline;
            while ((inputline = reader.readLine()) != null) {
                result.append(inputline);
            }
            jsonObject = (JSONObject) JSONValue.parseWithException(result.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray features = (JSONArray) jsonObject.get("features");

        for (int i = 0; i < features.size(); i++) {

            School school = new School();
            JSONObject featureObject = (JSONObject) features.get(i);

            JSONObject attributes = (JSONObject) featureObject.get("attributes");

            school.setSchoolnaam((String) attributes.get("NAAM"));
            school.setSchoolsoort((String) attributes.get("SOORT_ONDE"));
            school.setWebsiteURL((String) attributes.get("WEBSITE"));

            scholenLijst.add(school);

        }
        return scholenLijst;
    }

}
