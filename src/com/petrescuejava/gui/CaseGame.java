package com.petrescuejava.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class CaseGame extends JPanel implements ActionListener{
    private static int x =0;
    private static int y =0;
    private boolean present = true; // Pour savoir si la case est vide ou non
    private int valx;
    private int valy;
    private CaseGame[][] tabcase;


    public CaseGame(){
        super();
        this.valx = x;
        this.valy = y;
        if(y<9){
           y++;
        }
        else if(y==9){
            x++;
            y=0;
        }
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setLayout(new BorderLayout(0, 0));
        JButton button = new JButton();
        button.setName("HI");
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(this);
        this.add(button);
        this.setBorder(blackline);
    }
    public void setTAB(CaseGame[][] tab){
        this.tabcase = tab;
    }
    public void afficheTAB(){
        for(int i =0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print(tabcase[i][j].getBackground().toString()+" ");
            }
            System.out.println();
        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        checkCase(valx,valy,0);
        updatetab();
        afficheTAB();

        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichier = new FileOutputStream("data/niveau.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(this);
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
    }

    private void updatetab() {
        int k = 1;
        int w = 0;
        while (w==0){
            k = 0;
            for(int i = 9;i>0;i--){
                for(int j =9;j>=0;j--){
                    if(!tabcase[i][j].present && tabcase[i-1][j].present){
                        CaseGame tmp  = tabcase[i][j];
                        tabcase[i][j].setBackground(tabcase[i-1][j].getBackground());
                        tabcase[i][j].present = true;
                        deleteCase(i-1,j);
                        k++;
                    }
                }
            }
            if(k==0){
                w=1;
            }
        }
    }
    public boolean isLost(){
        //for(int i =0)
        return true;
    }


    public void deleteCase(int cx, int cy){
        tabcase[cx][cy].setBackground(Color.white);
        tabcase[cx][cy].present = false;
    }
    public void checkCase(int cx, int cy,int k){
        int z = 0;
        if(cx == 0 && tabcase[cx+1][cy].present && tabcase[cx+1][cy].getBackground().equals(tabcase[cx][cy].getBackground())){
            z++;
            tabcase[cx][cy].present = false;
            checkCase(cx+1,cy,1);
        }

        if(cx !=0 && tabcase[cx-1][cy].present && tabcase[cx-1][cy].getBackground().equals(tabcase[cx][cy].getBackground())){
            z++;
            tabcase[cx][cy].present = false;
            checkCase(cx-1,cy,1);
        }
        if(cx !=9 && tabcase[cx+1][cy].present && tabcase[cx+1][cy].getBackground().equals(tabcase[cx][cy].getBackground())){
            z++;
            tabcase[cx][cy].present = false;
            checkCase(cx+1,cy,1);
        }
        if(cy != 9 && tabcase[cx][cy+1].present && tabcase[cx][cy+1].getBackground().equals(tabcase[cx][cy].getBackground())){
            z++;
            tabcase[cx][cy].present = false;
            checkCase(cx,cy+1,1);

        }
        if(cy ==0 && tabcase[cx][cy+1].present && tabcase[cx][cy+1].getBackground().equals(tabcase[cx][cy].getBackground())){
            z++;
            tabcase[cx][cy].present = false;
            checkCase(cx,cy+1,1);
        }

        if(cy !=0 && tabcase[cx][cy-1].present && tabcase[cx][cy-1].getBackground().equals(tabcase[cx][cy].getBackground())){
            z++;
            tabcase[cx][cy].present = false;
            checkCase(cx,cy-1,1);
        }

        if(k ==1){
            deleteCase(cx,cy);
        }
        if(z >0 & k==0){
            deleteCase(cx,cy);
        }
    }
}

