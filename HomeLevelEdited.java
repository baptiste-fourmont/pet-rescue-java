import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class HomeLevelEdited extends JFrame implements ItemListener {
    JPanel cards;
    private JButton Precedent = new JButton("Precedent");
    private JButton Custom = new JButton("Jouer");
    private JPanel fenetre = new JPanel(); // Pour la fenetre qui va contenir les boutons



    public HomeLevelEdited() {
        fenetre.setSize(200,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("Textures/IconGame.png");
        this.setIconImage(icon); // On set l'icone de la fenêtre
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); // On récupère la dimension de l'écran de l'user pour trouver le milieu
        this.setSize(450, 450);
        setLevelList();

        Precedent.setBounds(100, 400, 50, 50);
        Custom.setBounds(0, 0, 50, 50);
        //fenetre.add(Custom);
        //fenetre.add(Precedent);
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);     // On place la fenêtre au milieu de l'écran de l'utilisateur
        this.add(fenetre);
        this.setVisible(true);

        Precedent.addActionListener(e -> {//Le listener du bouton qui permet de fermer cette fenêtre et réouvrir l'accueil quand on clique dessus
         Home home = new Home();
         home.createAndShowGUI();
         dispose();
         });
    }

    @Override
    public void itemStateChanged(ItemEvent evt) {
        try{
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, String.valueOf(evt.getItem()));
        }catch (Exception ignored){
        }
    }

    public void setLevelList(){ // On récupère la liste des fichiers dans custom pour les afficher dans le menu déroulant via serializable
        JPanel comboBoxPane = new JPanel();
        File f = new File("custom/");
        File[] paths = f.listFiles();
        String[] comboBoxItems = new String[0];
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        for (File path : paths != null ? paths : new File[0]) {
            cb.addItem(path);
        }
        fenetre.add(Precedent);
        fenetre.add(Custom);
        fenetre.add(comboBoxPane);
        this.add(fenetre);
        Custom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // Si On clique sur le bouton jouer alors on lance le niveau du nom choisi dans le menu déroulant cb
                ObjectInputStream ois = null;
                try {
                    final FileInputStream fichier = new FileInputStream((String)cb.getSelectedItem().toString());
                    ois = new ObjectInputStream(fichier);
                    Home.lvlName = (String) cb.getSelectedItem().toString();
                    final GameBox main;
                    main = new GameBoxLevel((CaseGame[][]) ois.readObject());
                    main.setVisible(true);
                    dispose();
                    main.setVisible(true);
                } catch (final IOException | ClassNotFoundException exc) {
                    exc.printStackTrace();
                } finally {
                    try {
                        if (ois != null) {
                            ois.close();
                        }
                    } catch (final IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

}
