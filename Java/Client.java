import java.io.*;
import java.net.Socket;

public class Client {

    /**
     * This method name and parameters must remain as-is
     */
    public static int add(int lhs, int rhs) {
        return sendRequest("ADD", lhs, rhs);
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static int divide(int num, int denom) {
        return sendRequest("DIVIDE", num, denom);
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) {
        return sendRequest("ECHO", message);
    }

    private static int sendRequest(String method, int... args) {
        try (Socket sock = new Socket("localhost", PORT);
             PrintWriter output = new PrintWriter(sock.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {
    
            output.println(method);
            for (int arg : args) {
                output.println(arg);
            }
    
            String response = input.readLine();
    
            if (method.equals("DIVIDE") && response.equals("ArithmeticException")) {
                throw new ArithmeticException();
            }
    
            return Integer.parseInt(response);
        } catch (IOException ex) {
            System.err.println("Error connecting to the server: " + ex.getMessage());
            return -1;
        }
    }
    
    private static String sendRequest(String method, String message) {
        try (Socket sock = new Socket("localhost", PORT);
             PrintWriter output = new PrintWriter(sock.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {

            output.println(method);
            output.println(message);

            return input.readLine();
        } catch (IOException ex) {
            System.err.println("Error connecting to the server: " + ex.getMessage());
            return "";
        }
    }


    // Do not modify any code below this line
    // --------------------------------------
    String server = "localhost";
    public static final int PORT = 10314;

    public static void main(String... args) {
        // All of the code below this line must be uncommented
        // to be successfully graded.
        System.out.print("Testing... ");

        if (add(2, 4) == 6)
            System.out.print(".");
        else
            System.out.print("X");

        try {
            divide(1, 0);
            System.out.print("X");
        }
        catch (ArithmeticException x) {
            System.out.print(".");
        }

        if (echo("Hello").equals("You said Hello!"))
            System.out.print(".");
        else
            System.out.print("X");
        
        System.out.println(" Finished");
    }
}