/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Datalayer.SchoolSoortReviewDAO;
import Model.SchoolSoortReview;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

/**
 * Het scherm waarin een overzicht te zien is van scholen met een beoordeling.
 *
 * @author Inge
 * @version 1.0
 */
public class ScholenOverzichtView extends BorderPane {

    private Header header = new Header();
    private Label lblSchoolsoort, lblGemScore, lblCheckSoort, lblCheckScore;
    private Hyperlink hlSchoolnaam;
    private Rating ratingGemScore;
    private Button btnFavorieten;
    private static MenuButton btnFav;
    private ScrollPane scrollPane = new ScrollPane();
    private SchoolSoortReviewDAO soortReviewDAO = new SchoolSoortReviewDAO();
    private ArrayList<String> scholenFavoriet = new ArrayList<>();
    private VBox vbScholenOverzicht;

    /**
     * Constructor van ScholenOverzichtView
     */
    public ScholenOverzichtView() {

        VBox vboxHeader = header.geefHeader();
        HBox hboxFav = new HBox();
        btnFav = new MenuButton();
        btnFav.setId("btnFav");

        hboxFav.setAlignment(Pos.CENTER_LEFT);
        hboxFav.getChildren().add(btnFav);
        vboxHeader.getChildren().add(hboxFav);
        hboxFav.setId("fav");

        //labels filters
        this.lblCheckSoort = new Label("Schoolsoort");
        lblCheckSoort.setId("lblFilter");
        this.lblCheckScore = new Label("Score");
        lblCheckScore.setId("lblFilter");

        //Aanmaken van groep voor radiobuttons soort school
        ToggleGroup groepSoort = new ToggleGroup();

        //radiobuttons groepSoort
        RadioButton rbBasis = new RadioButton("Basisonderwijs");
        RadioButton rbVoortgezet = new RadioButton("Voortgezet onderwijs");
        RadioButton rbSpeciaal = new RadioButton("Speciaal onderwijs");
        RadioButton rbPraktijk = new RadioButton("Praktijk onderwijs");

        rbBasis.setToggleGroup(groepSoort);
        rbVoortgezet.setToggleGroup(groepSoort);
        rbSpeciaal.setToggleGroup(groepSoort);
        rbPraktijk.setToggleGroup(groepSoort);

        //aanmaken van groep voor radiobuttons score
        ToggleGroup groepScore = new ToggleGroup();

        //radiobuttons groepScore
        RadioButton rbScore1 = new RadioButton(" 1+ ");
        RadioButton rbScore2 = new RadioButton(" 2+ ");
        RadioButton rbScore3 = new RadioButton(" 3+ ");
        RadioButton rbScore4 = new RadioButton(" 4+ ");

        rbScore1.setToggleGroup(groepScore);
        rbScore2.setToggleGroup(groepScore);
        rbScore3.setToggleGroup(groepScore);
        rbScore4.setToggleGroup(groepScore);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        Separator separator1 = new Separator();
        separator1.setOrientation(Orientation.HORIZONTAL);
        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.HORIZONTAL);

        //vbox filters
        VBox vboxFilters = new VBox();

        vboxFilters.setSpacing(15);

        vboxFilters.getChildren().addAll(separator2, lblCheckSoort, rbBasis, rbVoortgezet, rbPraktijk, rbSpeciaal, separator);
        vboxFilters.getChildren().addAll(lblCheckScore, rbScore1, rbScore2, rbScore3, rbScore4, separator1);
        vboxFilters.setId("vboxFilters");

        DecimalFormat df = new DecimalFormat("#.#");

