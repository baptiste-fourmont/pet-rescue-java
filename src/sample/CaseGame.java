package sample;

import javafx.scene.layout.Pane;

public class CaseGame extends Pane {
    private boolean Blank; // Pour savoir si la case est vide ou non
    private int val;

    public void ClearPane(){
        this.Blank = true;
        this.setStyle("-fx-background-color: WHITE;");
        this.val = 0;
    }
    public boolean getBlank(){
        return this.Blank;
    }
    public void setVal(int k){
        this.val = k;
    }
    public int getVal(){
        return this.val;
    }


    public void UpdatePane(CaseGame C){
        this.Blank = false;
        C.ClearPane();
    }
}
