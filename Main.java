
import java.io.*;
import java.util.Scanner;

// Classe Main qui correspond à la classe principale du Projet
public class Main {

    // Classe statique play() qui permet de lancer une GrilleT Random
    public static void play() throws IOException {
        GrilleT gr = new GrilleT(10,10);
        gr.setRandomColor(); // on lui attribue des cases randoms
        gr.afficheTAB(); // on affiche la grille
        Scanner sc = new Scanner(System.in); // On ouvre un scanner
        System.out.println("Entrez X:");
        // On se prépare à donner les coordonnées à rentrer dans le terminal pour pouvoir jouer
        String xa = sc.nextLine(); // On récupère la ligne dans le terminal
        int x = 0; // 0 est la valeur par défault si il ya un soucis lorsque l'on rentre une coordonnées dans la case
        try{
             x = Integer.parseInt(xa); // On vérifie que x est un entier
        }
        catch (NumberFormatException ignored){
            // On catche l'exception et on l'ignore ce qui permet d'éviter de recevoir l'erreur missinput argument
            // On a pas besoin de rest la valeur de x ici car elle est déjà à 0 par défault
        }
        System.out.println("Entrez Y:");
        String ya = sc.nextLine(); // On récupère la ligne dans le terminale correspond à y
        int y =0;  // On considére que 0 est la valeur par défault
        try{
            y = Integer.parseInt(ya);  // On vérifie que Y est un entier
        }
        catch (NumberFormatException ignored){
            // On catche l'exception et on l'ignore ce qui permet d'éviter de recevoir l'erreur missinput argument
            // On a pas besoin de rest la valeur de x ici car elle est déjà à 0 par défault
        }
        while (true) { // On boucle à l'infini de toute facon on sort du while lorsqu'on a perdu ou qu'on a gagné
            if(x < 0 || y < 0 || x > 10 || y > 10) { // Lors ce que l'on quitte le niveau random on sauvegarde la partie pour pouvoir la reprendre
                StoreObjectT obj = new StoreObjectT();  // On créer un objet pour pouvoir sauvegarder la Grillet
                obj.stored(gr); // On la store dans un objet
                principale(); // On retourne au menu principal
            }
            CaseGameT tmp = gr.getTabcase()[y][x];
            tmp.checkCase(y,x,0); // Fonction permettant de check les cases aux alentours et de les supprimers
            tmp.updatetabV(); // On fait la gravité
            tmp.updatetabH(); // On fait le décalage à gauche
            gr.checkAnimal(); // et on vérifie si les animaux peuvent-être supprimé
            tmp.calculatePoint(); // On calcule le nombre de point que nous a fait gagné les points précédent
            System.out.println("Vous avez "+tmp.getPointGame()+" Points dans la Partie en cours!");

            if(tmp.countAnimal() == 0 ){  // On vérifie si la partie est gagné en vérifiant si il n'y a pas d'animaux restants
                System.out.println("Vous avez Gagné!");
                gr.afficheTAB(); // On affiche le tableau qui reste
                principale(); // On retourne au menu principal

            }
            if(tmp.isLost()){ // On vérifie si la partie est perdu car il reste des animaux et il n'y a plus de mouvements possible
                System.out.println("PERDU!");
                sc.close(); // on close le scanner
                gr.afficheTAB(); // On affiche le tableau
                break;
            }
            gr.afficheTAB(); // On affiche le tableau
            System.out.println("Entrez X:");
            /*
              On recommence on revérifie les coordonnées
             */
            xa = sc.nextLine();
            try{
                x = Integer.parseInt(xa);
            }
            catch (NumberFormatException ignored){
            }
            System.out.println("Entrez Y:");
            ya = sc.nextLine();
            try{
                y = Integer.parseInt(ya);
            }
            catch (NumberFormatException ignored){
            }
        }
    }


