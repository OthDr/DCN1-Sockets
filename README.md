# DCN1-Sockets
### Simple text Sockets communication
![alt text](https://static.javatpoint.com/core/images/socket-programming.png)

#### Client Communication Steps:

**First: Create a socket** 
<br>
1)- Creating socket streams <br>
2)- Reading from & Writing to : the socket by the previously created streams <br>
3)- Append the socket io stream readers to buffered reader form more effeciency <br>
**Then send data to the server by buffered writing to the output stream**

```java
// write a msg to get it streamed out to the server
bufferedOutputStreamWriter.write(message); 
bufferedOutputStreamWriter.newLine();
```

```java
// write a msg to get istreamed out to the server
bufferedOutputStreamWriter.write("I am a client"); 
bufferedOutputStreamWriter.newLine();
bufferedOutputStreamWriter.flush();
```
     
```java       
// I Quit        
bufferedOutputStreamWriter.write("SierraCharlie");
bufferedOutputStreamWriter.newLine();
```

```java
// flush/clear data
bufferedOutputStreamWriter.flush();
```
