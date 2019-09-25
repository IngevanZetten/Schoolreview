/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Datalayer.BeoordelaarstypeDAO;
import Datalayer.BeoordelingDAO;
import Datalayer.ReviewDAO;
import Datalayer.SchoolDAO;
import Model.Beoordeling;
import Model.Review;
import Model.School;
import java.util.ArrayList;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

/**
 * Het scherm waarin de gebruiker een review kan geven aan een school. De school
 * wordt meegegeven vanuit HomePaginaView.
 *
 * @author Inge
 * @version 1.0
 * @see HomePaginaView
 */
public class ReviewView extends VBox {

    private Header header = new Header();
    private ScrollPane scrollPane = new ScrollPane();
    private Label lblSchoolnaam, lblLeraren, lblLesprogramma, lblOrganisatie, lblProblemen, lblContact, lblVeiligheid,
            lblOpmerkingen, lblTotaal, lblCombobox;
    private Rating ratingLeraren, ratingLesprogramma, ratingOrganisatie, ratingProblemen, ratingContact, ratingVeiligheid,
            ratingTotaal;
    private VBox vboxrapport1, vboxrapport2, vboxReview;
    private HBox hboxrapport, hboxButtons, hboxBeoordelaar;
    private TextArea taOpmerking;
    private ComboBox cmbBeoordelaar;
    private Button btnOpslaan, btnAnnuleren;
    private BeoordelaarstypeDAO beoordelingstypeDAO = new BeoordelaarstypeDAO();
    private ReviewDAO reviewDAO = new ReviewDAO();
    private SchoolDAO schoolDAO = new SchoolDAO();
    private BeoordelingDAO beoordelingDAO = new BeoordelingDAO();
    private static School school;

