import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket;

    private Server(int port) {
        // Create a new server socket
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running on port " + port);
            // Wait for a client to connect
            socket = serverSocket.accept();
            System.out.println("Client connected");
            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";
            // reads message from client until "Over" is sent
            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                } catch (IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
            // close connection
            socket.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 5000;
        // Create a new server socket
        Server server = new Server(port);
    }
}
