import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;


/*
* La classe GrilleTEdit permet de différencier une GrilleT et d'une GrilleTEdit qui est créé par l'éditeur de niveau dans la version terminale
 */
public class GrilleTEdit extends GrilleT implements Serializable {

    /* C’est une clé de hachage SHA qui identifie de manière unique votre Classe, c'est un debug pour vscode */
    private static final long serialVersionUID = 1L;

    public GrilleTEdit(int ligne, int colonne) {
        super(ligne,colonne); // On rappelle que GrilleTEdit hérite de Grille
        this.AllBlank(); // On set toutes les cases vides par défault
    }

    // Fonction permettant de remplir toute la GrilleEdit de Case vide
    public void AllBlank(){
        CaseGameT[][]tmp;
        for(int i=0;i<tabcase.length;i++){
            for(int j=0;j<tabcase[i].length;j++){
                tmp = tabcase;
                tabcase[i][j] = new CaseGameT(0,3,i,j);
                tabcase[i][j].setPresent(false); // Permet  de bien dire que la case est bien présente sinon les case vide vont être supprimable et les points vont être compter
                tabcase[i][j].setTab(tmp); // On set le tableau CaseGames à une CaseGame pour pouvoir avoir accès au double dableau de CaseGame plus facilement

            }
        }
    }

    /* Fonction permettant de créer des cases de Couleur dans l'éditeur de niveau */
    public void createCase(int x,int y) throws IOException {
        /* On demande à l'utilisateur la couleur qu'il souhaite utiliser pour chaque case */
        System.out.println("Choisir une couleur:");
        System.out.println("[1] Rouge:");
        System.out.println("[2] Vert:");
        System.out.println("[3] Violet:");
        System.out.println("[4] Jaune:");
        System.out.println("[5] Quitter:");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        switch (option) {
            case "1" :
                tabcase[x][y].setCase(x,y,1);
                break;
            case "2" :
                tabcase[x][y].setCase(x,y,2);
                break;
            case "3" :
                tabcase[x][y].setCase(x,y,3);
                break;
            case "4" :
                tabcase[x][y].setCase(x,y,4);
                break;
            case "5" :
                Main.principale(); // On retourne au menu
                break;
        }
    }



}