    /**
     * Constructor van ReviewView
     */
    public ReviewView() {

        //header
        VBox vboxHeader = header.geefHeader();

        //rapport layout
        this.vboxrapport1 = new VBox();
        this.vboxrapport2 = new VBox();

        this.hboxrapport = new HBox();

        //rapport nodes
        this.lblSchoolnaam = new Label("Beoordeel '" + this.school.getSchoolnaam() + "'");
        lblSchoolnaam.setStyle("-fx-font-size: 25px;");

        HBox hbSchoolnaam = new HBox();
        hbSchoolnaam.setAlignment(Pos.CENTER);
        hbSchoolnaam.setId("lblSchoolnaam");
        hbSchoolnaam.getChildren().add(lblSchoolnaam);

        this.lblLeraren = new Label("Leraren");
        this.lblLesprogramma = new Label("Lesprogramma");
        this.lblOrganisatie = new Label("Organisatie");
        this.lblProblemen = new Label("Aanpak van problemen");
        this.lblContact = new Label("Contact met school");
        this.lblVeiligheid = new Label("Veiligheid");

        this.ratingLeraren = new Rating(5, 0);
        ratingLeraren.setPartialRating(true);
        this.ratingLesprogramma = new Rating(5, 0);
        ratingLesprogramma.setPartialRating(true);
        this.ratingOrganisatie = new Rating(5, 0);
        ratingOrganisatie.setPartialRating(true);
        this.ratingProblemen = new Rating(5, 0);
        ratingProblemen.setPartialRating(true);
        this.ratingContact = new Rating(5, 0);
        ratingContact.setPartialRating(true);
        this.ratingVeiligheid = new Rating(5, 0);
        ratingVeiligheid.setPartialRating(true);

        //vul vboxrapport1 en 2
        vboxrapport1.getChildren().addAll(lblLeraren, ratingLeraren, lblOrganisatie, ratingOrganisatie, lblContact, ratingContact);
        vboxrapport2.getChildren().addAll(lblLesprogramma, ratingLesprogramma, lblProblemen, ratingProblemen, lblVeiligheid, ratingVeiligheid);
        vboxrapport1.setSpacing(10);

        vboxrapport2.setSpacing(10);

        //vul hboxrapport
        hboxrapport.getChildren().addAll(vboxrapport1, vboxrapport2);
        hboxrapport.setAlignment(Pos.CENTER);
        hboxrapport.setSpacing(80);
        hboxrapport.setPadding(new Insets(10, 0, 30, 0));

        //review nodes
        this.lblOpmerkingen = new Label("Opmerking: ");
        this.lblTotaal = new Label("Totaalscore: ");
        this.lblCombobox = new Label("Bent u: ");

        this.ratingTotaal = new Rating(5, 0);
        ratingTotaal.setPartialRating(true);

        this.taOpmerking = new TextArea();
        taOpmerking.setPromptText("max 500 karakters");
        taOpmerking.setWrapText(true);

        //vul combobox met typen uit database
        this.cmbBeoordelaar = new ComboBox();
        cmbBeoordelaar.setPromptText("Maak een keuze");
        ArrayList<String> bdltypen = beoordelingstypeDAO.geefBeoordelingstypen();
        for (int i = 0; i < bdltypen.size(); i++) {

            cmbBeoordelaar.getItems().add(bdltypen.get(i));

        }

        //ga terug naar homepagina bij annuleren
        this.btnAnnuleren = new Button("Annuleren");
        btnAnnuleren.setId("btnReview");
        btnAnnuleren.setOnAction(e -> {

            btnAnnuleren.getScene().setRoot(new HomePaginaView());

        });

        //sla review, rapport en school op in database 
        this.btnOpslaan = new Button("Opslaan");
        btnOpslaan.setId("btnReview");

        ArrayList<Rating> ratingLijst = new ArrayList<>();
        ratingLijst.add(ratingLeraren);
        ratingLijst.add(ratingLesprogramma);
        ratingLijst.add(ratingOrganisatie);
        ratingLijst.add(ratingContact);
        ratingLijst.add(ratingProblemen);
        ratingLijst.add(ratingVeiligheid);
        ratingLijst.add(ratingTotaal);

        btnOpslaan.setOnAction(e -> {

            int teller = 0;

            if (taOpmerking.getText().equals("")) {
                teller++;

            } else if (cmbBeoordelaar.getValue() == null) {
                teller++;
            }
            for (Rating rt : ratingLijst) {
                if (rt.getRating() == 0) {
                    teller++;
                }
            }
            if (teller > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Helaas een fout!");
                alert.setContentText("U heeft nog niet alle velden ingevuld!");
                alert.showAndWait();
            } else if (taOpmerking.getText().length() > 500) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Helaas een fout!");
                alert.setContentText("Tekst in opmerking mag niet groter zijn dan 500 karakers!");
                alert.showAndWait();

            } else {

                //school opslaan in database als deze nog niet bestaat in schooltabel
                ArrayList<String> scholenOpgeslagen = schoolDAO.geefScholenOpgeslagen();
                if (!scholenOpgeslagen.contains(this.school.getSchoolnaam())) {
                    schoolDAO.voegSchoolToe(school);
                }

                //review opslaan in database
                Review review = new Review(new Date(), taOpmerking.getText(), ratingTotaal.getRating(), this.school.getSchoolnaam(), (String) cmbBeoordelaar.getValue());
                reviewDAO.voegReviewToe(review);

                //rapport opslaan
                //maak lijst met rapport objecten om scores op te slaan
                ArrayList<Beoordeling> rapportscores = new ArrayList<>();

                Beoordeling rpLeraren = new Beoordeling(lblLeraren.getText(), ratingLeraren.getRating(), this.school.getSchoolnaam());
                Beoordeling rpLesprogramma = new Beoordeling(lblLesprogramma.getText(), ratingLesprogramma.getRating(), this.school.getSchoolnaam());
                Beoordeling rpOrganisatie = new Beoordeling(lblOrganisatie.getText(), ratingOrganisatie.getRating(), this.school.getSchoolnaam());
                Beoordeling rpProblemen = new Beoordeling(lblProblemen.getText(), ratingProblemen.getRating(), this.school.getSchoolnaam());
                Beoordeling rpContact = new Beoordeling(lblContact.getText(), ratingContact.getRating(), this.school.getSchoolnaam());
                Beoordeling rpVeiligheid = new Beoordeling(lblVeiligheid.getText(), ratingVeiligheid.getRating(), this.school.getSchoolnaam());

                rapportscores.add(rpLeraren);
                rapportscores.add(rpLesprogramma);
                rapportscores.add(rpOrganisatie);
                rapportscores.add(rpProblemen);
                rapportscores.add(rpContact);
                rapportscores.add(rpVeiligheid);

                for (Beoordeling bdl : rapportscores) {

                    beoordelingDAO.voegBeoordelingToe(bdl);

                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("");
                alert.setContentText("Review is opgeslagen!");
                alert.showAndWait();
                btnOpslaan.getScene().setRoot(new HomePaginaView());
            }

        });

        this.hboxBeoordelaar = new HBox();
        hboxBeoordelaar.setPadding(new Insets(20, 0, 20, 0));
        hboxBeoordelaar.setSpacing(10);
        hboxBeoordelaar.setAlignment(Pos.CENTER_LEFT);
        hboxBeoordelaar.getChildren().addAll(lblCombobox, cmbBeoordelaar);

        this.hboxButtons = new HBox();
        hboxButtons.setAlignment(Pos.CENTER);
        Region region = new Region();
        hboxButtons.setPadding(new Insets(30, 0, 0, 0));
        hboxButtons.setHgrow(region, Priority.ALWAYS);
        hboxButtons.getChildren().addAll(btnAnnuleren, region, btnOpslaan);

        this.vboxReview = new VBox();
        vboxReview.setSpacing(15);
        vboxReview.getChildren().addAll(lblOpmerkingen, taOpmerking, lblTotaal, ratingTotaal, hboxBeoordelaar, hboxButtons);

        //scrollpane
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        //inhoud review
        GridPane inhoud = new GridPane();
        inhoud.setMinSize(800, 940);
        inhoud.setMaxSize(800, 940);
        inhoud.setId("GridpaneInhoud");

        inhoud.add(hboxrapport, 0, 1);
        inhoud.add(vboxReview, 0, 2);

        //Gridpane als achtergrond scrollpane
        GridPane gridpaneAchtergrond = new GridPane();
        gridpaneAchtergrond.setVgap(20);
        gridpaneAchtergrond.add(hbSchoolnaam, 0, 0);
        gridpaneAchtergrond.add(inhoud, 0, 1);
        gridpaneAchtergrond.setId("gridpane");

        scrollPane.setId("scrollpane");
        scrollPane.setContent(gridpaneAchtergrond);
        scrollPane.setPrefWidth(this.getWidth());
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        this.getChildren().addAll(vboxHeader, scrollPane);

        //stylesheet        
        this.getStylesheets().add("/util/ReviewViewCSS.css");
    }

    /**
     * Methode die de school die vanuit homepagina wordt gezocht om te reviewen
     * gelijkstelt aan een school object in ReviewView. Dit school object wordt
     * gebruikt om de schoolnaam op het scherm te zetten en de gegevens die bij
     * de school horen in de database op te slaan als de school een review
     * krijgt.
     *
     * @param schl School object uit de beschikbare scholen van data.overheid
     * dat gekozen is om te reviewen.
     *
     */
    public static void setSchool(School schl) {

        ReviewView.school = schl;

    }
}
