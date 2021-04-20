import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class Home {
    JPanel cards;
    private static final JFrame frame = new JFrame("Pet Rescue Java");
    private final JButton jouer = new JButton("Jouer");
    private final JButton reprendre = new JButton("Reprendre partie");
    private final JButton quitter = new JButton("Quitter");
    private final JButton RandomGrid = new JButton("Grille Random");
    private final JButton levelEditor = new JButton("Editeur de niveaux");
    private final JButton custom = new JButton("Jouer au custom");
    protected static String lvlName;
    private GameBoxLevel game;

    public void setLvlName(String s){
        lvlName = s;
    }

    public void addComponentToPane(Container pane) {
        jouer.setBounds(150,40,150,50);
        frame.add(jouer);
        reprendre.setBounds(150,100,150,50);
        frame.add(reprendre);
        custom.setBounds(150,160,150,50);
        frame.add(custom);
        levelEditor.setBounds(150,220,150,50);
        frame.add(levelEditor);
        RandomGrid.setBounds(150,280,150,50);
        frame.add(RandomGrid);
        quitter.setBounds(150,340,150,50);
        frame.add(quitter);
        frame.getContentPane().setBackground(Color.orange);
        quitter.addActionListener(e -> System.exit(0));

        custom.addActionListener(e -> {
            frame.dispose();
            new HomeLevelEdited();
        });

        RandomGrid.addActionListener(e -> EventQueue.invokeLater(() -> {
            game = GameBoxLevel.getBox();
            game.setVisible(true);
            frame.dispose();
        }));
        levelEditor.addActionListener(e -> {
            GameBoxLevelEditor principale = GameBoxLevelEditor.getBox();
            principale.setVisible(true);
            frame.dispose();
        });
        jouer.addActionListener(e -> {
            frame.dispose();
            new HomeLevelChoice();
        });

        reprendre.addActionListener(e -> {
            ObjectInputStream ois = null;
            try {
                final FileInputStream fichier = new FileInputStream("data/data.ser");
                ois = new ObjectInputStream(fichier);

                final GameBoxLevel main = new GameBoxLevel((CaseGame[][]) ois.readObject()) ;
                //frame.setVisible(false);
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
        });
    }
    public void createAndShowGUI() {
        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Textures/BackgroundINGAME.jpg")))));
        }
        catch (Exception e){
            System.out.println("L'IMAGE BackgroundINGAME.jpg n'est pas dispo veuillez vérifier le dossier Textures");
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("Textures/IconGame.png");
        frame.setIconImage(icon);
        Home container = new Home();
        container.addComponentToPane(frame.getContentPane());
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(450, 450);
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);
    }
}