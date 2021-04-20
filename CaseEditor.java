import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaseEditor extends CaseGame implements ActionListener {
    private static int x =0;    // Cette classe ressemble fortement à casegame sur certains points afin de fonctionner sur le même systeme
    private static int y =0;
    private boolean present = true; // Pour savoir si la case est vide ou non
    private final int valx;
    private final int valy;
    private int type;
    private int color;
    private int valueEDITOR; //Cette valeur servira à récupérer la valeur dans GameBoxLevelEditor afin de savoir quelle couleur utiliser
    private CaseEditor[][] tabedit;
    private JButton button = new JButton();
    protected static CaseGame[][] save = new CaseGame[10][10];  // Ce tableau sauvegarde tout en CaseGame pour pouvoir le réutiliser plus tard comme un lvl
    public CaseEditor(int type,int color){
        super(type,color); // On utilise donc le même constructeur
        this.type =type;
        this.color = color;
        this.valx = x;
        this.valy = y;
        if(y<9){
            y++;
        }
        else if(y==9){
            x++;
            y=0;
        }
        if(x > 9){
            x=0;
        }
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setLayout(new BorderLayout(0, 0));
        button.setName("HI");
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
        this.add(button);
        this.setBorder(blackline);  // On créee la boite pour build
    }

    private void fillBlankEditor() { //On remplit tout en blanc afin d'éviter les nullpointerexception
        for(int i =0;i<10;i++){
            for(int j = 0;j<10;j++){
                CaseGame blank = new CaseGame(3,0);
                blank.setBackground(Color.white);
                save[i][j] = blank;
            }
        }
    }

    public void setTAB(CaseEditor[][] tab){
        this.tabedit = tab;
    }
    public void setValue(int i){
        this.valueEDITOR = i;
    }
    public void ChangeItem(int i){  //Cette fonction va changer la casegame et ses infos selon la valeur de valueEditor et affectera donc
                                    //la case aux valeurs choisies(Type d'objet couleurs...) par l'utilisateur dans la vue courante et dans la save pour le niveau
        if(i ==1){
            tabedit[valx][valy].setBackground(new Color(130, 27, 27));
            GameBoxLevelEditor.updateGrille(1,valx,valy);
            tabedit[valx][valy].type = 0;
            save[valx][valy].setType(0,0);
            save[valx][valy].setCaseInfos(0,0);
        }
        if(i ==2){
            tabedit[valx][valy].setBackground(new Color(92, 36, 179));
            GameBoxLevelEditor.updateGrille(2,valx,valy);
            tabedit[valx][valy].type = 0;
            save[valx][valy].setType(0,1);
            save[valx][valy].setCaseInfos(0,1);

        }
        if(i==3){
            tabedit[valx][valy].setBackground(new Color(36, 180, 25));
            GameBoxLevelEditor.updateGrille(3,valx,valy);
            tabedit[valx][valy].type = 0;
            save[valx][valy].setType(0,2);
            save[valx][valy].setCaseInfos(0,2);
        }
        if(i==4){
            tabedit[valx][valy].setBackground(new Color(54, 91, 229));
            GameBoxLevelEditor.updateGrille(4,valx,valy);
            tabedit[valx][valy].type = 0;
            save[valx][valy].setType(0,3);
            save[valx][valy].setCaseInfos(0,3);
        }
        if(i==5){
            tabedit[valx][valy].setBackground(new Color(255, 253, 58));
            GameBoxLevelEditor.updateGrille(5,valx,valy);
            tabedit[valx][valy].type = 0;
            save[valx][valy].setType(0,4);
            save[valx][valy].setCaseInfos(0,4);
        }
        if(i==6){
            tabedit[valx][valy].setBackground(new Color(253, 135, 16));
            GameBoxLevelEditor.updateGrille(6,valx,valy);
            tabedit[valx][valy].type = 0;
            save[valx][valy].setType(0,5);
            save[valx][valy].setCaseInfos(0,5);
        }
        if(i==7){
            tabedit[valx][valy].setBackground(Color.PINK);
            GameBoxLevelEditor.updateGrille(7,valx,valy);
            tabedit[valx][valy].type = 1;
            save[valx][valy].setType(1,0);
            save[valx][valy].setCaseInfos(1,0);
        }
        if(i==8){
            tabedit[valx][valy].setBackground(Color.PINK);
            GameBoxLevelEditor.updateGrille(8,valx,valy);
            tabedit[valx][valy].type = 1;
            save[valx][valy].setType(1,1);
            save[valx][valy].setCaseInfos(1,1);
        }
        if(i==9){
            tabedit[valx][valy].setBackground(Color.BLACK);
            GameBoxLevelEditor.updateGrille(9,valx,valy);
            tabedit[valx][valy].type = 2;
        }
        if(i==10){
            tabedit[valx][valy].setBackground(new Color(121, 75, 27));
            GameBoxLevelEditor.updateGrille(10,valx,valy);
            tabedit[valx][valy].type = 2;
        }
        if(i==11){
            tabedit[valx][valy].setBackground(Color.WHITE);
            GameBoxLevelEditor.updateGrille(11,valx,valy);
            tabedit[valx][valy].type = 0;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) { // Si on clique sur une case , alors changer cette case en ValueEditor
        ChangeItem(valueEDITOR);
    }
    public CaseGame randompanel(){ //Cette fonction est utile comme fillpanel() pour remplir les tableaux par defaut uniquement pour éviter les nullpointer lorsque nous changeons les valeurs des cases
        CaseGame paneli = new CaseGame(3,1);
        paneli.setBackground(Color.WHITE);
        return paneli;
    }
    public void fillpanel(){ //
        for(int i = 0;i<10;i++){
            for(int j = 0;j<10;j++){
                this.save[i][j] = randompanel();
            }
        }
        for(int i = 0;i<10;i++){
            for(int j = 0;j<10;j++){
                this.save[i][j].setTAB(save);
            }
        }
    }
}
