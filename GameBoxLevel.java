import javax.swing.*;
import java.awt.*;

public class GameBoxLevel extends GameBox {
    private  Grille principale;
    protected static JLabel jlabel = new JLabel();
    protected static JLabel petsLabel = new JLabel();
    private JButton Hammer = new JButton("MARTEAU");
    private JButton Firework = new JButton("FUSEE");
    private JButton Bot = new JButton("BOT");
    protected static int ValButton;
    public GameBoxLevel(){
        super();
        principale = new Grille();
        this.setTitle("Pet Rescue Java");
        principale.setBounds(150,150,500,500);
        this.add(principale);
        FillConstructor(0);
    }
    public GameBoxLevel(CaseGame[][] tabCase){ // On utilise tabCase pour pouvoir importer un niveau déjà sauvegardé
        super();
        principale = new Grille(tabCase);
        this.setTitle("Pet Rescue Java");
        principale.setBounds(150,150,500,500);
        this.add(principale);
        FillConstructor(1); // On utilise cette fonction pour rendre le constructeur plus léger
    }
    public void FillConstructor(int k){
        JPanel Score = new JPanel();
        Score.setBounds(638,43,145,38);
        jlabel.setFont(new Font("Verdana",1,23));
        Score.add(jlabel);
        this.add(Score);
        JPanel levelName = new JPanel();
        levelName.setBounds(258,84,286,52);
        JLabel e = null;
        if(k==1) { // Ici si k = 1 alors il s'agit d'un niveau ouvert donc on va récupérer le chemin puis derriere le nom du niveauu
            if(Home.lvlName !=null){
                String[] nomFichier = (Home.lvlName.split("\\\\"));
		if(nomFichier.length == 1){
                    nomFichier = (Home.lvlName.split("/"));
                }
                e = new JLabel(nomFichier[1]);
            }
            else {
                e = new JLabel("Reprise");
            }
        }
        else {
            e = new JLabel("Niveau random");
        }
        e.setFont(new Font("Verdana",1,26));
        levelName.add(e);
        this.add(levelName);
        JPanel petLeft = new JPanel();
        petLeft.setBounds(93,5,62,64);
        //JLabel petsL = new JLabel("4");
        petsLabel.setFont(new Font("Verdana",1,40));
        petLeft.add(petsLabel);
        this.add(petLeft);
        Bot.setBounds(675,420,100,60);
        Bot.setIcon(new ImageIcon("Textures/Robot.jpg"));
        Hammer.setBounds(675,480,100,60);
        Hammer.setIcon(new ImageIcon("Textures/Hammer.jpg"));
        Firework.setBounds(675,540,100,60);
        Firework.setIcon(new ImageIcon("Textures/Firework.jpg"));
        this.add(Hammer);
        this.add(Firework);
        this.add(Bot);
        setButtonsListeners();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Home home = new Home();
                home.createAndShowGUI();
                dispose();
            }
        });
    }

    private void setButtonsListeners() {//On change la valeur pour savoir quel bonus est utilisé dans la grille
        Bot.addActionListener(e -> ValButton = 1);
        Hammer.addActionListener(e -> ValButton = 2);
        Firework.addActionListener(e -> ValButton = 3);
    }

    public static GameBoxLevel getBox(){
        return new GameBoxLevel();
    }

    public static void updateScore() {
        jlabel.setText(String.valueOf(CaseGame.ScoreSessActual));
    }
    public CaseGame[][] getCaseGame(){
        return principale.tab;
    }

}
