/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Datalayer.BeoordelingDAO;
import Datalayer.ReviewDAO;
import Datalayer.SchoolDAO;
import Datalayer.SchoolSoortReviewDAO;
import Model.Review;
import Model.SchoolSoortReview;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

/**
 * Het scherm waarin gemiddelde rapportscores en reviews bekeken kunnen worden
 * van een school.
 *
 * @author Inge
 * @version 1.0
 * @see ScholenOverzichtView
 */
public class SchoolDetailView extends BorderPane {

    private Header header = new Header();
    private static String schoolnaam;
    private ScrollPane scrollPane = new ScrollPane();
    private Label lblGemScore, lblSchoolnaam, lblSchoolsoort, lblWebsite, lblReviews, lblDatum,
            lblBeoordelaar, lblLeraren, lblLesprogramma, lblOrganisatie, lblProblemen, lblContact, lblVeiligheid;
    private Button btnWebsite, btnTerug;
    private Rating ratingLeraren, ratingLesprogramma, ratingOrganisatie, ratingProblemen, ratingContact,
            ratingVeiligheid, ratingBDScore;
    private VBox vboxrapport1;
    private VBox vboxrapport2;
    private HBox hboxrapport;
    private TextArea taReviewTekst;
    private SchoolSoortReviewDAO ssrdao = new SchoolSoortReviewDAO();
    private ReviewDAO reviewDAO = new ReviewDAO();
    private SchoolDAO schoolDAO = new SchoolDAO();
    private BeoordelingDAO beoordelingDAO = new BeoordelingDAO();

