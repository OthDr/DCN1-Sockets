package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    public static void main(String[] args) throws IOException {
        
        Socket clientSocket = null;
        
        int port = ServConfig.PORT;
        
        ServerSocket server = new ServerSocket(port);
        
        clientSocket = server.accept();
        
        if(clientSocket != null){
            System.out.println("Connection successfully established with: "+clientSocket.getInetAddress());
        }
        
        
    }
}
