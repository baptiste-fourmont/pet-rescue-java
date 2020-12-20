package sample;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.awt.*;
import java.util.Random;


public class GridPaneTrackingController {

    @FXML
    private GridPane grid ;
    private Pane WhitePane = new Pane();
    private int Colonnes = 10 ;
    private int Rows = 10;

    public void initialize() {

        WhitePane.setStyle("-fx-background-color: RED;");


        for (int i = 0; i < Colonnes; i++) {
            for (int j = 0; j < Rows; j++) {
                CreatePane(i, j);
            }
        }
        //grid.getChildren().remove(6);
        //grid.add(WhitePane,0,6);


    }

    private void CreatePane(int colIndex, int rowIndex) {
        Random r = new Random();
        int k = r.nextInt(3-1)+1;
        CaseGame pane = new CaseGame();
        String nom = "img/Test"+k+".png";
        pane.setVal(k);
        int lastVAL = grid.getChildren().size();
        BackgroundImage myBI= new BackgroundImage(new Image(nom,55,60,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        BackgroundImage myBIS= new BackgroundImage(new Image("img/TestH.png",55,60,true,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));

        pane.setOnMouseEntered(e -> {
            if(pane.getBackground().equals(new Background(myBI))){
                pane.setBackground(new Background(myBIS));
            }
        });
        pane.setOnMouseExited( e ->{
           if(pane.getBackground().equals(new Background(myBIS))){
               pane.setBackground(new Background(myBI));
           }
        });
        pane.setOnMouseClicked(e -> {
            paneDelete(lastVAL);
            pane.setVal(0);
            //paneActu();
        });
        grid.add(pane, colIndex, rowIndex);

    }



    private void paneActu(){

        int val = 89;
        String nom = "img/Test1.png";

        BackgroundImage myBI= new BackgroundImage(new Image(nom,55,60,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        while (val>0){
            if(val%9==0){
                val--;
            }
            else {
                CaseGame paneACTU = (CaseGame) grid.getChildren().get(val);
                CaseGame pane = (CaseGame) grid.getChildren().get(val + 1);
                if (pane.getBlank()) {
                    ((CaseGame) grid.getChildren().get(val + 1)).setBackground(new Background(myBI));
                    ((CaseGame) grid.getChildren().get(val)).ClearPane();
                }
                val--;
            }
        }
    }

    public void paneCheck(int k,int l){
        if(((CaseGame) grid.getChildren().get(k)).getVal() == ((CaseGame) grid.getChildren().get(l)).getVal()) {
            paneDelete(l);
            ((CaseGame) grid.getChildren().get(k)).ClearPane();
        }
    }
    public void paneDelete(int k){
        System.out.println(k);
        System.out.println(Rows*Colonnes);
        //((CaseGame) grid.getChildren().get(k)).setStyle("-fx-background-color: BLACK;");
        if(k==1){ // CASE EN HAUT A GAUCHE
            paneCheck(k,k+1);
            paneCheck(k,k+10);
            ((CaseGame) grid.getChildren().get(k+10)).ClearPane();
        }
        else if (k == 1+((Colonnes-1)*Rows)){ // CASE EN HAUT A DROITE
            paneCheck(k,k+1);
            paneCheck(k,k-10);
        }
        else if(k==(Rows*Colonnes)){ // CASE EN BAS A DROITE
            paneCheck(k,k-1);
            paneCheck(k,k-10);
        }
        else if(k == Rows){ // CASE EN BAS A GAUCHE
            paneCheck(k,k-1);
            paneCheck(k,k+10);
        }
        else if(k<Rows && k>0){ // CASES TOUT A GAUCHE
            if(((CaseGame) grid.getChildren().get(k)).getVal() == ((CaseGame) grid.getChildren().get(k+1)).getVal()) {
                paneDelete(k+1);
                ((CaseGame) grid.getChildren().get(k)).ClearPane();
                ((CaseGame) grid.getChildren().get(k+1)).ClearPane();
            }
        }
        else if(k%(Rows)==1 && k>Rows){

        }
        else if(k>Rows && k%Rows==0){

        }

        /**if(((CaseGame) grid.getChildren().get(k)).getVal() == ((CaseGame) grid.getChildren().get(k-1)).getVal()){
            ((CaseGame) grid.getChildren().get(k)).ClearPane();
            ((CaseGame) grid.getChildren().get(k-1)).ClearPane();
        }
        if(((CaseGame) grid.getChildren().get(k)).getVal() == ((CaseGame) grid.getChildren().get(k+10)).getVal()){
            ((CaseGame) grid.getChildren().get(k)).ClearPane();
            ((CaseGame) grid.getChildren().get(k+10)).ClearPane();
        }
        if(((CaseGame) grid.getChildren().get(k)).getVal() == ((CaseGame) grid.getChildren().get(k-10)).getVal()){
            ((CaseGame) grid.getChildren().get(k)).ClearPane();
            ((CaseGame) grid.getChildren().get(k-10)).ClearPane();
        }
        if(((CaseGame) grid.getChildren().get(k)).getVal() == ((CaseGame) grid.getChildren().get(k+1)).getVal()){
            ((CaseGame) grid.getChildren().get(k)).ClearPane();
            ((CaseGame) grid.getChildren().get(k+1)).ClearPane();
        }**/

    }

    /**grid.getChildren().get(colIndex).setStyle("-fx-background-color: WHITE;");
     grid.getChildren().get(colIndex+1).setStyle("-fx-background-color: WHITE;");
     grid.getChildren().get(colIndex-1).setStyle("-fx-background-color: WHITE;");
     grid.getChildren().get(colIndex+10).setStyle("-fx-background-color: WHITE;");
     grid.getChildren().get(colIndex-10).setStyle("-fx-background-color: WHITE;");**/

    /**for(int i = 0 ;i<90;i = i+10){
     for( int j = 0;j <9;j++){
     if(grid.getChildren().get(10+i).equals(WhitePane)){
     System.out.println("HEhe");
     grid.add(grid.getChildren().get(i+j),i/10,j+1);
     }
     }
     }**/



}
