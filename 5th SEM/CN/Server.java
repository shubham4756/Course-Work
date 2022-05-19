// A Java program for a Server 
import java.net.*; 
import java.io.*; 

public class Server 
{ 
	//initialize socket and input stream 
	private Socket socket[] = new Socket[2]; 
	private ServerSocket server = null; 
	private DataInputStream in	 = null;
    private DataInputStream infk = null; 
    private DataOutputStream outt[] = new DataOutputStream[2];
	// constructor with port 
	public Server(int port) 
	{ 
		// starts server and waits for a connection 
		try
		{ 
			server = new ServerSocket(port); 
			System.out.println("Server started"); 
			System.out.println("Waiting for a primary client ..."); 
			socket[0] = server.accept(); 
            System.out.println("Waiting for another client ...");
            socket[1] = server.accept();
			System.out.println("all Clients accepted"); 
			// takes input from the client socket 
			in = new DataInputStream( new BufferedInputStream( ( socket[0].getInputStream() ) ) ); 
            infk = new DataInputStream (System.in);
            outt[0]= new DataOutputStream(socket[0].getOutputStream());
            outt[1]= new DataOutputStream(socket[1].getOutputStream());
			String line = "", line2 = ""; 

			// reads message from client until "Over" is sent 
			while (!line.equals("Over") && !line2.equals("Over")) 
			{ 
				try
				{ 
                    line2=infk.readLine();
                    outt[0].writeUTF(line2);
                    outt[1].writeUTF(line2);
					line = in.readUTF(); 
					System.out.println("client says:" + line); 
                    
				} 
				catch(IOException i) 
				{ 
					System.out.println(i); 
				} 
			} 
			System.out.println("Closing connection"); 

			// close connection 
			socket[0].close();
            socket[1].close(); 
			in.close(); 
            outt[0].close();
            outt[1].close();
            infk.close();
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
	} 

	public static void main(String args[]) 
	{ 
		Server server = new Server(9999); 
	} 
} 