    // Fonction permettant d'éditer un niveau
    public static void edit() throws IOException {
        // On créer une grille 10x10 car dans la version terminale on a pas implémenter la vérification pour des lignes et colonnes différentes
        GrilleTEdit gr = new GrilleTEdit(10,10); // On crée une GrilleTEdit
        gr.afficheTAB(); // On affiche le tableau, il est ainsi composé que de case vide

        Scanner sc = new Scanner(System.in); // On ouvre un scanner
        System.out.println("Entrez X:");
        // On se prépare à donner les coordonnées à rentrer dans le terminal pour pouvoir jouer
        String xa = sc.nextLine(); // On récupère la ligne dans le terminal
        int x = 0; // 0 est la valeur par défault si il ya un soucis lorsque l'on rentre une coordonnées dans la case
        try{
            x = Integer.parseInt(xa); // On vérifie que x est un entier
        }
        catch (NumberFormatException ignored){
            // On catche l'exception et on l'ignore ce qui permet d'éviter de recevoir l'erreur missinput argument
            // On a pas besoin de rest la valeur de x ici car elle est déjà à 0 par défault
        }
        System.out.println("Entrez Y:");
        String ya = sc.nextLine(); // On récupère la ligne dans le terminale correspond à y
        int y =0;  // On considére que 0 est la valeur par défault
        try{
            y = Integer.parseInt(ya);  // On vérifie que Y est un entier
        }
        catch (NumberFormatException ignored){
            // On catche l'exception et on l'ignore ce qui permet d'éviter de recevoir l'erreur missinput argument
            // On a pas besoin de rest la valeur de x ici car elle est déjà à 0 par défault
        }
        while(true){  // On boucle à l'infini de toute facon on sort du while lorsqu'on a perdu ou qu'on a gagné
            if(x < 0 || y < 0 || x > 10 || y > 10) {
                throw new IllegalArgumentException("Arguments inconnu");
            }
            Scanner principale = new Scanner(System.in); // On recrée un scanner pour récuper si soit la case qu'on créer est un animal/nombre
            System.out.println("Chosir une option");
            System.out.println("[1] Ajouter un Animal");
            System.out.println("[2] Ajouter une Nombre");
            System.out.println("[3] Quitter");
            String option = principale.nextLine(); // On récupère ce qu'on rentre dans le scanner principale
            switch (option) { // Switch permettant de vérifier ce que rentre l'utilsiateur
                case "1" :
                    gr.tabcase[0][0].setAnimal(y,x); // On set la position de l'animal
                    break;
                case "2" :
                    gr.createCase(y,x); // On lance la fonction créer une case qui nous dira quelle nombre ont veut sélectionner
                    break;
                case "3" :
                    StoreObjectT obj2 = new StoreObjectT(); // On quitte donc on va vouloir sauvegarder le niveau
                    System.out.println("Entrez le nom du Niveau");
                    Scanner scobj = new Scanner(System.in); // On recrée un scanner pour pouvoir récupèrer le nom du niveau
                    String str = scobj.nextLine(); // On récupère le nom du niveau
                    gr.setTabcase(gr.getTabcase()); // On set dans la grille le tabcase pour pouvoir accéder au tabcase plus facilement
                    obj2.storec(gr,str); // On sauvegarde la GrilleTEdit avec son nom donné ci-dessus
                    principale(); // On lance le menu prinicpale
                    break;
                default: throw new IllegalArgumentException("Arguments inconnu"); // On vérifie si les arguments rentrées ne correpondent pas à ceux  ci-dessus
            }
            gr.afficheTAB(); // on affiche le tableau
            System.out.println("Entrez X:");
            xa = sc.nextLine();
             /*
              On recommence on revérifie les coordonnées
             */
            try{
                x = Integer.parseInt(xa);
            }
            catch (NumberFormatException ignored){
            }
            System.out.println("Entrez Y:");
            ya = sc.nextLine();
            try{
                y = Integer.parseInt(ya);
            }
            catch (NumberFormatException ignored){
            }
        }

    }


    // Permet de lancer les niveaux Customisable câd créer dans l'éditeur de niveau
    public static void custom(){
        System.out.println("Veuillez choisir parmi les niveaux suivants:");
        System.out.println("COPY/PASTE un niveau parmi les suivant:");
        File f = new File("terminal/custom"); // On vérifie dans le dossier custom qui est lui même contenu dans terminal
        File []paths = f.listFiles(); // On récupère les fichiers à l'intérieur de ce chemin
        if (paths != null) { // On vérifie si il le sous dossier custom contient bien des fichiers
            for(File file: paths){
                System.out.println(file); // On affiche les fichiers disponibles câd qui correspond à une GrilleTEdit qu'on peut lancer
            }
        }
        Scanner scs = new Scanner(System.in); // On ouvre un scanner pour récupèrer le nom du niveau choisi
        String str = scs.nextLine(); // On récupère ce que l'utilisateur a entré
        StoreObjectT obj = new StoreObjectT(); // On crée un objet stockable
        obj.display(str,false); // on affiche l'objet stocké
    }


    // Permet de reprendre une partie qui a été quittée
    public static void reprendre(){
        System.out.println("Vous allez reprendre la partie que vous avez quitté:");
        String data = "terminal/data.ser"; // chemin correspond ou la partie à été sauvegardé
        StoreObjectT obj = new StoreObjectT(); // On créer un objet stockable
        obj.display(data,true); // On appelle la fonction display permettant d'afficher la grille stockée
    }


