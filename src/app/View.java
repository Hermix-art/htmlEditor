package app;

import app.listeners.FrameListener;
import app.listeners.TabbedPaneChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    public Controller getController() {
        return controller;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void init(){
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
    public void initMenuBar(){

    }

    public void initEditor(){
        htmlTextPane.setContentType("text/html");
        JScrollPane scrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML", scrollPane); // adding 1st tab

        JScrollPane scrollPane1 = new JScrollPane(plainTextPane);
        tabbedPane.add("Текст", scrollPane1);// adding 2nd tab

        tabbedPane.setPreferredSize(new Dimension(300,300));

        TabbedPaneChangeListener listener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(listener);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    public void selectedTabChanged(){

    }

    public void exit(){
        controller.exit();
    }
}
