package base;

import java.io.File;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

public class TextNote extends Note implements Serializable {
    String content;

    public TextNote(String title) {
        super(title);
    }

    public TextNote(String title, String content) {
        super(title);
        this.content = content;
    }

    public TextNote(File f) {
        super(f.getName());
        this.content = getTextFromFile(f.getAbsolutePath());
    }

    /**
     * get the content of a file
     *
     * @param absolutePath of the file
     * @return the content of the file
     */
    private String getTextFromFile(String absolutePath) {
        StringBuilder result = new StringBuilder();
        try {
            File file = new File(absolutePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                result.append(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result.toString();
    }

    public void exportTextToFile(String pathFolder) {
        try {
            if(!pathFolder.equals(""))
                pathFolder += File.separator;
            File file = new File( pathFolder + getTitle().replaceAll(" ", "_") + ".txt");
            PrintWriter writer = new PrintWriter(file);
            writer.print(content);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
