
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DOTH
 */
public class Client {

    public static void main(String[] args) {

        int port = ClientConfig.PORT;
        String host = ClientConfig.HOST;

        try {
            Socket socket = new Socket(host, port);
                  
            //InputStreamReader reader = new InputStreamReader(socket.getInputStream()); 
            
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            
            String message = input.readLine();
            
            PrintWriter outMsg = new PrintWriter(socket.getOutputStream());
            
            outMsg.println(message);
            outMsg.flush();
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
