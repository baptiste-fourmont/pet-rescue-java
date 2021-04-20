import java.io.Serializable;

// Classe CaseGameT correspond à une Case dans le tableau
public class CaseGameT implements Serializable,CaseMinimumSettings{
    /*
    Serizable permet de sérialiser CaseGameT
    CaseMinimumSettings contient les méthodes communes aux deux programmes
     */

    /* C’est une clé de hachage SHA qui identifie de manière unique votre Classe, c'est un debug pour vscode */
    private static final long serialVersionUID = 1L;
    private int value;
    private int type;
    private int x;
    private int y;
    private static int vx =0;
    private static int vy =0;
    private CaseGameT[][] tab;
    private static int Animal = 0;
    private static int PointGame = 0; // Point de la partie en cours
    private static int Point = 0; // Nombre de case Supprimé
    private boolean present;

    public CaseGameT(int value, int type, int x, int y){
        this.value = value;
        this.type = type;
        this.x = x;
        this.y = y;
        setXandY();
        this.present = true;
    }


    public void setXandY(){
        if(vy<9){
            vy++;
        }
        else if(vy==9){
            vx++;
            vy=0;
        }
        if(vx > 9){
            vx=0;
        }

        if(type == 1 ){
            Animal++;
        }
        if(type ==2){
            this.present = false;
        }
    }

    public void deleteCase(int cx, int cy){
        if(tab[cx][cy].type == 0  ) { // On vérifie si la case supprimé correspond à une case de couleur
            Point++; // On ajoute un si c'est le cas
        }
        if(tab[x][y].getValue() == -1 && tab[x][y].present){ // On regarde si la case est un animal
            PointGame = PointGame + 1000; // On ajoute 1000points si c'est le cas
        }

        // On difinit la case comme vide
        tab[cx][cy].present = false;
        tab[cx][cy].setValue(0);
        tab[cx][cy].type = 3;
    }

    // Voir CaseGame
    public boolean checkCase(int cx, int cy,int k){
        int z = 0;
        if(cx == 0 && tab[cx+1][cy].present && tab[cx+1][cy].getValue() == (tab[cx][cy].getValue()) && tab[cx][cy].type !=1 && tab[cx][cy].type !=2){
            z++;
            tab[cx][cy].present = false;
            checkCase(cx+1,cy,1);
        }

        if(cx !=0 && tab[cx-1][cy].present && tab[cx-1][cy].getValue() == (tab[cx][cy].getValue())&& tab[cx][cy].type !=1 && tab[cx][cy].type !=2){
            z++;
            tab[cx][cy].present = false;
            checkCase(cx-1,cy,1);
        }
        if(cx !=9 && tab[cx+1][cy].present && tab[cx+1][cy].getValue() == (tab[cx][cy].getValue())&& tab[cx][cy].type !=1 && tab[cx][cy].type !=2){
            z++;
            tab[cx][cy].present = false;
            checkCase(cx+1,cy,1);
        }
        if(cy != 9 && tab[cx][cy+1].present && tab[cx][cy+1].getValue() == (tab[cx][cy].getValue())&& tab[cx][cy].type !=1 && tab[cx][cy].type !=2){
            z++;
            tab[cx][cy].present = false;
            checkCase(cx,cy+1,1);

        }
        if(cy ==0 && tab[cx][cy+1].present && tab[cx][cy+1].getValue() == (tab[cx][cy].getValue())&& tab[cx][cy].type !=1 && tab[cx][cy].type !=2){
            z++;
            tab[cx][cy].present = false;
            checkCase(cx,cy+1,1);
        }

        if(cy !=0 && tab[cx][cy-1].present && tab[cx][cy-1].getValue() == (tab[cx][cy].getValue())&& tab[cx][cy].type !=1 && tab[cx][cy].type !=2){
            z++;
            tab[cx][cy].present = false;
            checkCase(cx,cy-1,1);
        }

        if(k ==1){
            deleteCase(cx,cy);
        }
        if(z >0 & k==0){
            deleteCase(cx,cy);
	    return true;
        }
	return false;
    }

    


