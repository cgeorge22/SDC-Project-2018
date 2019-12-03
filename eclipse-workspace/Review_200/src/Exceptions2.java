public class Exceptions2 {
    public static String getInput(int in) throws Exception {
        if (in == 0) {
            return "abc";
        } else {
            throw new Exception(" exception message");
        }
    }

    public static void main(String[] args) throws Exception {
        String command = null;
        try {
            command = getInput(0); // also pass 0
            System.out.println("command:" + command);
        } catch (Exception e) {
            System.out.println("invalid input: " + e.getMessage());
        } finally {
            // If command is null the following will throw an exception.
            // What happens when this exception is thrown?
            System.out.println(command.toString());
        }
        System.out.println("end of main");
    }
}
