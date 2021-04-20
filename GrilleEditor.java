import java.awt.*;
import javax.swing.*;

public class GrilleEditor extends JFrame{ //Pour l'éditeur de niveau on utilise une grille différente
    protected CaseEditor[][] tab = new CaseEditor[10][10]; // on utilise donc pas des cases games mais des CaseEditor pour cette grille cette fois ci
    public GrilleEditor() {
        this.setBackground(Color.ORANGE);
        JPanel gridLay = new JPanel();
        gridLay.setLayout(new GridLayout(10, 10));
        fillpanel();
    }
    public void fillpanel(){
        for(int i = 0;i<10;i++){
            for(int j = 0;j<10;j++){
                this.tab[i][j] = randompanel();
            }
        }
        for(int i =0;i<10;i++){
            for(int j =0;j<10;j++){
                tab[i][j].setTAB(tab);
                this.add(tab[i][j]);
            }
        }
    }
    public CaseEditor randompanel(){
        CaseEditor paneli = new CaseEditor(0,0); // On remplit le Jpanel de cases vides pour les éditer plus tard
        return paneli;
    }

}