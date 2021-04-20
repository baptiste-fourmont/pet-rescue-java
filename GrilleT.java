//package com.petrescuesaga.terminal;



import java.util.Random;
import java.io.Serializable;

public class GrilleT implements Serializable {
    /**
     *   La classe Grillet correspond à la grille de la version terminal
     *   Elle permet de contenir les CaseGames qui correspondent aux cases jouables
     *   Une grillet correspond à une grille d'un niveau
     */
    private static final long serialVersionUID = 1L;
    private int ligne;  // Attribut correspondant au ligne
    private int colonne; // Attribut correspondant au colonne
    protected CaseGameT[][] tabcase; // Attribut correspond à un double tableau de CaseGame qui permet de contenir les CaseGames


    // Constructeur permettant d'initialiser une Grille Random avec une ligne et une colonne customisable
    public GrilleT(int ligne, int colonne){
        this.tabcase = new CaseGameT[ligne][colonne];
        this.setRandomColor();  // Fonction générant des cases avec des couleurs différentes
        this.ligne = ligne;
        this.colonne = colonne;
    }

    // Constructeur initialisant une Grille uniquement à partir d'un tableau
    public GrilleT(CaseGameT[][] tabCase){
        this.tabcase = tabCase;
    }

    // Constructeur initialisant une Grille avec les lignes, colonnes et un double tableau de CaseGame
    public GrilleT(int ligne, int colonne, CaseGameT[][] tabCase) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.tabcase = tabCase;
    }


    // Cette fonction permet d'initialiser des Case de Couleurs différentes avec deux animaux en haut à Gauche en 0|0 et 0|1
    public void setRandomColor(){
        int min = 1;
        int max = 2; // 4 au max ce qui correspond à 5 au maximum
        Random random = new Random(); // On génère random pour pouvoir générer des couleurs différentes

        CaseGameT[][]tmp;
        // On s'apprête à remplir le double Tableau de CaseGame de Case de couleur
        for(int i=0;i<tabcase.length;i++){
            for(int j=0;j<tabcase[i].length;j++){
                tmp = tabcase;
                int color = random.nextInt(max + min) + min; // On réactualise ici à chaque fois le random pour qu'il est une couleur différente
                tabcase[i][j] = new CaseGameT(color,0, i,j); // Le type 0 correspond à des cases cassables de couleur
                tabcase[i][j].setTab(tmp); // On set le tableau CaseGames à une CaseGame pour pouvoir avoir accès au double dableau de CaseGame plus facilement
            }
        }
        tmp = tabcase;  // On rappelle le double tableau vide;
        tabcase[0][0].setAnimal(0,0);
        tabcase[0][0].setAnimal(0,1);
        // On ajoute deux animaux en haut à Gauche en x=0 && y= 0 ainsi que x=0 et y=1 pour retrouver les mécaniques prinicpale de jeux principales
        tabcase[0][0].setTab(tmp);  // On set le tableau CaseGames à une CaseGame pour pouvoir avoir accès au double dableau de CaseGame plus facilement
    }


    /* La fonction Check Animal permet de supprimer les animaux quand ils sont sur la dernière ligne 9 et ainsi pouvoir donné la récompense de 1000points par animal en faisant le décalage à gauche et actualsier la gravité
     * Pour ca on vérifie là 9ème ligne de toutes les colonnes en vérifiant si c'est un animal pour pouvoir le supprimer
    */
    public void checkAnimal(){
        for(int i =0;i<10;i++){
            if(tabcase[9][i].getValue() == -1){ // -1 correspond à un animal et comme aucune case ne peut avoir la valeur -1 sauf un animal on check la valeur -1 tout simplement de toute les colonnes
                tabcase[9][i].deleteCase(9,i); // On délete l'animal sur la dernière ligne et i correspond à la colonne
                tabcase[9][i].updatetabH(); // Permet de faire le décalage à gauche
                tabcase[9][i].updatetabV(); // Permet de faire la gravité
                checkAnimal(); // On réactualise récursivement si il y a un autre animal à détecter
            }
        }
    }


    /* Cette fonction permet d'afficher la ligne horizontale avec les valeurs 0 - 10 par  défault */
    public void afficheNiveau(){
        System.out.print("   ");    // Permet de décaler à gauche pour pas que le 0 se retrouve au dessus de la colonne avec les valeurs sur le côté
        for(int i=0;i< tabcase.length;i++){
            System.out.print(" "+i+" ");    // On ajoute les valeurs
        }
        System.out.println();
    }

    /* Cette fonction permet d'ajouter la ligne et la colonne qui permet d'améliorer l'expérience utilisateur en aidaint le joueur à voir plus facilement les coordonnées */
    public void afficheTAB(){
        afficheNiveau(); // On affiche la ligne au dessus
        for(int i=0;i< tabcase.length;i++){
            for(int j=0;j<tabcase[i].length;j++){
                if(j==0){
                    System.out.print(" "+i+" "); // Ici on affiche chaque y un par un
                }
                System.out.print(tabcase[i][j]); // On affiche tabacase[i][j].toString() par défault ce qui permet de lire les informations de la CaseGame
            }
            System.out.println();
        }
        System.out.println();
    }


    /* Getter et Setter */
    public CaseGameT[][] getTabcase() {
        return tabcase;
    }

    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setTabcase(CaseGameT[][] tabcase) {
        this.tabcase = tabcase;
    }
}
