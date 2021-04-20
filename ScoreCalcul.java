import java.io.*;

public class ScoreCalcul {
    public static int readScore() { // Cette methode va utiliser serializable pour récupérer le score dans le fichier socre afin de le lire dans CaseGame
        ObjectInputStream ois = null;
        try {
            final FileInputStream fichier = new FileInputStream("jeu/score");
            ois = new ObjectInputStream(fichier);
            int a = (int) ois.readObject();
            return a;
        }
        catch (Exception e){
            System.out.println("Erreur lors de la lecture du fichier score");
        }
        return 0;
    }

    public static void writeScore(int score) {  // Cette methode va réecrire l'ancien score au nouveau obtenu dans casegame dans le fichier score
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream("jeu/score"); // On save ici le nouveau Integer dans le fichier score
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(score);
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
    }
}
