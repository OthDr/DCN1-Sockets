package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket listener = null ;
        Socket serverSocket = null;
        String recievedData;

        BufferedReader bufferedInputStreamReader = null;
        BufferedWriter bufferedOutputStreamWriter = null;

         listener = new ServerSocket(ServConfig.PORT);// create ServerSocket
        System.out.println("-------\n Server is listening on port "+ServConfig.PORT+" \n-------");

        //create server socket 
        serverSocket = listener.accept();// accept client req and create a socket to communicate
        System.out.println("\n +++++++++++++++++++++ \n Connection successfully established with:\nclient IP:" + serverSocket.getInetAddress() + "\n +++++++++++++++++++++ \n");
        
        // 1)- Creating socket streams
        InputStream socketInput = serverSocket.getInputStream();//get the input streams from the client
        OutputStream socketOutput = serverSocket.getOutputStream();//create the input stream to send to the client

        // 2)- Reading from & Writing to : the socket by the previously created streams
        InputStreamReader socketInputReader = new InputStreamReader(socketInput); // read the socket streams 
        OutputStreamWriter socketOutputWriter = new OutputStreamWriter(socketOutput);// write output streams

        // 3)- Append the socket io stream readers to buffered reader form more effeciency
        bufferedInputStreamReader = new BufferedReader(socketInputReader);//buffered read the socket streams for effeciency
        bufferedOutputStreamWriter = new BufferedWriter(socketOutputWriter);// buffered write the socket writer for more effeciency
        
        bufferedOutputStreamWriter.write("I am the server. Talk! ");

        
        while (true) {// keep looping till the client say 'SierraCharlie' (I QUIT)
            recievedData = bufferedInputStreamReader.readLine();
            System.out.println(recievedData);

            bufferedOutputStreamWriter.write("You said:" + recievedData);

            bufferedOutputStreamWriter.newLine();
            bufferedOutputStreamWriter.flush();

            if (recievedData.contains("SierraCharlie")) {// client leaving 
                bufferedOutputStreamWriter.write("7Tango"); // Ok server leaves to
                bufferedOutputStreamWriter.newLine();
                bufferedOutputStreamWriter.flush();

                break;
            }

        }
        serverSocket.close(); // close the socket between the server and the previous client

        listener.close();

    }
}
