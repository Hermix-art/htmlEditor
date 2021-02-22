package app;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        String name = f.getName().toLowerCase();
        boolean a = name.endsWith(".html");
        boolean b = name.endsWith(".htm");

        return f.isDirectory() || a || b;
    }

    @Override
    public String getDescription() {
        return "HTML and HTM files";
    }
}
