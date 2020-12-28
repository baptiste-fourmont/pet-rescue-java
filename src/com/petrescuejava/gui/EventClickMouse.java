package com.petrescuejava.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventClickMouse implements ActionListener {
    private CaseGame source;

    public EventClickMouse(CaseGame c) {
        this.source = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
