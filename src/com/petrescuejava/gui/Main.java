package com.petrescuejava.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        Home home = new Home();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                home.createAndShowGUI();
            }
        });


  /**              EventQueue.invokeLater(() -> {
                    Grille principale = Grille.getGrille();
                    principale.setVisible(true);
                    ObjectOutputStream oos = null;

                    try {
                        final FileOutputStream fichier = new FileOutputStream("jeu/niveau50.ser");
                        oos = new ObjectOutputStream(fichier);
                        oos.writeObject(principale);
                        oos.flush();
                    } catch (final java.io.IOException e) {
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
                });



  /**          ObjectOutputStream oos = null;

            try {
                final FileOutputStream fichier = new FileOutputStream("jeu/niveau3.ser");
                oos = new ObjectOutputStream(fichier);
                oos.writeObject(principale);
                oos.flush();
            } catch (final java.io.IOException e) {
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

        });
        /**
        Home home = new Home();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                home.createAndShowGUI();
            }
        });
        **/



    }
}