import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {

    public static final int PORT = 10314;
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String method = input.readLine();
                    switch (method) {
                        case "ADD": {
                            int lhs = Integer.parseInt(input.readLine());
                            int rhs = Integer.parseInt(input.readLine());
                            output.println(add(lhs, rhs));
                            break;
                        }
                        case "DIVIDE": {
                            int num = Integer.parseInt(input.readLine());
                            int denom = Integer.parseInt(input.readLine());
                            try {
                                output.println(divide(num, denom));
                            } catch (ArithmeticException ex) {
                                output.println("ArithmeticException");
                            }
                            break;
                        }
                        case "ECHO": {
                            String message = input.readLine();
                            output.println(echo(message));
                            break;
                        }
                        default:
                            break;
                    }
                } 
            }
        } catch (IOException ex) {
            
        }
    }

    // Do not modify any code below tihs line
    // --------------------------------------
    public static String echo(String message) { 
        return "You said " + message + "!";
    }
    public static int add(int lhs, int rhs) {
        return lhs + rhs;
    }
    public static int divide(int num, int denom) {
        if (denom == 0)
            throw new ArithmeticException();

        return num / denom;
    }
}