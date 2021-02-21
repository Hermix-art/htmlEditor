package app.listeners;

import app.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class UndoMenuListener implements MenuListener {
    private View view;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;


    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem){
        this.view = view;
        this.redoMenuItem = redoMenuItem;
        this.undoMenuItem = undoMenuItem;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        redoMenuItem.setEnabled(view.canRedo());
        undoMenuItem.setEnabled(view.canUndo());
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
