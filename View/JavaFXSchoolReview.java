/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Opstartklasse van SchoolReview waarin Stage geinstantieerd wordt.
 * @author Inge
 * @version 1.0
 */
public class JavaFXSchoolReview extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        Pane root = new HomePaginaView();
        FadeTransition ft = new FadeTransition(Duration.millis(2000), root);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        Scene scene = new Scene(root, 1500, 900);

        primaryStage.setTitle("SchoolReview");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
