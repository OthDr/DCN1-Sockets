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
        System.out.println("-------\n Server is listening on port 3008 \n-------");
        
        clientSocket = server.accept();
        
        if(clientSocket != null){
            System.out.println("\n +++++++++++++++++++++ \n Connection successfully established with: "+clientSocket.getInetAddress() +"\n +++++++++++++++++++++ \n");
        }
        
        
    }
}
