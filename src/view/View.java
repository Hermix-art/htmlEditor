package view;

import controller.Controller;
import listeners.FrameListener;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class View extends JFrame implements ActionListener {
    private HTMLDocument document;
    private File currentFile;
    private Controller controller;

    JTabbedPane tabbedPane = new JTabbedPane();
    JTextPane textPane = new JTextPane();
    JEditorPane editorPane = new JEditorPane();

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    public void exit() {
        controller.exit();
    }
    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);

        setVisible(true);
    }

    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged(){

    }

    public void initMenuBar(){

    }
    public void initEditor(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
