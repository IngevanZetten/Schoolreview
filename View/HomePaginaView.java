/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Datalayer.Json;
import Model.School;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;

/**
 * Scherm dat je ziet als de applicatie opstart. In dit scherm worden scholen van data.overheid opgehaald en kan de gebruiker 
 * een school kiezen om een review aan te geven in ReviewView. Of er kan doorgeklikt worden naar SchoolOverzichtView.
 * @author Inge
 * @version 1.0
 * @see ReviewView
 * @see ScholenOverzichtView
 */
public class HomePaginaView extends BorderPane {

    private Label lblSchoolReviewen, lblSchoolBekijken, lbltekstReviewen, lbltekstBeijken;
    private Button btnAlleScholen, btnReviewen;
    private TextField tfScholen;
    private Json json;
    private Header header = new Header();
    private ArrayList<School> scholen = null;

    /**
     * Constructor van HomePaginaView
     */
    public HomePaginaView() {

        //json
        this.json = new Json();

        //header
        VBox hBoxHeader = header.geefHeader();

        //layout links
        GridPane layoutLinks = new GridPane();
        layoutLinks.setId("layout");

        //layout rechts
        GridPane layoutRechts = new GridPane();
        layoutRechts.setId("layout");

        //labels
        this.lblSchoolReviewen = new Label("Beoordeel uw school");
        lblSchoolReviewen.getStyleClass().add("lbl");
        this.lblSchoolBekijken = new Label("Bekijk alle scholen");
        lblSchoolBekijken.getStyleClass().add("lbl");
        this.lbltekstBeijken = new Label("Bent u op zoek naar een school voor uw kind of voor uzelf? Bekijk dan hier alle scholen die beoordeelt zijn door ervaringsdeskundigen en maak uw keus!");
        lbltekstBeijken.getStyleClass().add("lbltekst");
        this.lbltekstReviewen = new Label("Wilt u dat de kwaliteit van uw school verbetert en wilt u schoolkiezers helpen een betere keuze te maken? Beoordeel dan hier uw school!");
        lbltekstReviewen.getStyleClass().add("lbltekst");

        //textfield
        this.tfScholen = new TextField();
        tfScholen.setPromptText("zoek een school");
        tfScholen.setPrefSize(380, 40);

        ArrayList<String> schoolnamen = new ArrayList<>();
        //autocomplete search schoolnamen
        new Thread() {
           
            @Override
            public void run() {

                scholen = json.geefScholenLijst();

                for (School schl : scholen) {
                    schoolnamen.add(schl.getSchoolnaam());

                }
                TextFields.bindAutoCompletion(tfScholen, t -> {
                    return schoolnamen.stream().filter(elem
                            -> {
                        return elem.toLowerCase().startsWith(t.getUserText().toLowerCase());
                    }).collect(Collectors.toList());
                }).setMinWidth(380);
            }
        }.start();

        //buttons
        this.btnAlleScholen = new Button("Alle scholen");
        btnAlleScholen.setPrefWidth(450);

        //navigeer naar ScholenOverzichtView
        btnAlleScholen.setOnAction(e -> {

            btnAlleScholen.getScene().setRoot(new ScholenOverzichtView());

        });

        this.btnReviewen = new Button("Review");
        btnReviewen.setPrefWidth(100);

        //Zorg ervoor dat de knop om naar het reviewscherm te gaan reageert op Enter.
        //Dan hoeft er niet meer met de muis op geklikt te worden.
        tfScholen.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                btnReviewen.fire();
                ev.consume();
            }
        });

        //navigeer naar ReviewView
        btnReviewen.setOnAction(e -> {

            if ((tfScholen.getText().equals(""))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Helaas een fout!");
                alert.setContentText("U heeft geen school ingevoerd!");
                alert.showAndWait();
            }
            else if (!schoolnamen.contains(tfScholen.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Helaas een fout!");
                alert.setContentText("Deze school bestaat niet in Zeist!");
                alert.showAndWait();
                tfScholen.clear();

            } else {

                String target = tfScholen.getText();

                //geef schoolobject van json met de naam uit het tekstveld mee naar reviewView
                School school = null;

                for (School schl : scholen) {
                    if (target.equals(schl.getSchoolnaam())) {
                        school = schl;
                    }
                }

                ReviewView.setSchool(school);
                btnReviewen.getScene().setRoot(new ReviewView());
            }

        });

        //hboxButtons
        HBox hboxZoekSchool = new HBox();
        hboxZoekSchool.getChildren().addAll(tfScholen, btnReviewen);
        hboxZoekSchool.setAlignment(Pos.CENTER);

        VBox vboxLinks = new VBox();
        vboxLinks.setSpacing(40);
        vboxLinks.setAlignment(Pos.CENTER);
        VBox vboxRechts = new VBox();
        vboxRechts.setSpacing(40);
        vboxRechts.setAlignment(Pos.CENTER);

        vboxLinks.getChildren().addAll(lblSchoolReviewen, lbltekstReviewen, hboxZoekSchool);
        vboxRechts.getChildren().addAll(lblSchoolBekijken, lbltekstBeijken, btnAlleScholen);

        layoutLinks.add(vboxLinks, 0, 0);
        layoutRechts.add(vboxRechts, 0, 0);

        setLeft(layoutLinks);
        setRight(layoutRechts);
        setTop(hBoxHeader);
        setCenter(null);

        this.getStylesheets().add("/util/CSSHomePagina.css");

    }

}
