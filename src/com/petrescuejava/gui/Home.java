/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR


/*
 * CardLayoutDemo.java
 *
 */
package com.petrescuejava.gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Home extends JFrame implements ItemListener, ActionListener {
    JPanel container = new JPanel();
    JPanel cards;
    private static JFrame frame = new JFrame("Pet Rescue Java");
    private JButton jouer = new JButton("Jouer");
    private JButton reprendre = new JButton("Reprendre partie");
    private JButton quitter = new JButton("Quitter");



    public void addComponentToPane(Container pane) {
        JPanel comboBoxPane = new JPanel();



        File f = new File("jeu/");
        File []paths = f.listFiles();
        String comboBoxItems[] = new String[0];
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        for(File path: paths) {
            cb.addItem(path);
        }


        JPanel card1 = new JPanel();
        card1.add(jouer);
        card1.add(reprendre);
        card1.add(quitter);

        quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jouer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ObjectInputStream ois = null;

                try {

                    final FileInputStream fichier = new FileInputStream((String)cb.getSelectedItem().toString());
                    ois = new ObjectInputStream(fichier);
                    final Grille main = (Grille) ois.readObject();
                    main.getGrille();
                    frame.setVisible(false);
                    main.setVisible(true);
                } catch (final java.io.IOException exc) {
                    exc.printStackTrace();
                } catch (final ClassNotFoundException exc) {
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

        reprendre.addActionListener(e -> {
            ObjectInputStream ois = null;

            try {
                final FileInputStream fichier = new FileInputStream("data/niveau.ser");
                ois = new ObjectInputStream(fichier);
                final CaseGame[][] tab = (CaseGame[][]) ois.readObject();
                Grille principale = new Grille();

            } catch (final IOException exc) {
                exc.printStackTrace();
            } catch (final ClassNotFoundException exc) {
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




        jouer.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if(cb.getSelectedItem() != null) {
                    jouer.setBackground(Color.GREEN);
                }else{
                    jouer.setBackground(Color.RED);
                }
            }

            public void mouseExited(MouseEvent evt) {
                jouer.setBackground(UIManager.getColor("control"));
            }
        });

        cards = new JPanel(new CardLayout());
        cards.add(card1);
        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }

    public void itemStateChanged(ItemEvent evt) {
        try{
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, String.valueOf(evt.getItem()));
        }catch (Exception e){
        }
    }

    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Home container = new Home();
        container.addComponentToPane(frame.getContentPane());
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(450, 450);
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.pack();
        frame.setVisible(true);
        //this.add(frame);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}