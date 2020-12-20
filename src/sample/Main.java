package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {
    public class ScreenController {
        private HashMap<String, Pane> screenMap = new HashMap<>();
        private Scene main;

        public ScreenController(Scene main) {
            this.main = main;
        }

        protected void addScreen(String name, Pane pane){
            screenMap.put(name, pane);
        }

        protected void removeScreen(String name){
            screenMap.remove(name);
        }

        protected void activate(String name){
            main.setRoot( screenMap.get(name) );
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        changeScene("Dan.fxml",primaryStage);
        GridPane root2 = FXMLLoader.load(getClass().getResource("Main2.fxml"));
        Scene Grille2 = new Scene(root2,800,800);
        primaryStage.show();
        primaryStage.setTitle("Dany");
        int s = 525;
        if(s == 25){
            primaryStage.setScene(Grille2);
            primaryStage.setTitle("Main2");
        }

    }

    private void changeScene(String s, Stage primaryStage) throws IOException {
        Parent test = FXMLLoader.load(getClass().getResource(s));
        Scene Scene = new Scene(test,800,800);
        primaryStage.setScene(Scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}