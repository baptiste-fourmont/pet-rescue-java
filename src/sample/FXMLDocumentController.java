package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

    @FXML
    private void loadScene(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("dan.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
