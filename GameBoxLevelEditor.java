import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GameBoxLevelEditor extends GameBox{
    JButton Col1 = new JButton("Rouge"); // Buttons pour les couleurs
    JButton Col2 = new JButton("Violet");
    JButton Col3 = new JButton("Vert");
    JButton Col4 = new JButton("Bleu");
    JButton Col5 = new JButton("Jaune");
    JButton Col6 = new JButton("Orange");
    JButton Col7 = new JButton("Animal1");
    JButton Col8 = new JButton("Animal2");
    JButton Col9 = new JButton("Obstacle1");
    JButton Col10 = new JButton("Obstacle2");
    JButton Col11 = new JButton("Gomme");
    JButton save = new JButton("Save");
    JTextField nom = new JTextField("LvlName");
    protected static CaseEditor[][] tab = new CaseEditor[10][10];
    private CaseGame[][]tabsave = new CaseGame[10][10];
    JPanel grid = new JPanel();
    public GameBoxLevelEditor(){
        super();
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Textures/BackgroundLevel.jpg")))));
        } catch (IOException e) {
            System.out.println("L'IMAGE BackgroundLevel.jpg n'est pas dispo veuillez vérifier le dossier Textures");
        }
        this.setTitle("Pet Rescue Java");
        grid.setLayout(new GridLayout(10, 10));
        fillpanel();
        addButtons();
        grid.setVisible(true);
        grid.setBounds(150,150,500,500);
        this.add(grid);
        this.setTitle("Pet Rescue Java");
        updateEvents();
        SaveLevel();

    }
    public void addButtonEditor(int x,int y,int w, int h, JButton button, Color color){
        button.setBounds(x,y,w,h);
        button.setBackground(color);
        this.add(button);
    }
    public void addButtons(){
        addButtonEditor(0,100,100,50, Col1,new Color(130, 27, 27));
        addButtonEditor(0,150,100,50, Col2,new Color(92, 36, 179));
        addButtonEditor(0,200,100,50, Col3,new Color(36, 180, 25));
        addButtonEditor(0,250,100,50, Col4,new Color(54, 91, 229));
        addButtonEditor(0,300,100,50, Col5,new Color(255, 253, 58));
        addButtonEditor(0,350,100,50, Col6,new Color(253, 135, 16));
        addButtonEditor(0,400,100,50, Col7,Color.WHITE);
        addButtonEditor(0,450,100,50, Col8,Color.WHITE);
        addButtonEditor(0,500,100,50, Col9,Color.WHITE);
        addButtonEditor(0,550,100,50, Col10,Color.WHITE);
        addButtonEditor(0,600,100,50, Col11,Color.WHITE);
        nom.setBounds(250,50,250,50);
        nom.setBackground(Color.WHITE);
        this.add(nom);
        save.setBounds(500,50,100,50);
        save.setBackground(Color.RED);
        this.add(save);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) { // Si on ferme la fenetre on réouvre le launcher
                Home home = new Home();
                home.createAndShowGUI();
                dispose();
            }
        });
    }

    public static void updateGrille(int w,int x,int y) { // On set le tableau de sauvegarde partout dans toutes les cases
        for(int i =0;i<10;i++){
            for(int j = 0;j<10;j++){
                if(w == 1){
                    tab[i][j].save[x][y].setCaseInfos(0,0);
                }
                if(w == 2){
                    tab[i][j].save[x][y].setCaseInfos(0,1);
                }
                if(w == 3){
                    tab[i][j].save[x][y].setCaseInfos(0,2);
                }
                if(w == 4){
                    tab[i][j].save[x][y].setCaseInfos(0,3);
                }
                if(w == 5){
                    tab[i][j].save[x][y].setCaseInfos(0,4);
                }
                if(w == 6){
                    tab[i][j].save[x][y].setCaseInfos(0,5);
                }
                if(w == 7){
                    tab[i][j].save[x][y].setCaseInfos(1,0);
                }
                if(w == 8){
                    tab[i][j].save[x][y].setCaseInfos(1,1);
                }
                if(w == 9){
                    tab[i][j].save[x][y].setCaseInfos(2,0);
                }
                if(w == 10){
                    tab[i][j].save[x][y].setCaseInfos(2,1);
                    tab[i][j].save[x][y].setPresent(false);
                }
                if(w == 11){
                    tab[i][j].save[x][y].setCaseInfos(3,0);
                    tab[i][j].save[x][y].setPresent(false);
                }
            }
        }
    }

    public static GameBoxLevelEditor getBox(){
        return new GameBoxLevelEditor();
    }
    public void fillpanel(){ // On remplit le panel de cases vides pour éviter les nullPointer
        for(int i = 0;i<10;i++){
            for(int j = 0;j<10;j++){
                this.tab[i][j] = randompanel();
                this.tabsave[i][j] = new CaseGame(0,1);
            }
        }
        for(int i =0;i<10;i++){
            for(int j =0;j<10;j++){
                tab[i][j].setTAB(tab);
                tab[i][j].fillpanel();
                //this.tabsave[i][j].setTAB(tab);
                grid.add(tab[i][j]);
            }
        }
    }
    public CaseEditor randompanel(){
        CaseEditor paneli = new CaseEditor(0,1);
        paneli.setBackground(Color.WHITE);
        return paneli;
    }
    public void changeValEditor(int k){
        for(int i =0;i<10;i++){
            for(int j =0;j<10;j++){
                tab[i][j].setValue(k);
            }
        }
    }

    private void SaveLevel(){ // Lorsqu'on clique sur sauvegarder on écrit un nouveau fichier avec le nom entré dans la zone indiquée
        save.addActionListener(e -> {
            ObjectOutputStream oos = null;
            try {
                final FileOutputStream fichier = new FileOutputStream("custom/"+nom.getText());
                oos = new ObjectOutputStream(fichier);
                oos.writeObject(CaseEditor.save);
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
        });

    }
    private void updateEvents() { // Pour chaque bouton cliqué on change la valeur dans la classe CaseEditor pour pouvoir editer
        Col1.addActionListener(e -> changeValEditor(1));
        Col2.addActionListener(e -> changeValEditor(2));
        Col3.addActionListener(e -> changeValEditor(3));
        Col4.addActionListener(e -> changeValEditor(4));
        Col5.addActionListener(e -> changeValEditor(5));
        Col6.addActionListener(e -> changeValEditor(6));
        Col7.addActionListener(e -> changeValEditor(7));
        Col8.addActionListener(e -> changeValEditor(8));
        Col9.addActionListener(e -> changeValEditor(9));
        Col10.addActionListener(e -> changeValEditor(10));
        Col11.addActionListener(e -> changeValEditor(11));
    }

}
