package com.petrescuejava.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;

public class Grille extends JFrame{
    JPanel container = new JPanel();
    protected CaseGame[][] tab = new CaseGame[10][10];
    private boolean isDone = false;
    public Grille() {
        this.setTitle("Pet Rescue Java");
        this.setSize(500, 500);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new GridLayout(10, 10));
        fillpanel();
        this.setContentPane(container);
        this.setVisible(true);
    }

    public void fillpanel(){
        for(int i = 0;i<10;i++){
            for(int j = 0;j<10;j++){
                this.tab[i][j] = randompanel();
            }
        }
        for(int i =0;i<10;i++){
            for(int j =0;j<10;j++){
                tab[i][j].setTAB(tab);
                container.add(tab[i][j]);
            }
        }
    }

    public CaseGame randompanel(){
        Color color1 = new Color(196, 255, 206);
        Color color2 = new Color(200, 131, 208);
        Color color3 = new Color(23, 78, 201);
        Color color4 = new Color(230, 73, 95);
        Random random = new Random();
        int k = random.nextInt(4 - 1 + 1) + 1;
        CaseGame paneli = new CaseGame();
        String name = "color"+k;
        if(k==1){
            paneli.setBackground(color1);
        }
        if(k==2){
            paneli.setBackground(color2);
        }
        if(k==3){
            paneli.setBackground(color3);
        }
        if(k==4){
            paneli.setBackground(color4);
        }

        return paneli;
    }
    public void SetWin(){
        this.isDone = true;
    }
    public static Grille getGrille() {
        return new Grille();
    }






}