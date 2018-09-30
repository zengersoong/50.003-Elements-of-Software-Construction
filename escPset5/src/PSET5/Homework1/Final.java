package Homework1;

import java.net.*;
import java.io.*;
import java.math.BigInteger;

// ---------------------NOTE TO USER----------------------------------------------------------------//
//---compile this in Command line and key in the Big integer for example "1127451830576035879"------//
//DO NOT  need to run Echo server or echo Cilent those are demo files------------------------------//
/**
 * 
 * Server that waits for client connections, wait for user input,
 * send this input to each of the 10 clients, then wait for 
 * any one of the client to send result. On receiving a result
 * it send stops to all clients
 *
 */
class Server extends Thread {


    public final int NUM_CLIENTS = 10;
    ConnectedClient tenclients[] ;
    ServerSocket serverSocket;


    /**
     * an inner class to contain the client connection in & out
     *
     */
    class ConnectedClient implements Runnable {
        PrintWriter out;
        BufferedReader in;
        String result;
        int id;
        private Socket clientSocket;
        private Thread clientWaitThr;

        void setup(Socket clientSocket, int id) throws IOException {
            this.id = id;
            this.clientSocket = clientSocket;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            result = null;  // initially set empty, later when data arrive, it will be set
        }

        void sendWork(String outputLine) {
            out.println(outputLine);
            clientWaitThr = new Thread(this);
            clientWaitThr.start();

        }

        @Override
        public void run() { 
            try {
                result = in.readLine();
                System.out.println("Server client monitor thr#" + id + " received result ("+result+") from client");
                synchronized(Server.this) {
                    Server.this.notify();  //wake up the server
                }
            } catch (IOException e) {
                //e.printStackTrace();
                //System.out.println("Client " + id + " socket closed!");
            } 
        }

        /**
         * Call by server to send stop command to client
         * @throws IOException
         */
        public void sendStop() throws IOException {

            out.println("stop");  //sending stop command to client
            out.flush();
            clientSocket.close();
            in.close();
            out.close();
            
        }

    }

    Server() throws IOException {       

        serverSocket = new ServerSocket(4321);  // Server socket to wait for connection

        tenclients = new ConnectedClient[10];
        for ( int n = 0; n < NUM_CLIENTS; n++ ) {
            tenclients[n] = new ConnectedClient();
        }

    }

    /**
     * The main work of the server
     * @throws IOException
     * @throws InterruptedException
     */
    public void doWork() throws IOException, InterruptedException {

        //wait for the clients to connect

        int nClient = 0;
        while ( nClient < 10 ) {
            System.out.printf("Waiting for connection %d\n", nClient+1);
            Socket clientSocket = serverSocket.accept();
            System.out.printf("connection established from client # %d...\n", nClient+1);
            tenclients[nClient].setup(clientSocket, nClient+1);     
            nClient++;
        }


        //wait For User Input And Send

        BufferedReader stdIn =
                new BufferedReader(
                        new InputStreamReader(System.in));
        String inputLine;
        
        while ( true) {
            System.out.println("Enter data to send:");
            inputLine = stdIn.readLine();
            if ( ! inputLine.matches("^\\d+$")) {
                System.out.println("Invalid input, please re-enter");
            } else {
                break;
            }
        }
        
        System.out.println("Got input:"+inputLine);
        for (int n = 0; n < NUM_CLIENTS; n++) {
            System.out.printf("Sending work to client %d\n", n+1);
            this.tenclients[n].sendWork(inputLine);
        }

        //Now wait for any client monitor thread to notify

        synchronized( this ) {
            wait(); 
        }

        //sending stop to all clients
        for (int n=0; n <NUM_CLIENTS; n++ ) {
            tenclients[n].sendStop();
        }

    }
    
