import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public abstract class GameBox extends JFrame { // Cette JFrame sera la fenêtre principale contenant tous les élèments comme la grille contenant les cases
    JPanel score = new JPanel(); // On initialise le socre ici
    public GameBox(){
        Image icon = Toolkit.getDefaultToolkit().getImage("Textures/IconGame.png"); // Icon de l'app
        this.setIconImage(icon);
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Textures/BackgroundINGAME.jpg"))))); //Background de la frame
        }
        catch (Exception e){
            System.out.println("L'IMAGE BackgroundINGAME.jpg n'est pas dispo veuillez vérifier le dossier Textures");
        }
        score.setBounds(0,0,800,150);
        JLabel scoreval = new JLabel("Dany");
        scoreval.setBounds(0,0,800,150);
        score.add(scoreval);
        score.setBackground(Color.PINK);
        this.setLayout(null);
        this.setSize(800,735);
        this.setVisible(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

    }
}