    /* Fonction permettant de joueur l'aventure
    A chaque fois que vous finissez une partie, vous passez au suivant et si il n'y a plus de niveau suivant vous avez fini l'aventure
    Vous ne pouvez pas rejouer les niveaux que vous avez fini
    Pour recommencer l'aventure il faut supprimé le fichier niveau.txt situé dans terminal/niveau.txt
     */
    public static void aventure() throws IOException {
        File niveau = new File("terminal\\niveau.txt");
        if (!niveau.createNewFile()) { // On vérifie si le niveau n'existe pas
            try{
                Scanner myReader = new Scanner(niveau); // On crée un scanner qui lit le contenu du fichier
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine(); // On récupère ce que contient le fichier niveau.txt
                    System.out.println("Vous êtes au niveau: "+data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Vous n'avez fini aucun niveau!"); // Si il n'y a pas de contenu, câd jamais puis ce qu'il est set à 1 par défault
            }
        }else{
            /**
             * On set la valeur par défault à 1 si le fichier n'existe pas
             */
            FileWriter writer = new FileWriter(niveau);
            BufferedWriter WriteFileBuffer = new BufferedWriter(writer); // On se prépare à écrire dans le fichier niveau.txt
            WriteFileBuffer.write(Integer.toString(1));  // On écrit le chiffre un dans le fichier niveau.txt
            WriteFileBuffer.close();
            writer.close();
        }

        // On vérifie si le prochain niveau existe sinon ca veut dire que le jeu est fini
        try {
            Scanner myReader = new Scanner(niveau);
            int niveaud = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine(); // On lit le contenu du fichieer niveau.txt
                niveaud = Integer.parseInt(data); // On ajoute le contenue du fichier à l'entier niveaued
            }
            String data = "terminal/niveau/"+niveaud+".ser";
            StoreObjectT obj = new StoreObjectT();

            File f = new File("terminal/niveau/"+niveaud+".ser");
            boolean bool;
            bool = f.exists(); // On vérifie si le niveau existe
            if(bool) {
                obj.display(data,true); // si il existe on l'affiche
            }else{
                System.out.println("Le jeu est fini!"); // sinon il n'existe pas
            }
        } catch (FileNotFoundException e) {
            System.out.println("Vous n'avez fini aucun niveau"); // Si il n'y a aucun fichier
        }
    }


    // La fonction statique permant d'affiche le  menu principale du Terminal avec les options Jouer/Aventure/Editeur/Niveau Random/ Custom / Quitter
    public static void principale() throws IOException {
        /* Menu Principale */
        Scanner principale = new Scanner(System.in); // On créer un scanner pour pouvoir récupère l'option que l'utilsiateur veut choisir dans le terminal
        // On affiche les options possible
        System.out.println("Chosir une option");
        System.out.println("[1] Jouer un niveau");
        System.out.println("[2] Reprendre la Partie");
        System.out.println("[3] Editeur de Niveau");
        System.out.println("[4] Jouer un niveau Random");
        System.out.println("[5] Jouer un niveau Custom");
        System.out.println("[6] Quitter");
        String option = principale.nextLine();
        switch (option) {
            case "1" : aventure(); break; // lance l'aventure
            case "2" : reprendre(); break; // permet de reprendre une partie
            case "3" : edit(); break; // lance l'éditeur de niveau
            case "4" : play(); break; // On peut joueur un niveau Random
            case "5" : custom(); break; // lance un niveau custom au choix
            case "6" :
                System.exit(0); // On quitte le terminal
                break;
            default : throw new IllegalStateException("Valeur inconnu: " + option); // Permet de vérifier si les arguments rentré sont invalide
        }
    }

    // Fonction main permettant d'intéragir entre la version Terminal / Graphique
    public static void main(String[] args) throws IOException {
        // On lance un scanner pour pouvoir récupérer les réponses de l'utilisateur
        Scanner sc = new Scanner(System.in);
        // On lance les options 2 pour la version terminale et tout les chiffres possible pour la version graphique
        System.out.println("Bienvenue sur PetRescue Java !");
        System.out.println("1: Pour la version Graphique | 2: Pour la version terminal");
        System.out.println("Toute autre reponse sera consideree comme 1");
        String x = sc.nextLine() ; // On récupère le contenu de ce qui est rentré dans le terminal
        int val;
        try{
            val = Integer.parseInt(x); // On vérifie si le contenu entré dans le terminal est un entier

        }
        catch(Exception e){ // Par défaut on a dit que c'était la version Graphique
            val = 1;
        }
        if(val == 2){ // Si c'est l'option 2 on lance la version Terminale
            File file = new File("terminal\\points.txt");

            if (!file.createNewFile()) { // on vérifie si le fichier point existe
                try{
                    Scanner myReader = new Scanner(file);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine(); // Si le fichier point n'est pas vide il récupère le contenu du fichier
                    System.out.println("Vous avez "+data+" points!"); // On affiche le nombre de points
                }
                myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Vous n'avez pas de points!"); // Si le fichier point n'existe pas, l'utilisateur n'a aucun point
                }
            }
            principale(); // On lance le menu principale du Terminal
        }else{ // On rentre dans le cas tout sauf 2
           
            @SuppressWarnings("unchecked")
            Home home = new Home(); // On créer l'objet Home qui contient le menu de l'interface graphique
            javax.swing.SwingUtilities.invokeLater(() -> home.createAndShowGUI()); // On lance le menu principal de l'interface graphique
      
        }
    
    }
}
