import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class CaseGame extends JPanel implements ActionListener,CaseMinimumSettings{
    private static int x =0; // Static afin de pouvoir faire un tableau avec les coordonnées correctes pour chaque nouvelle case
    private static int y =0; // voir  setXandY() pour les conditions pour éviter les dépassements
    private boolean present = true; // Pour savoir si la case est vide ou non
    private final int valx;  // Afin de récupérer plus tard le x actuel pour le sauvegarder uniquement pour cette case
    private final int valy;  // Pareil pour y
    private int type; // Le Type de la case (0 pour case de couleur , 1 Animal, 2 Obstacle, 3 Case vide)
    private int color; // Couleur de la case voir setCase pour les values
    private CaseGame[][] tabcase; // Afin d'avoir acces aux autres cases du tableau depuis ici et donc pouvoir les modifier
    private static int animaltoSave; // Le nombre d'animaux à sauver static afin de s'additionner à chaque nouvel animal dans les cases
    private final JButton button = new JButton(); // Pour ajouter un bouton sur le panel pour détecter les clics de l'utilisateur
    private int combo;  // Le combo servira à compter combien de cases ont été touchées en un coup pour calculer le score
    public static int ScoreSessActual = 0; // Le score du niveau fait actuellement
    public static int scoreTotal = 0; // Le score de Session depuis que le jeu a été lancé

    public CaseGame(int type,int color){
        super(); // On récupère le constructeur d'un Jpanel
        this.type =type; // On set le Type
        this.color = color; // et la couleur
        this.valx = x; // puis la coord x de la case actu
        this.valy = y; // et y
        setXandY();
        Border blackline = BorderFactory.createLineBorder(Color.black); // On met des contours entre les cases du tableau
        this.setLayout(new BorderLayout(0, 0));
        button.setContentAreaFilled(false); // On cache les boutons par défaut
        button.setBorderPainted(false); //     qd la case est vide
        button.addActionListener(this); //   afin de voir le fond blanc
        this.add(button); // On ajoute un bouton dans le jpanel
        this.setBorder(blackline);
        GameBoxLevel.updateScore(); // permet d'appeler une fonction qui actualise le score en haut du jframe GameBoxLevel
    }

    public void setXandY(){
        if(y<9){ // Si y <9 alors on est pas à la fin d'une ligne et on peut encore l'incrémenter de 1
            y++;
        }
        else if(y==9){ // Par contre Si on arrive à la fin d'une ligne et que y = 9 alors on augmente d'une colonne (alors x++ et y revient à 0)
            x++;
            y=0;
        }
        if(x > 9){ // Si x > 9 cela veut donc dire qu'on est sur une nouvelle grille alors on remet x à 0
            x=0;
        }
        if(valx ==0 && valy == 0 || animaltoSave <0){
            animaltoSave = 0;
        }
        if(type ==2){
            this.present = false;
        }
        if(type == 1){
            animaltoSave++;
            GameBoxLevel.petsLabel.setText(String.valueOf(animaltoSave));
        }
    }
    public int getType(){
        return this.type;
    }
    public int getValue(){
        return this.color;
    }

    public int getColor(){
        return this.color;
    }
    public void setType(int i,int j){
         this.type = i;
         this.color = j;
    }


    public void setCaseInfos(int i, int colorToSet){
        this.type = i;
        if(i==0){ //SI C EST UNE CASE DE COULEUR
            setCase(colorToSet);
        }
        if(i==1){ //SI C EST UN ANIMAL
            setAnimal(colorToSet);
        }
        if(i==2){ //SI C EST UN OBSTACLE
            setObstacle(colorToSet);
        }
        if(i==3){ //Pour une case blanche vide
            deleteCase(valx,valy);
        }
    }


    private void setCase(int color) { // On va donc maintenant définir le type de case de couleur que l'on veut
        if(color==0){
            Color casecolor = new Color(130, 27, 27);
            tabcase[valx][valy].setBackground(casecolor);
            this.button.setIcon(new ImageIcon("Textures/Red.jpg"));
            button.setContentAreaFilled(true);
            button.setBorderPainted(true);
        }
        if(color==1){
            Color casecolor = new Color(92, 36, 179);
            tabcase[valx][valy].setBackground(casecolor);
            this.button.setIcon(new ImageIcon("Textures/Purple.jpg"));
            button.setContentAreaFilled(true);
            button.setBorderPainted(true);
        }
        if(color==2){
            Color casecolor = new Color(36, 180, 25);
            tabcase[valx][valy].setBackground(casecolor);
            this.button.setIcon(new ImageIcon("Textures/Green.jpg"));
            button.setContentAreaFilled(true);
            button.setBorderPainted(true);
        }
        if(color==3){
            Color casecolor = new Color(54, 91, 229);
            tabcase[valx][valy].setBackground(casecolor);
            this.button.setIcon(new ImageIcon("Textures/Blue.jpg"));
            button.setContentAreaFilled(true);
            button.setBorderPainted(true);
        }
        if(color==4){
            Color casecolor = new Color(255, 253, 58);
            tabcase[valx][valy].setBackground(casecolor);
            this.button.setIcon(new ImageIcon("Textures/Yellow.jpg"));
            button.setContentAreaFilled(true);
            button.setBorderPainted(true);
        }
        if(color==5){
            Color casecolor = new Color(253, 135, 16);
            tabcase[valx][valy].setBackground(casecolor);
            this.button.setIcon(new ImageIcon("Textures/Orange.jpg"));
            button.setContentAreaFilled(true);
            button.setBorderPainted(true);
        }
    }

    private void setAnimal(int color) { // On va définir le type d'animal qu'on veut
        tabcase[valx][valy].setBackground(Color.PINK);
        tabcase[valx][valy].type = 1;
        if(color ==0){
            this.button.setIcon(new ImageIcon("Textures/Pet1.jpg"));
            button.setContentAreaFilled(true);
            button.setBorderPainted(true);
        }
        if(color == 1) {
            this.button.setIcon(new ImageIcon("Textures/Pet2.jpg"));
            button.setContentAreaFilled(true);
            button.setBorderPainted(true);
        }

    }

    private void setObstacle(int color) { // On va définir le type d'obstacle qu'on veut
        tabcase[valx][valy].setBackground(new Color(222, 27, 107, 247));
        tabcase[valx][valy].button.setIcon(new ImageIcon("Textures/Border.jpg"));
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);
        tabcase[valx][valy].type = 2;
    }
    public void deleteCase(int cx, int cy){ // On fait tout pour supprimer correctement une case
        tabcase[cx][cy].setBackground(Color.white);
        tabcase[cx][cy].present = false;
        tabcase[cx][cy].type = 3;
        tabcase[cx][cy].button.setContentAreaFilled(false);
        tabcase[cx][cy].button.setBorderPainted(false);
        tabcase[cx][cy].button.setIcon(new ImageIcon("Textures/Blank.jpg"));

    }

    public void setTAB(CaseGame[][] tab){ //Afin de pouvoir avoir le même tableau en commun que les autres cases et rester synchro
        this.tabcase = tab;
    }

    public void save(){ // On sauvegarde le casegame dans un fichier
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fichier = new FileOutputStream("data/data.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(this.tabcase);
            oos.flush();
        } catch (final IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //On vérifie d'abord si on a un bonus actif
        if(GameBoxLevel.ValButton ==0) { // Jeu Normal
            checkCase(valx, valy, 0);
            GameBoxLevel.ValButton = 0;
        }
        else if (GameBoxLevel.ValButton ==1) { // Bot
            botPlays();
            GameBoxLevel.ValButton = 0;
        }
        else if (GameBoxLevel.ValButton ==2) { // Marteau
            delete3x3Case();
            GameBoxLevel.ValButton = 0;
        }
        else if(GameBoxLevel.ValButton ==3) { // Fusée
            removeColonne(valy);
            GameBoxLevel.ValButton = 0;
        }
        for(int i = 0;i<10;i++){ // Ensuite on update la grille plusieurs fois pour éviter certains bugs d'actualisation
            checkBLANKCASES();
            updatetabV();
            updatetabH();
        }
        checkAnimals(); // On check les animaux
        save(); // On sauvegarde au cas ou pour reprendre si le jeu est fermé
        GameBoxLevel.updateScore();
        for(int i = 0;i<10;i++){
            checkBLANKCASES();
            updatetabV();
            updatetabH();
        }

        if(isLost()){ // On vérifie si c est perdu
            JOptionPane.showMessageDialog(null,"Oh non perdu !","Perdu",JOptionPane.INFORMATION_MESSAGE);
        }
        if(isWin()){// On vérifie si c est gagné et on actualise derrière
            JOptionPane.showMessageDialog(null,"BRAVO VOUS AVEZ GAGNE ! Votre score sur ce niveau est de : "+ scoreTotal,"Gagné",JOptionPane.INFORMATION_MESSAGE);
            int score = ScoreCalcul.readScore();
            score = scoreTotal+score;
            scoreTotal = 0;
            ScoreCalcul.writeScore(score);
        }
    }

    private void delete3x3Case() { // On supprime des cases en 3x3x3 en faisant attention aux out of bounds
        if(valx-1>=0 && valy-1>=0 && tabcase[valx-1][valy-1].type == 0){
            deleteCase(valx-1,valy-1);
        }
        if(valx-1>=0 && tabcase[valx-1][valy].type == 0){
            deleteCase(valx-1,valy);
        }
        if(valy+1<10  && valx+1<10 && tabcase[valx+1][valy+1].type == 0){
            deleteCase(valx+1,valy+1);
        }
        if(valx+1<10 && tabcase[valx+1][valy].type == 0){
            deleteCase(valx+1,valy);
        }
        if(valy+1<10 && tabcase[valx][valy+1].type == 0){
            deleteCase(valx,valy+1);
        }
        if(valy-1>=0 && tabcase[valx][valy-1].type == 0){
            deleteCase(valx,valy-1);
        }
        if(valx-1>=0 && valy+1<10 && tabcase[valx-1][valy+1].type == 0){
            deleteCase(valx-1,valy+1);
        }
        if(valx+1<10 && valy-1>=0 && tabcase[valx+1][valy-1].type == 0){
            deleteCase(valx+1,valy-1);
        }
        if(tabcase[valx][valy].type == 0){
            deleteCase(valx,valy);
        }
    }


    private void removeColonne(int y) { // On supprime toute une colone avec la fusée
        for(int i =0;i<10;i++){
            if(tabcase[i][y].getType() == 0){
                deleteCase(i,y);
            }
        }
    }

    private void checkBLANKCASES() { // Ici on delete juste toutes les cases blanches au début pour éviter certains bugs rencontrés durant le projet
        for(int i = 0;i<10;i++){
            for(int j=0;j<10;j++){
                if(tabcase[i][j].getBackground().equals(Color.white)){
                    tabcase[i][j].deleteCase(i,j);
                }
            }
        }
    }

     public void checkAnimals() { // On va ici vérifier si des animaux se trouvent sur la derniere ligne pour les retirer
        for(int i =0;i<10;i++){
            if(tabcase[9][i].type == 1){  // Si Type = 1 alors animal et comme nous sommes sur la derniere colone
                deleteCase(9,i); // On supprime la case contenant l'animal
                animaltoSave--; // On retire donc 1 animal de la liste de ceux à sauver
                GameBoxLevel.petsLabel.setText(String.valueOf(animaltoSave)); // On met à jour le nb d'animaux à sauver
                ScoreSessActual = ScoreSessActual +1000;  // On update les différents scores
                scoreTotal = scoreTotal+1000;
                updatetabH(); // On met ensuite à jour la grille Horizontalement
                updatetabV(); // Puis verticalement
                checkAnimals(); // Et on reverifie derriere s'il n'y a pas d'autres animaux en réappelement la fonctione (Ex : 2 animaux se supperposant)
            }
        }
    }

    public void updatetabV() { // Ici on va parcourir le tableau de droite à gauche en partant du bas afin de vérifier si la case d'au dessus n'est pas vide et celle
                              // Actuelle non alors on swap les 2 et ensuite on refait ça indéfiniment tant que des swaps sont faits
        int k = 1;
        int w = 0;
        while (w==0){
            k = 0;
            for(int i = 9;i>0;i--){
                for(int j =9;j>=0;j--){
                    if(tabcase[i][j].getType() == 3 && tabcase[i-1][j].getType() !=3 && tabcase[i][j].type != 2 && tabcase[i-1][j].type != 2){
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

    public void Swap2Cases(int x1, int y1, int x2, int y2){ // Pour swap une case et supprimer la 2e (utilisé pour l'actualisation V pour accelerer
        tabcase[x1][y1].setType(tabcase[x2][y2].getType(),tabcase[x2][y2].getColor());
        tabcase[x1][y1].setCaseInfos(tabcase[x2][y2].getType(),tabcase[x2][y2].getColor());
        tabcase[x1][y1].present = true;
        deleteCase(x2,y2);
    }

    public void updatetabH() { // On parcours la grille de bas en haut et si une colonne est vide on la swap avec celle à sa droite via swapcase
        for(int i = 0;i<9;i++){
            int k = 0;
            for(int j = 0 ; j<10; j++){
                if(tabcase[j][i].present){
                    k++;
                }
            }
            if(k==0){
                SwapCases(i);
                updatetabH();// On réexecute la fonction pour voir si une autre actualisation est à faire
            }
            k=0;
        }
    }
    public void setPresent(boolean b){
        this.present = b;
    }

    public void SwapCases(int actual) { // Cette fonction prend en argument une valeur correspondant à une colonne et la swap avec celle à sa droite
        for(int i = 0;i<10;i++){
            if(tabcase[i][actual].type != 2 && tabcase[i][actual+1].type !=2){
                tabcase[i][actual].setType(tabcase[i][actual+1].getType(),tabcase[i][actual+1].getColor());
                tabcase[i][actual].setCaseInfos(tabcase[i][actual+1].getType(),tabcase[i][actual+1].getColor());
                deleteCase(i,actual+1);
                tabcase[i][actual].present = true;
            }
        }
    }


    public boolean isWin(){ // Si le nb d'animaux = 0 alors c est gagné !
        return animaltoSave==0;
    }
    public boolean isLost(){ // Ici on va juste vérifier toutes les combi possibles, et si une est possible alors ce n'est pas perduq
        for(int i = 0;i<10;i++){
            for(int j = 0;j<10;j++){
                if(!(tabcase[i][j].getBackground().equals(Color.WHITE))){
                    if(i!=9){
                        if(tabcase[i+1][j].getBackground().equals(tabcase[i][j].getBackground())&& tabcase[i+1][j].type !=1 && tabcase[i+1][j].type !=2&& tabcase[i+1][j].present ){
                            return false;
                        }
                    }
                    if(i!=0){
                        if(tabcase[i-1][j].getBackground().equals(tabcase[i][j].getBackground())&& tabcase[i-1][j].type !=1 && tabcase[i-1][j].present){
                            return false;
                        }
                    }
                    if(j!=9){
                        if(tabcase[i][j+1].getBackground().equals(tabcase[i][j].getBackground())&& tabcase[i][j+1].type !=1&& tabcase[i][j+1].present){
                            return false;
                        }
                    }
                    if(j!=0){
                        if(tabcase[i][j-1].getBackground().equals(tabcase[i][j].getBackground())&& tabcase[i][j-1].type !=1&& tabcase[i][j-1].present){
                            return false;
                        }
                    }
                }
            }
        }
        if(!isWin()){ // On vérifie bien que c'est pas win car par exemple s'il y'a aucune case et aucun animal iswin et islose = true sauf que c est gagné
            return true;
        }
        return false;
    }

    public void setColor(int i){
        this.color = i;
    } //Pour changer la couleur comme private


    public boolean checkCase(int cx, int cy,int k){ // k sert d'argument pour savoir s'il s'agit du premier appel ou pas de la fonction
        int z = 0;//Nombre de cases autour si > 0 alors cette case pourra être suprimée plus tard
        if(k==0){ // S'il s'agit du premier du premier appel alors on reset le combo pour le nouveau coup
            combo = 0;
        }
        //Si x = 0 alors on peut pas check la case en haut mais celle en bas si donc on le fait
        if(cx == 0 && tabcase[cx+1][cy].present && tabcase[cx+1][cy].getBackground().equals(tabcase[cx][cy].getBackground()) && tabcase[cx][cy].type !=1 && tabcase[cx][cy].type !=2 && tabcase[cx][cy].type !=3){
            z++; // On incrémente le nb de cases autour
            tabcase[cx][cy].present = false; //
            checkCase(cx+1,cy,1);// Et on appelle récursivement la fonction sur cette case
            combo++;// Si la case respecte les conditions alors on incrémente le combo de 1
        }

        if(cx !=0 && tabcase[cx-1][cy].present && tabcase[cx-1][cy].getBackground().equals(tabcase[cx][cy].getBackground())&& tabcase[cx][cy].type !=1 && tabcase[cx][cy].type !=2 && tabcase[cx][cy].type !=3){
            z++;
            tabcase[cx][cy].present = false;
            checkCase(cx-1,cy,1);
            combo++;
        }
        if(cx !=9 && tabcase[cx+1][cy].present && tabcase[cx+1][cy].getBackground().equals(tabcase[cx][cy].getBackground())&& tabcase[cx][cy].type !=1 && tabcase[cx][cy].type !=2 && tabcase[cx][cy].type !=3){
            z++;
            tabcase[cx][cy].present = false;
            checkCase(cx+1,cy,1);
            combo++;
        }
        if(cy != 9 && tabcase[cx][cy+1].present && tabcase[cx][cy+1].getBackground().equals(tabcase[cx][cy].getBackground())&& tabcase[cx][cy].type !=1 && tabcase[cx][cy].type !=2 && tabcase[cx][cy].type !=3){
            z++;
            tabcase[cx][cy].present = false;
            checkCase(cx,cy+1,1);
            combo++;

        }
        if(cy ==0 && tabcase[cx][cy+1].present && tabcase[cx][cy+1].getBackground().equals(tabcase[cx][cy].getBackground())&& tabcase[cx][cy].type !=1 && tabcase[cx][cy].type !=2 && tabcase[cx][cy].type !=3){
            z++;
            tabcase[cx][cy].present = false;
            checkCase(cx,cy+1,1);
            combo++;
        }

        if(cy !=0 && tabcase[cx][cy-1].present && tabcase[cx][cy-1].getBackground().equals(tabcase[cx][cy].getBackground())&& tabcase[cx][cy].type !=1 && tabcase[cx][cy].type !=2 && tabcase[cx][cy].type !=3){
            z++;
            tabcase[cx][cy].present = false;
            checkCase(cx,cy-1,1);
            combo++;
        }

        if(k ==1){ //Si k = 1 alors l'appel a été fait depuis une autre case donc la case peut se faire supprimer même si y'a rien à côté
            deleteCase(cx,cy);
        }
        if(z >0 & k==0){ //On arrive ici à la fin de la fonction pour la case initiale et qu'au moins 1 de combo est fait
            deleteCase(cx,cy); // On supprime la case actuelle
            combo++; // On incrémente le combo de la case actu
            ScoreSessActual = ScoreSessActual + combo*combo*10; // On ajoute aux scores le score de ce coup
            scoreTotal = scoreTotal + combo*combo*10;
            return true; //true car une suppression a été faite
        }
        return false; // false si aucune suppression n'est faite
    }

    public void botPlays(){ // On parcours le tableau de haut en bas et si checkcase return true cela veut dire qu'un tour a été joué et donc le bot a joué
        for(int i = 0;i<10;i++){
            for(int j = 0; j<10; j++){
                if(checkCase(i,j,0)){
                    return;
                }
            }
        }
    }

}


