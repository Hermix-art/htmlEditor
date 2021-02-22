package app;

import app.listeners.FrameListener;
import app.listeners.TabbedPaneChangeListener;
import app.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;

    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    public Controller getController() {
        return controller;
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "New":
                controller.createNewDocument();
                break;
            case "Open":
                controller.openDocument();
                break;
            case "Save":
                controller.saveDocument();
                break;
            case "Save as...":
                controller.saveDocumentAs();
                break;
            case "Exit":
                controller.exit();
                break;
            case "About":
                showAbout();
                break;
        }
    }

    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);

        setVisible(true);
    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);

        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane scrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML", scrollPane); // adding 1st tab

        JScrollPane scrollPane1 = new JScrollPane(plainTextPane);
        tabbedPane.add("Text", scrollPane1);// adding 2nd tab

        tabbedPane.setPreferredSize(new Dimension(600, 300));

        TabbedPaneChangeListener listener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(listener);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void selectedTabChanged() { //invokes when tab changes
        int selectedTab = tabbedPane.getSelectedIndex(); // when 1st tab: uploading text to html, using controller
        if (selectedTab == 0) {
            String text = plainTextPane.getText();
            controller.setPlainText(text);
        } else if (selectedTab == 1) {// when 2st tab: uploading text to text tab, using controller
            String text = controller.getPlainText();
            plainTextPane.setText(text);
        }
        resetUndo();

    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void undo() {
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected() {
        int currentTab = tabbedPane.getSelectedIndex();
        return currentTab == 0;
    }

    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);// choosing html tab
        resetUndo();
    }

    public void update() {
        HTMLDocument document = controller.getDocument();//getting data from Model
        htmlTextPane.setDocument(document);//setting HTML tab with data
    }

    public void showAbout() {
        JOptionPane.showMessageDialog(tabbedPane, "HTML editor by Hermix-art \u00a9",
                "About", JOptionPane.INFORMATION_MESSAGE);
    }

    public void exit() {
        controller.exit();
    }
}