        //maak voor iedere school met een review een vbox met info over de school
        ArrayList<SchoolSoortReview> opgeslagenScholen = soortReviewDAO.geefOverzichtScholen();
        ArrayList<VBox> vboxLijst = new ArrayList<>();
        for (SchoolSoortReview ssr : opgeslagenScholen) {

            //layout nodes center
            this.lblGemScore = new Label("gem score");
            this.hlSchoolnaam = new Hyperlink();
            hlSchoolnaam.setStyle("-fx-font-size: 25px; -fx-text-fill: rgb(0, 64, 100);");
            this.lblSchoolsoort = new Label("onderwijssoort");
            this.ratingGemScore = new Rating(5);
            ratingGemScore.setPartialRating(true);
            ratingGemScore.setDisable(true);
            this.btnFavorieten = new Button();
            btnFavorieten.setId("btnFavorieten");

            hlSchoolnaam.setText(ssr.getSchoolnaam());
            lblSchoolsoort.setText(ssr.getSchoolsoort());
            ratingGemScore.setRating(ssr.getGemScore());
            lblGemScore.setText(df.format(ssr.getGemScore()));

            btnFavorieten.setOnAction(e -> {

                voegFavorietToe(ssr.getSchoolnaam());

            });

            //ga naar detail info over school wanneer er wordt geklikt op schoolnaam
            hlSchoolnaam.setOnAction(e -> {

                SchoolDetailView.setSchoolnaam(ssr.getSchoolnaam());
                hlSchoolnaam.getScene().setRoot(new SchoolDetailView());

            });

            //HBox rating, score, btnFavorieten
            HBox hboxSchoolInfo = new HBox();
            Region region = new Region();
            hboxSchoolInfo.setHgrow(region, Priority.ALWAYS);
            hboxSchoolInfo.setSpacing(10);
            hboxSchoolInfo.setAlignment(Pos.CENTER_LEFT);
            hboxSchoolInfo.getChildren().addAll(ratingGemScore, lblGemScore, region, btnFavorieten);

            //vbox school
            VBox vboxSchoolInfo = new VBox();
            vboxSchoolInfo.setId("vboxSchoolInfo");
            vboxSchoolInfo.setSpacing(10);
            vboxSchoolInfo.setPadding(new Insets(20));
            vboxSchoolInfo.setMinWidth(800);
            vboxSchoolInfo.getChildren().addAll(hlSchoolnaam, lblSchoolsoort, hboxSchoolInfo);

            vboxLijst.add(vboxSchoolInfo);

        }

        //vbox scholen
        this.vbScholenOverzicht = new VBox();
        vbScholenOverzicht.setId("vbScholenOverzicht");
        vbScholenOverzicht.setMaxWidth(800);
        vbScholenOverzicht.setSpacing(20);

        for (VBox vb : vboxLijst) {
            vbScholenOverzicht.getChildren().add(vb);
        }

        scrollPane.setContent(vbScholenOverzicht);
        scrollPane.setMinWidth(800);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        setTop(vboxHeader);

        setLeft(vboxFilters);
        setMargin(vboxFilters, new Insets(20, 40, 20, 210));
        setCenter(scrollPane);
        setMargin(scrollPane, new Insets(20, 0, 20, 10));
        setAlignment(vbScholenOverzicht, Pos.TOP_LEFT);

        this.getStylesheets().add("/util/ScholenOverzichtCSS.css");

    }

    /**
     * Methode die een school toevoegt aan de lijst met favorieten nadat op de
     * knop met het hart icoon is gedrukt.
     *
     * @param schoolnaam De schoolnaam die toegevoegt moet worden aan
     * favorieten.
     *
     */
    public void voegFavorietToe(String schoolnaam) {

        
        if (scholenFavoriet.size() > 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Helaas een fout!");
            alert.setContentText("U heeft al 5 favorieten opgeslagen!\nVerwijder eerst een favoriet voordat u een nieuwe toe kan voegen.");
            alert.showAndWait();

        } else if (scholenFavoriet.contains(schoolnaam)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Helaas een fout!");
            alert.setContentText("U heeft deze school al opgeslagen!");
            alert.showAndWait();

        } else {
            scholenFavoriet.add(schoolnaam);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setContentText("School is toegevoegd aan favorieten!");
            alert.showAndWait();

        }
        for (String st : scholenFavoriet) {
            System.out.println(st);
        }

        btnFav.getItems().clear();

        //voeg scholen uit favorietenLijst toe aan menubutton
        for (String st : scholenFavoriet) {

            MenuItem mi = new MenuItem(st);

            System.out.println(mi.getText());
            btnFav.getItems().add(mi);

        }

    }

}
