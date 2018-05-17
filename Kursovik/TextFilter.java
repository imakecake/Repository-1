import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;
//фильтр файлов *.txt, *.bd  
public class TextFilter extends FileFilter {
      public boolean accept(File f) {
        if (f.isDirectory()){return true;}  
        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.txt)||
                extension.equals(Utils.bd)){return true;} 
                else {return false;}
        }
        return false;
    }

    public String getDescription(){return "Текстовые файлы";}//Описание фильтра
}
