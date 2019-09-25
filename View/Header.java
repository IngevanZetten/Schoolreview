/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 * Klasse die de layout van de header instantieert. Dit zorgt ervoor dat je de header
 * niet in ieder scherm opnieuw hoeft te maken.
 * @author Inge
 * @version 1.0
 */
public class Header {

    private ImageView ivLogo;
    private VBox vbox;

    /**
     * Methode die de layout van de header maakt. 
     * @return Vbox Vbox waarin layout nodes van de header zitten.
     */
    public VBox geefHeader() {

        //image       
        this.ivLogo = new ImageView();

        Image image = new Image("/util/logo.png");
        ivLogo.setImage(image);
        ivLogo.setFitHeight(60);
        ivLogo.setFitWidth(120);
        ivLogo.setOnMouseClicked(e -> {

            ivLogo.getScene().setRoot(new HomePaginaView());

        });

        ivLogo.setStyle("-fx-cursor: hand");
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5));
        hbox.getChildren().addAll(ivLogo);
        hbox.setAlignment(Pos.BOTTOM_LEFT);

        hbox.setBackground(new Background(new BackgroundFill(Paint.valueOf("Rgb(0,151,211)"), CornerRadii.EMPTY, Insets.EMPTY)));

        HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(15));
        hbox2.setBackground(new Background(new BackgroundFill(Paint.valueOf("Rgb(0,64,100)"), CornerRadii.EMPTY, Insets.EMPTY)));

        this.vbox = new VBox();
        vbox.getChildren().addAll(hbox, hbox2);

        return vbox;
    }

}
