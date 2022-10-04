package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    public static void main(String[] args) throws IOException {
        
        Socket socket = null;
        
        int port = ServConfig.PORT;
        
        ServerSocket server = new ServerSocket(port);
        
        socket = server.accept();
        
        if(socket != null){
            System.out.println("Connection successfully established with: "+socket.getInetAddress());
        }
        
        
    }
}
