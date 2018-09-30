package Homework1;
import java.io.*;
import java.net.*;
 
public class EchoClient {
    public static void main(String[] args) throws Exception {
        //String hostName = "localhost";
        String hostIP = "10.12.106.12";
        //String hostName = "fe80::7517:c1af:b2bb:da73%4";
        int portNumber = 4321;
 
//        Socket echoSocket = new Socket(hostName, portNumber);
		Socket echoSocket = new Socket();
		SocketAddress sockaddr = new InetSocketAddress("localhost", portNumber);
		echoSocket.connect(sockaddr, 100);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
        BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
        
        String userInput;
        String inputLine;
        userInput= "Cilent ready";
        out.println(userInput);
        out.flush();
        do {
        	
        	inputLine = in.readLine();
        	System.out.println("Got:"+inputLine);
        	
        	out.print("cilent gets input\n");
        	out.flush();
        
//            userInput = stdIn.readLine();
            
            System.out.println("Husband says: " + in.readLine());
            
        } while (!userInput.equals("Bye"));
            
        echoSocket.close();
        in.close();
        out.close();
        stdIn.close();           
    }
    
}
