import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Attributes {
    public static void main(String[] args) {
        File file = new File("../../eclipse-workspace/Eliza_200/src/Eliza.java");
        System.out.println("exists: " + file.exists());
        System.out.println("absolute path: " + file.getAbsolutePath());
        System.out.println("relative path: " + file.getPath());
        System.out.println("parent: " + file.getParent());
        System.out.println("length: " + file.length());
        System.out.println("isDirectory: " + file.isDirectory());
        System.out.println("isFile: " + file.isFile());
        System.out.println("canRead: " + file.canRead());
        
        
        String outFileName = "myData.txt";
        PrintWriter writer = null;
        try {
        writer = new PrintWriter(outFileName);
        for ( int i = 1; i <= 9; i++) {
        writer.printf("%d, ", i * i);
        }
        } catch (IOException e) {
        System.err.println("Unable to write to " + outFileName);
        } finally {
        if (writer != null) writer.close();
        }
    }
}
