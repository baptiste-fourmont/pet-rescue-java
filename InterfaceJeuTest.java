import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class InterfaceJeuTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FruttySaver"); // On changera le nom plus tard ptdr
        BorderPane borderPaneRoot = new BorderPane();
        Scene scene = new Scene(borderPaneRoot,800,600);
        primaryStage.setScene(scene);
        HBox hbox1 = new HBox();
        Label labelNom = new Label("Coucou Baptiste:");
        hbox1.getChildren().addAll(labelNom);
        borderPaneRoot.setCenter(hbox1);
        primaryStage.show();
    }
}
