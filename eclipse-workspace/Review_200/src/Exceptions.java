import java.io.IOException;

public class Exceptions {
    static String getFilename() {
        // Try these situations:
         return null; //1
        // return ""; //2
        //return "abc"; // 3
    }

    static void processFile(String filename) throws IOException, Exception {
        if (filename == null) {
            throw new IOException("null filename");
        } else if (filename.length() == 0) {
            throw new Exception("0 length filename");
        }
        System.out.println("processing file...");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("beginning of main");
        try {
            System.out.println("before processFile");
            String filename = getFilename();
            processFile(filename);
            System.out.println("after processFile");
        } catch (IOException e) {
            System.out.println("catch IOException");
        } catch (Exception e) {
            System.out.println("catch Exception");
            throw e;
        } finally {
            System.out.println("in finally");
        }
        System.out.println("end of main");
    }
}