    public boolean checkValue(CaseGameT a, CaseGameT b){
        if(a.getType() != 0 || b.getType() != 0 ){ 
            return  false;
        }
        return a.getValue() == b.getValue();
    }

    
    public boolean isLost(){
        if(countAnimal()>0){
            for(int i = 0;i<10;i++){
                for(int j = 0;j<10;j++){
                    if(i!=9){
                        if(tab[i+1][j].getValue()==(tab[i][j].getValue()) && tab[i+1][j].present ){
                            return false;
                        }
                    }
                    if(i!=0){
                        if(tab[i-1][j].getValue()==(tab[i][j].getValue()) && tab[i-1][j].present){
                            return false;
                        }
                    }
                    if(j!=9){
                        if(tab[i][j+1].getValue()==(tab[i][j].getValue())&& tab[i][j+1].present){
                            return false;
                        }
                    }
                    if(j!=0){
                        if(tab[i][j-1].getValue()==(tab[i][j].getValue())&& tab[i][j-1].present){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // ReDéfintion de tostring() pour CaseGameT
    public String toString(){
        if(tab[x][y].getValue() == -1 && tab[x][y].present){ // Correspond à un animal
            return " A ";
        }else{
            if(tab[x][y].getValue() == 0){ // On check si la case est de couleur et pas présente
                return "   ";
            }else{
                return " "+value+" "; //On regarde la valeur de la case
            }
        }
    }



    // Fonction permettant de compter le nombre d'animal encore en vie dans une Grille
    int countAnimal(){
        int animal = 0;
        for(int i =0;i<10;i++) {
            for(int j =0;j<10;j++) {
                if(tab[i][j].getValue() == -1){ // On vérifie si c'est un animal
                    animal++;
                }
            }
        }
        return  animal;
    }

    public void checkAnimals() {
        for(int i =0;i<10;i++){
            if(tab[9][i].getValue() == -1){
                deleteCase(9,i);
                CaseGameT.Animal--;
                updatetabH();
                updatetabV();
                checkAnimals();

            }
        }
    }


    public void updatetabV() {
            int k;
            int w = 0;
            while (w==0){
                k = 0;
                for(int i = 9;i>0;i--){
                    for(int j =9;j>=0;j--){
                        if(!tab[i][j].present && tab[i-1][j].present && tab[i][j].type != 2 && tab[i-1][j].type != 2){
                            Swap2Cases(i,j,i-1,j);
                            k++;
                        }
                    }
                }
                if(k==0){
                    w=1;
                }
            }
    }

    public void Swap2Cases(int x1, int y1, int x2, int y2) {
        //CaseGame tmp = tab[x1][y1];
        tab[x1][y1].setValue(tab[x2][y2].getValue());
        tab[x2][y2].setValue(0);
        tab[x2][y2].setPresent(false);
        tab[x2][y2].present = false;
        tab[x1][y1].setPresent(true);
        tab[x1][y1].present = true;
    }

    public void updatetabH() {
        for(int i = 0;i<9;i++){
            int k = 0;
            for(int j = 0 ; j<10; j++){
                if(tab[j][i].present){
                    k++;
                }
            }
            if(k==0){
                SwapCases(i);
                updatetabH();
            }
        }
    }

    public void SwapCases(int w) {
        for(int i = 0;i<10;i++){
            if(tab[i][w].type != 2 && tab[i][w+1].type !=2){
                tab[i][w].setValue(tab[i][w+1].getValue());
                tab[i][w].type = tab[i][w+1].type;
                deleteCase(i,w+1);
                tab[i][w].present = true;
            }
        }
    }

    public int getPointGame(){
        return PointGame;
    }

    public void setPointGame(int PointGames){
        PointGame = PointGames;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int point) {
        Point = point;
    }

    public void calculatePoint(){
        int tmp = Point;
        if(Point >= 2){
            PointGame = PointGame + tmp*tmp*10;
            this.setPoint(0);
        }
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public int getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CaseGameT[][] getTab() {
        return tab;
    }

    public static int getAnimal() {
        return Animal;
    }

    public void setType(int type) {
        this.type = type;
    }

    private void setVide(int x, int y) {
        tab[x][y].setType(3);
        tab[x][y].setPresent(false);
        tab[x][y].present = false;
    }

    protected void setCase(int x, int y, int val) {
        tab[x][y].setValue(val);
        tab[x][y].setPresent(true);
        tab[x][y].present = true;
    }

    private void setObstacle(int x, int y) {
        tab[x][y].setType(2);
        tab[x][y].setPresent(true);
        tab[x][y].present = true;
    }
    protected void setAnimal(int x, int y){
        tab[x][y].setType(1);
        tab[x][y].setValue(-1);
        tab[x][y].setPresent(true);
        tab[x][y].present = true;
        Animal++;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public void setTab(CaseGameT[][] tabe) { tab = tabe; }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public static void setAnimal(int animal) {
        Animal = animal;
    }
}