    /**
     * Constructor van SchoolDetailView
     */
    public SchoolDetailView() {

        //header
        VBox vboxHeader = header.geefHeader();

        //vbox schoolinfo nodes
        this.lblGemScore = new Label("gem score");
        this.lblSchoolnaam = new Label("schoolnaam");
        lblSchoolnaam.setStyle("-fx-font-size: 25px;");
        this.lblSchoolsoort = new Label("onderwijssoort");
        Rating ratingGemScore = new Rating(5);
        ratingGemScore.setPartialRating(true);
        ratingGemScore.setDisable(true);

        this.btnTerug = new Button("Terug");
        btnTerug.setId("btnTerug");
        btnTerug.setOnAction(e -> {

            btnTerug.getScene().setRoot(new ScholenOverzichtView());

        });

        DecimalFormat decf = new DecimalFormat("#.#");

        //geef labels waarde vanuit schoolinfo database
        SchoolSoortReview ssr = ssrdao.geefOverzichtPerSchool(schoolnaam);
        lblSchoolnaam.setText(ssr.getSchoolnaam());
        lblSchoolsoort.setText(ssr.getSchoolsoort());
        lblGemScore.setText(decf.format(ssr.getGemScore()));
        ratingGemScore.setRating(ssr.getGemScore());

        //HBox rating, score, btnFavorieten
        HBox hboxSchoolInfo = new HBox();
        Region region = new Region();
        hboxSchoolInfo.setHgrow(region, Priority.ALWAYS);

        hboxSchoolInfo.setSpacing(10);
        hboxSchoolInfo.setAlignment(Pos.CENTER_LEFT);
        hboxSchoolInfo.getChildren().addAll(ratingGemScore, lblGemScore, region, btnTerug);

        //vbox school
        VBox vboxSchoolInfo = new VBox();
        vboxSchoolInfo.setId("vboxSchoolInfo");
        vboxSchoolInfo.setSpacing(10);

        vboxSchoolInfo.setMinWidth(800);
        vboxSchoolInfo.getChildren().addAll(lblSchoolnaam, lblSchoolsoort, hboxSchoolInfo);

        //rapport
        //rapport layout
        this.vboxrapport1 = new VBox();
        this.vboxrapport2 = new VBox();

        this.hboxrapport = new HBox();

        //rapport nodes
        this.lblLeraren = new Label("Leraren");
        this.lblLesprogramma = new Label("Lesprogramma");
        this.lblOrganisatie = new Label("Organisatie");
        this.lblProblemen = new Label("Aanpak van problemen");
        this.lblContact = new Label("Contact met school");
        this.lblVeiligheid = new Label("Veiligheid");

        this.ratingLeraren = new Rating(5, 0);
        this.ratingLesprogramma = new Rating(5, 0);
        this.ratingOrganisatie = new Rating(5, 0);
        this.ratingProblemen = new Rating(5, 0);
        this.ratingContact = new Rating(5, 0);
        this.ratingVeiligheid = new Rating(5, 0);

        ArrayList<Rating> ratingLijst = new ArrayList<>();
        ratingLijst.add(ratingLeraren);
        ratingLijst.add(ratingLesprogramma);
        ratingLijst.add(ratingOrganisatie);
        ratingLijst.add(ratingContact);
        ratingLijst.add(ratingVeiligheid);
        ratingLijst.add(ratingProblemen);

        for (Rating rt : ratingLijst) {
            rt.setPartialRating(true);
            rt.setDisable(true);

        }

        //zet gemiddelde scores in ratings
        HashMap<String, Double> scores = beoordelingDAO.geefScores(schoolnaam);
        ratingLeraren.setRating(scores.get(lblLeraren.getText()));
        ratingLesprogramma.setRating(scores.get(lblLesprogramma.getText()));
        ratingOrganisatie.setRating(scores.get(lblOrganisatie.getText()));
        ratingContact.setRating(scores.get(lblContact.getText()));
        ratingVeiligheid.setRating(scores.get(lblVeiligheid.getText()));
        ratingProblemen.setRating(scores.get(lblProblemen.getText()));

        //vul vboxrapport1 en 2
        vboxrapport1.getChildren().addAll(lblLeraren, ratingLeraren, lblOrganisatie, ratingOrganisatie, lblContact, ratingContact);
        vboxrapport2.getChildren().addAll(lblLesprogramma, ratingLesprogramma, lblProblemen, ratingProblemen, lblVeiligheid, ratingVeiligheid);
        vboxrapport1.setSpacing(10);
        vboxrapport1.setPadding(new Insets(30));
        vboxrapport2.setSpacing(10);
        vboxrapport2.setPadding(new Insets(30));

        //vul hboxrapport
        hboxrapport.getChildren().addAll(vboxrapport1, vboxrapport2);
        hboxrapport.setAlignment(Pos.CENTER);
        hboxrapport.setId("vboxSchoolInfo");

        //website nodes
        this.lblWebsite = new Label("Website bezoeken");
        this.btnWebsite = new Button("Ga naar website");
        btnWebsite.setId("btnWebsite");
        VBox vboxWebsite = new VBox();
        vboxWebsite.getChildren().addAll(lblWebsite, btnWebsite);
        vboxWebsite.setAlignment(Pos.CENTER);
        vboxWebsite.setSpacing(10);
        vboxWebsite.setId("vboxSchoolInfo");

        btnWebsite.setOnAction(e -> {

            Desktop d = Desktop.getDesktop();
            try {
                d.browse(new URI(schoolDAO.geefWebsite(schoolnaam)));
            } catch (URISyntaxException ex) {
                Logger.getLogger(SchoolDetailView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SchoolDetailView.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        //reviews
        this.lblReviews = new Label("Reviews");

        //maak voor iedere review een vbox 
        ArrayList<Review> schoolReviews = reviewDAO.geefReviewsSchool(schoolnaam);
        VBox vbReview = new VBox();
        vbReview.setAlignment(Pos.CENTER);
        vbReview.setId("vboxSchoolInfo");
        vbReview.getChildren().add(lblReviews);

        for (Review rv : schoolReviews) {

            //reviews nodes
            this.lblDatum = new Label("datum");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            lblDatum.setText(df.format(rv.getRevDatum()));
            this.lblBeoordelaar = new Label("beoordelaar");
            lblBeoordelaar.setText(rv.getBeoordelaarstype() + "  -");
            this.ratingBDScore = new Rating(5);
            ratingBDScore.setPartialRating(true);
            ratingBDScore.setDisable(true);
            ratingBDScore.setRating(rv.getTotaalscore());
            this.taReviewTekst = new TextArea();
            taReviewTekst.setText(rv.getReviewTekst());
            taReviewTekst.setWrapText(true);
            taReviewTekst.setEditable(false);
            taReviewTekst.setMouseTransparent(true);
            Region region1 = new Region();

            HBox hboxReviewNodes = new HBox();
            hboxReviewNodes.setSpacing(10);
            hboxReviewNodes.setPadding(new Insets(15));
            hboxReviewNodes.setHgrow(region1, Priority.ALWAYS);
            hboxReviewNodes.getChildren().addAll(ratingBDScore, region1, lblBeoordelaar, lblDatum);

            VBox vbox = new VBox();
            vbox.getChildren().addAll(hboxReviewNodes, taReviewTekst);
            vbReview.getChildren().add(vbox);

        }

        //Gridpane inhoud
        GridPane gridPane = new GridPane();
        gridPane.add(vboxSchoolInfo, 0, 0);
        gridPane.add(hboxrapport, 0, 1);
        gridPane.add(vboxWebsite, 0, 2);
        gridPane.add(vbReview, 0, 3);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(20, 350, 20, 350));

        //scrollpane
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setContent(gridPane);

        setTop(vboxHeader);
        setCenter(scrollPane);

        //stylesheet        
        this.getStylesheets().add("/util/SchoolDetailViewCSS.css");

    }

    /**
     * Methode die de schoolnaam in deze klasse gelijkstelt aan de schoolnaam
     * waarop geklikt is in het scholenoverzicht. De schoolnaam wordt gebruikt
     * om reviews en scores behorend bij de school op te halen uit de database.
     *
     * @param schlnm schoolnaam waarop geklikt is in ScholenOverzichtView
     */
    public static void setSchoolnaam(String schlnm) {

        SchoolDetailView.schoolnaam = schlnm;

    }

}
