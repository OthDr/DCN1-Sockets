
package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
            Socket clientSocket = new Socket(host, port);
            
            BufferedReader bufferedInputStreamReader = null;
            BufferedWriter bufferedOutputStreamWriter = null;
            
                  
            // 1)- Creating socket streams
            
            InputStream socketInput = clientSocket.getInputStream();//get the input streams from the server
            OutputStream socketOutput = clientSocket.getOutputStream();//create the input stream to send to the server
            
            
            // 2)- Reading from & Writing to : the socket by the previously created streams
            
            InputStreamReader socketInputReader = new InputStreamReader(socketInput); // read the socket streams 
            OutputStreamWriter socketOutputWriter = new OutputStreamWriter(socketOutput);// write output streams
            
            
            // 3)- Append the socket io stream readers to buffered reader form more effeciency
            
            bufferedInputStreamReader = new BufferedReader(socketInputReader);//buffered read the socket streams for effeciency
            bufferedOutputStreamWriter = new BufferedWriter(socketOutputWriter);// buffered write the socket writer for more effeciency
            
            
            // --------------------
           
            String message = "I am a client";
            
            // Send data to the server by buffered writing to the output stream
            bufferedOutputStreamWriter.write(message); // write a msg to get it streamed out to the server
            bufferedOutputStreamWriter.newLine();
            
            bufferedOutputStreamWriter.write("I am quitting");
            bufferedOutputStreamWriter.flush(); // flush/clear data
            
            String responseData = null; // the data that comes from the server as a response
            
           // Read data comming from the server
            while ( bufferedInputStreamReader.readLine() != null ){
                
                responseData += bufferedInputStreamReader.readLine();
                System.out.println("***** Server :"+ responseData);
                
                if(responseData.contains("7Tango") ){// if the server responds with '7Tango' the communication ends
                    break;
                }
            }
            
            // close communication (Input and Output)
            bufferedOutputStreamWriter.close();
            bufferedInputStreamReader.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
