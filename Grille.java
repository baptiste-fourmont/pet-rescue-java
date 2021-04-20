import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Grille extends JPanel { // La grille est un Jpanel avec un GridLayout qui nous servira à contenir les cases
    protected CaseGame[][] tab = new CaseGame[10][10]; // La grille va être remplit d'un tableau tab contenant des CaseGame

    private boolean isDone = false;
    public Grille() { // COmme aucun arg ds le constructeur alors la grille sera au hasard
        this.setLayout(new GridLayout(10, 10)); // On définit un gridLayout afin de mettre les caseGame dedans
        fillpanel(); // On remplit le panel avec cette fonction pour mettre des cases au hasard dans la grille
        this.setVisible(true); // On rend ce Jpanel visible
    }
    public Grille(CaseGame[][] ImportedTab){ // On va ici créer une grille avec un tableau importé
        this.setBackground(Color.ORANGE);
        this.setLayout(new GridLayout(10, 10));
        fillpanelTAB(ImportedTab); //On set la grille comme le tableau importé
        this.setVisible(true);
        tab[0][0].updatetabH(); // Apres avoir importé le tableau on actualise les différentes cases
        tab[0][0].updatetabV();
        tab[0][0].checkAnimals();
    }

    public void setTab(CaseGame[][] tableau){
        this.tab = tableau;
    }

    public void fillpanel(){//On remplit la grille de cases au hasard
        for(int i = 0;i<10;i++){
            for(int j = 0;j<10;j++){
                this.tab[i][j] = randompanel();
            }
        }
        for(int i =0;i<10;i++){
            for(int j =0;j<10;j++){
                tab[i][j].setTAB(tab); // On set le TAB de la caseGame dans toutes les cases possibles afin d'y acceder partout
                this.add(tab[i][j]);
                tab[i][j].setCaseInfos(tab[i][j].getType(),tab[i][j].getColor());
            }
        }
    }
public void fillpanelTAB(CaseGame[][]ImportedTab){ // Ici on remplit la grille avec les cases contenues dans le tableau importé dans le constructeur
        for(int i = 0;i<10;i++){
            for(int j = 0;j<10;j++){
                CaseGame paneli = null;
                paneli = new CaseGame(ImportedTab[i][j].getType(),ImportedTab[i][j].getColor());
                paneli.setBackground(Color.white);
                this.tab[i][j] = paneli;
            }
        }
        for(int i =0;i<10;i++){
            for(int j =0;j<10;j++){
                tab[i][j].setTAB(tab);
                this.add(tab[i][j]);
                tab[i][j].setCaseInfos(tab[i][j].getType(),tab[i][j].getColor());
            }
        }
    }

    public CaseGame randompanel(){ // On génére ici des cases au hasard pour remplir le panel
        Random random = new Random();
        int k = random.nextInt(5 - 1 + 1) + 1;
        CaseGame paneli = null;
        if(k>0 && k<5){
            paneli = new CaseGame(0,k);
            paneli.setBackground(Color.white);
        }
        if(k==5){
            paneli = new CaseGame(1,0);
            paneli.setBackground(Color.PINK);
        }
        if(k==6){
            paneli = new CaseGame(2,0);
            paneli.setBackground(Color.BLACK);
        }

        return paneli;
    }
    public void SetWin(){
        this.isDone = true;
    }
    public static Grille getGrille() {
        return new Grille();
    }
    public static Grille getGrilleSave(CaseGame[][] t){
        return  new Grille(t);
    }

}