    /**
     * EchoServer thread run
     */
    @Override
    public void run() {

        super.run();
        try {
            doWork();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

/**
 * Client
 * each client has a worker thread that waits for server input
 * upon receiving server input, it launch a server command
 * monitor thread and then starts work
 * 
 *
 */
class Client extends Thread {
    Socket echoSocket;
    PrintWriter out;
    BufferedReader in;
    int id;
    private Thread clientWaitThr;

    /**
     * 
     * @param id : an integer representing the id of the client
     * @throws IOException
     */
    Client(int id) throws IOException {

        this.id = id;
        String hostIP = "127.0.0.1";
        int portNumber = 4321;

        echoSocket = new Socket();
        SocketAddress sockaddr = new InetSocketAddress(hostIP, portNumber);
        System.out.printf("Client %d connecting\n", id);
        echoSocket.connect(sockaddr, 100);
        out = new PrintWriter(echoSocket.getOutputStream(), true);
        in =  new BufferedReader(
                new InputStreamReader(echoSocket.getInputStream()));


    }
    
    /**
     * waits for server to send data 
     * starts a thread to wait for server stop command, 
     * after that it starts work
     * 
     * @throws IOException
     */
    public void run()  {
        String inputLine;
        final int numberOfThreads = 4;
        try {
            inputLine = in.readLine();
            System.out.printf("Client %d received data '%s' and starts work\n", id, inputLine);
            clientWaitThr = new ClientStopMonitorThread();  // a thread to wait for stop command from server
            clientWaitThr.start();

            //put your calculation work here
            //simulate work by waiting, thread #10 work 1 seconds, thr #9 work 2 secs ..
            //Thread.sleep((11-id)*1000); 
            BigInteger n = new BigInteger(inputLine);
            FactorInterrupt[] factors = new FactorInterrupt[numberOfThreads];
    		for (int i = 0; i < numberOfThreads; i++) {
    			factors[i] = new FactorInterrupt (n, i+2, numberOfThreads);
    			factors[i].start();
    		}

    		for (int i = 0; i < numberOfThreads; i++) {
    			factors[i].join();
    		}
    		if(FactorInterrupt.stopThread) {
    			this.interrupt();
    		}

            System.out.printf("Client %d completed work and sending result to server\n", id);
            out.println(FactorInterrupt.resultFact);

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            return;
        }

    }

    /**
     * Inner class within EchoClient to monitor stop command from server
     *
     */
    class ClientStopMonitorThread extends Thread {
        @Override
        public void run() {
            String inputLine;

            try {
                inputLine = in.readLine();

                if (inputLine.equals("stop") ) {
                    System.out.println("Client " +id+" got stop message from server");
                    Client.this.interrupt();
                    echoSocket.close();
                    in.close();
                    out.close();
                }
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
    }
    /*
     * inside cilent
     */
    public static BigInteger factorMultiThread (BigInteger input) {
		BigInteger result = null;
		
		return result;
	}
    static class FactorInterrupt extends Thread {
    	BigInteger n;
    	BigInteger init;
    	BigInteger stepSize;
    	static volatile BigInteger resultFact;
    	static volatile boolean stopThread;//Use a static Booblean variable found for communication between threads
    	
    	public FactorInterrupt (BigInteger n, int init, int stepSize) {
    		this.n = n;
    		this.init = BigInteger.valueOf(init);
    		this.stepSize = BigInteger.valueOf(stepSize);
    	}
    	
        public void run() {
        	
    		BigInteger zero = new BigInteger("0");
    		
    		while (init.compareTo(n) < 0) {	
    			if(stopThread)break;
    			if (n.remainder(init).compareTo(zero) == 0) {
    				System.out.println("Got it: " + init);
    				resultFact=init;
    				stopThread=true;
    				break;
    			}
    			
    			init = init.add(stepSize);
    		}
    	}
    }
    
    
}


public class Final {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();

        Client[] clients = new Client[10];
        for ( int n = 0; n < 10; n++ ) {
            clients[n] = new Client(n+1);
            clients[n].start();
        }

    }

}
