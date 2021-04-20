import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.logging.Level;

public class  HomeLevelChoice{ // Cette classe de Frame pour contenir la liste des niveaux par défaut
    private JButton lvl1 = new JButton("Niveau 1");   // Ces boutons seront les boutons pour les différents niveaux
    private JButton lvl2 = new JButton("Niveau 2");
    private JButton lvl3 = new JButton("Niveau 3");
    private JButton lvl4 = new JButton("Niveau 4");
    private JButton lvl5 = new JButton("Niveau 5");
    private final JButton Precedent = new JButton("Precedent");
    private static final JFrame frame = new JFrame("Pet Rescue Java");
    private final JPanel Levels = new JPanel();
    private int valLevel = readValueProgrssion();


    public HomeLevelChoice(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("Textures/IconGame.png");
        frame.setIconImage(icon); // On set l'icone de la fenêtre
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); // On récupère la dimension de l'écran de l'user pour trouver le milieu
        frame.setSize(450, 450);
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);     // On place la fenêtre au milieu de l'écran de l'utilisateur
        frame.setVisible(true);
        ChecksButtons();
        AddButtons();
        frame.add(Levels);

        Precedent.addActionListener(e -> {//Le listener du bouton qui permet de fermer cette fenêtre et réouvrir l'accueil quand on clique dessus
            Home home = new Home();
            home.createAndShowGUI();
            frame.dispose();
        });
    }

    private void AddButtons() { // Afin d'ajouter les boutons au Jpanel, on fait une function juste pour simplifier le constructeur
        Levels.add(lvl1);
        Levels.add(lvl2);
        Levels.add(lvl3);
        Levels.add(lvl4);
        Levels.add(lvl5);
        Levels.add(Precedent);
        lvl1.addActionListener(e -> { //A chaque fois qu'un niveau est lancé pour la premiere fois celui d'arpes est débloqué
            if(readValueProgrssion()<1){
                writeFile(1);
            }
            ChecksButtons();
            launchLevel("Niveau1");
        });
        lvl2.addActionListener(e -> {
            if(readValueProgrssion()<2){
                writeFile(2);
            }
            ChecksButtons();
            launchLevel("Niveau2");
        });
        lvl3.addActionListener(e -> {
            if(readValueProgrssion()<3){
                writeFile(3);
            }
            ChecksButtons();
            launchLevel("Niveau3");
        });
        lvl4.addActionListener(e -> {
            if(readValueProgrssion()<4){
                writeFile(4);
            }
            ChecksButtons();
            launchLevel("Niveau4");
        });
        lvl5.addActionListener(e -> {
            ChecksButtons();
            launchLevel("Niveau5");
        });
    }

    private void ChecksButtons() { //Ici on va réccupérer les niveaux que le joueur a déjà lancé et derrière désactiver ceux qu'il n'a pas débloqué
        if (valLevel == 0) {
            lvl2.setEnabled(false);
            lvl3.setEnabled(false);
            lvl4.setEnabled(false);
            lvl5.setEnabled(false);
        }
        if(valLevel ==1){
            lvl2.setEnabled(true);
        }
        if(valLevel == 2){
            lvl2.setEnabled(true);
            lvl3.setEnabled(true);

        }
        if(valLevel == 3){
            lvl2.setEnabled(true);
            lvl3.setEnabled(true);
            lvl4.setEnabled(true);
        }
        if( valLevel == 4){
            lvl2.setEnabled(true);
            lvl3.setEnabled(true);
            lvl4.setEnabled(true);
            lvl5.setEnabled(true);
        }

    }

    private int readValueProgrssion() { // On récupère le fichier de progression ici pour savoir où en est le joueur
        ObjectInputStream ois;
        try {
            final FileInputStream fichier = new FileInputStream("jeu/progression");
            ois = new ObjectInputStream(fichier);
            return (int) ois.readObject();
        }
        catch (Exception e){ // Si le fichier n'existe pas alors il est crée
            System.out.println("Erreur lors de la lecture du fichier progression, creation d'un nouveau fichier progression...");
            writeFile(0);
            //launchLevel("Niveau1");
        }
        return 0;
    }

    private void launchLevel(String s) { // On va ici lire le niveau dont le nom est s dans le répertoire jeu
        ObjectInputStream ois;
        String w = "jeu/"+s;
        try {
            final FileInputStream LevelFile = new FileInputStream(w);
            ois = new ObjectInputStream(LevelFile);
            Home.lvlName = w;
            final GameBox main;
            main = new GameBoxLevel((CaseGame[][]) ois.readObject());
            main.setVisible(true);
            frame.repaint();
            frame.dispose(); // On ferme la frame actuelle apres avoir ouvert le niveau
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement du fichier");
        }
    }

    public void writeFile(int n){
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream("jeu/progression"); // On save ici le nouveau Integer dans le fichier pour savoir l'avancement
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(n);
            oos.flush();
        }
        catch (final java.io.IOException e) {
            e.printStackTrace();
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
        valLevel = n;
    }
}
