import java.io.File;
public class Attributes {
    public static void main(String[] args) {
        File file = new File("src/Attributes.java");
        System.out.println("exists: " + file.exists());
        System.out.println("path: " + file.getPath());
        System.out.println("length: " + file.length());
        System.out.println("isDirectory: " + file.isDirectory());
        System.out.println("isFile: " + file.isFile());
        System.out.println("canRead: " + file.canRead());
    }
}
