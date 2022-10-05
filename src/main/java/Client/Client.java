package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
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
        Scanner keyboard = new Scanner(System.in);

        try {
            Socket clientSocket;
            clientSocket = new Socket(host, port);

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
            String message = "Hello!";

            // Send data to the server by buffered writing to the output stream
            /*
            //** staticly typed data 
            bufferedOutputStreamWriter.write(message); // write a msg to get it streamed out to the server
            bufferedOutputStreamWriter.newLine();

            bufferedOutputStreamWriter.write("I am a client"); // write a msg to get it streamed out to the server
            bufferedOutputStreamWriter.newLine();
            bufferedOutputStreamWriter.flush();
            
            
            bufferedOutputStreamWriter.write("SierraCharlie");// I Quit
            bufferedOutputStreamWriter.newLine();
            bufferedOutputStreamWriter.flush(); // flush/clear data*/
            
            String keyboardInput;

            String responseData; // the data that comes from the server as a response

            while (true) {
                keyboardInput = keyboard.nextLine();
                bufferedOutputStreamWriter.write(keyboardInput);
                bufferedOutputStreamWriter.newLine();
                bufferedOutputStreamWriter.flush();

                // Read data comming from the server
                if ((responseData = bufferedInputStreamReader.readLine()) != null) {

                    System.out.println("***** Server :" + responseData);

                    if (responseData.contains("7Tango")) {// if the server responds with '7Tango' the communication ends
                        System.out.println("server has left");
                        bufferedOutputStreamWriter.close();
                        bufferedInputStreamReader.close();
                        clientSocket.close();// close socket
                    }
                }
            }

            // close communication (Input and Output)
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
