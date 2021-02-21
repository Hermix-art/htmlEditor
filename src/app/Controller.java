package app;

import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Controller {
    private View view;
    private HTMLDocument document; // model
    private File currentFile;

    public HTMLDocument getDocument() {
        return document;
    }

    public Controller(View view){
        this.view = view;
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);

        view.init();
        controller.init();
    }

    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }

        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        document = (HTMLDocument) htmlEditorKit.createDefaultDocument();

        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text){ // writes text with HTML tags to document
        resetDocument();
        StringReader reader =  new StringReader(text);

        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.read(reader, document,0);
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }

    }
    public String getPlainText(){// reads text with HTML tags from document
        StringWriter writer = new StringWriter();

        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.write(writer, document,0,document.getLength());
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }

    public void init(){

    }
    public void exit(){
        System.exit(0);
    }
}
