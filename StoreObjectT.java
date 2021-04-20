import java.io.*;
import java.util.Scanner;

/* La classe StoreObject permet de mieux stocker les GrillesT et ses fils plus facilement et de montrer les grilles de facon plus simplifier */
public class StoreObjectT {

    //  Fonction permettant de sauvegarder les niveaux customs
    public void storec(GrilleT gr, String str){
        OutputStream ops; // Flux de sortie
        ObjectOutputStream objOps = null; // Objet de Flux de sortie
        try {
            ops = new FileOutputStream("terminal/custom/"+str+".ser"); // On dit que le flux de sortie est un objet effectif FileOutPutstream
            objOps = new ObjectOutputStream(ops); // On assigne l'objet 
            objOps.writeObject(gr); // On assigne l'objet à objetouputstream
            objOps.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(objOps != null) objOps.close();
            } catch (Exception ignored){

            }
        }

    }

    // Fonction permettant de stocker les niveaux 
    public void store(GrilleT gr, String str){
        OutputStream ops;
        ObjectOutputStream objOps = null;
        try {
            ops = new FileOutputStream("terminal/niveau/"+str); // On dit que le flux de sortie est un objet effectif FileOutPutstream
            objOps = new ObjectOutputStream(ops); // On assigne l'objet 
            objOps.writeObject(gr); // On créer l'objet
            objOps.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(objOps != null) objOps.close();
            } catch (Exception ignored){

            }
        }
    }


    // Fonction permettant de stocker les niveaux data
    public void stored(GrilleT gr){
        OutputStream ops;
        ObjectOutputStream objOps = null;
        try {
            ops = new FileOutputStream("terminal/data.ser"); // On dit que le flux de sortie est un objet effectif FileOutPutstream
            objOps = new ObjectOutputStream(ops);  // On assigne l'objet 
            objOps.writeObject(gr); // On créer l'objet
            objOps.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(objOps != null) objOps.close();
            } catch (Exception ignored){

            }
        }
    }


    // Permet d'afficher les grilles stockées
    public void display(String str, boolean check){
        InputStream fileIs;
        ObjectInputStream objIs = null;
        try {
            fileIs = new FileInputStream(str);
            objIs = new ObjectInputStream(fileIs);
            GrilleT gr = (GrilleT) objIs.readObject();
            gr.afficheTAB(); // On affiche le tableau
            // On récupère les infomrations que nous donne l'utilisateur
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez X:"); 
            String xa = sc.nextLine(); 
            int x = 0;
            try{
                x = Integer.parseInt(xa); // On vérifie si le joueur a bien rentré une bonne valeur
            }
            catch (NumberFormatException ignored){
            }
            System.out.println("Entrez Y:"); 
            String ya = sc.nextLine(); // On récupère la coordonnée Y
            int y =0;
            try{
                y = Integer.parseInt(ya); // On vérifie si le joueur a bien rentré une bonne valeur
            }
            catch (NumberFormatException ignored){
            }
            while (true) {
                if (x < 0 || y < 0 || x > 10 || y > 10) {
                    throw new IllegalArgumentException("Ces valeurs ne sont pas acceptees!");  // Tout  est dit évite OUT of BONS
                }
                CaseGameT tmp = gr.getTabcase()[y][x];
                tmp.checkCase(y, x, 0); // On check toutes les cases
                tmp.updatetabV(); // On vérifie la gravité
                tmp.updatetabH(); // On fait le décalage à gauche 
                gr.checkAnimal(); // On check les animaux
                tmp.calculatePoint(); // On calcule les points 
                System.out.println("Vous avez " + tmp.getPointGame() + " Points dans la Partie en cours!");
                if (tmp.countAnimal() == 0) { // On vérifie si la Partie est gagné 
                    // Store data like play()
                    if(check){    // On check ca permet de voir si on stocke les points ou non check correspond à un niveau à sauvegarder
                    try {
                        System.out.println("Vous avez Gagné!");
                        File file = new File("terminal\\points.txt");
                        Scanner myReader = new Scanner(file);
                        int pointfile = 0;              // On set la valeur pointfile à 0
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            pointfile = Integer.parseInt(data); // On récupère son contenu et on l'assigne à pointfile
                        }
                        FileWriter writer = new FileWriter(file);
                        BufferedWriter WriteFileBuffer = new BufferedWriter(writer);
                        pointfile = pointfile + tmp.getPointGame(); 
                        WriteFileBuffer.write(Integer.toString(pointfile)); // On rajoute les points à pointfile
                        WriteFileBuffer.close();
                        writer.close();
                        myReader.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Vous n'avez pas de points!");
                    }

                    try {
                        File file = new File("terminal\\niveau.txt");
                        Scanner myReader = new Scanner(file);
                        int niveau = 0;
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            niveau = Integer.parseInt(data); // On  vérifie si le fichier contient bien un INT
                        }
                        System.out.println("Vous avez gagné!");
                        FileWriter writer = new FileWriter(file);
                        BufferedWriter WriteFileBuffer = new BufferedWriter(writer);
                        niveau = niveau + 1;
                        WriteFileBuffer.write(Integer.toString(niveau));    // On additionne les INT
                        WriteFileBuffer.close();
                        writer.close();
                        myReader.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Vous n'avez pas de niveau!");
                    }
                    StoreObjectT obj = new StoreObjectT();
                    System.out.println("GAGNE!");
                    obj.stored(gr); // On sauvegarde la DATA pour reprendre la PARTIE
                    }
                    gr.afficheTAB();
                    Main.principale();
                }
                if (tmp.isLost()) { // On vérifie si on a perdu
                    System.out.println("PERDU!");
                    gr.afficheTAB();
                    Main.principale();
                }
                gr.afficheTAB();
                System.out.println("Entrez X:");
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
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(objIs != null) objIs.close();
            } catch (Exception ignored){

            }
        }

    }

}
