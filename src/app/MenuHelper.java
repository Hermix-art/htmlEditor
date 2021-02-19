package app;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuHelper {
    public static JMenuItem addMenuItem(JMenu parent, String text, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(text); //new Item in menu
        menuItem.addActionListener(actionListener); // adding name to new Item

        parent.add(menuItem);// adding Item to menu

        return menuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, Action action) {
        JMenuItem menuItem = new JMenuItem(action);
        parent.add(menuItem);

        return menuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, String text, Action action) {
        JMenuItem menuItem = addMenuItem(parent,action);
        menuItem.setText(text);

        parent.add(menuItem);

        return menuItem;
    }

    public static void initFontMenu(View view, JMenuBar menuBar) {
    }

    public static void initHelpMenu(View view, JMenuBar menuBar) {
    }

    public static void initColorMenu(View view, JMenuBar menuBar) {
    }

    public static void initAlignMenu(View view, JMenuBar menuBar) {
    }

    public static void initStyleMenu(View view, JMenuBar menuBar) {
    }

    public static void initEditMenu(View view, JMenuBar menuBar) {
    }

    public static void initFileMenu(View view, JMenuBar menuBar) {
    }
}
