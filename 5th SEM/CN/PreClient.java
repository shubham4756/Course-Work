// A Java program for a Client 
import java.net.*; 
import java.io.*; 

public class PreClient 
{ 
	// initialize socket and input output streams 
	private Socket socket[] = new Socket[2]; 
    private ServerSocket sserver =null;
	private DataInputStream infsc[] = new DataInputStream[2];
    private DataInputStream infk=null; 
	private DataOutputStream out = null; 

	// constructor to put ip address and port 
	public PreClient(String address, int port1,int port2) 
	{ 
		// establish a connection 
		try
		{ 
			socket[0] = new Socket(address, port1); 
			System.out.println("Connected to the main Server:" +  "\n" + "waiting for the other client to join");
            sserver= new ServerSocket(port2);
            socket[1]=sserver.accept();
            System.out.println("Connected to the client");
			// takes input from terminal 
			infk = new DataInputStream(System.in); 
            infsc[0]=new DataInputStream( new BufferedInputStream(socket[0].getInputStream()));
            infsc[1]=new DataInputStream( new BufferedInputStream(socket[1].getInputStream()));
			// sends output to the socket 
			out = new DataOutputStream(socket[0].getOutputStream()); 
		} 
		catch(UnknownHostException u) 
		{ 
			System.out.println(u); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 

		// string to read message from input 
		String line = "",line2=""; 

		// keep reading until "Over" is input 
		while (!line.equals("Over") && !line2.equals("Over") ) 
		{ 
			try
			{ 
				line = infsc[0].readUTF(); 
                System.out.println("Server says :" + line);
                line2=infsc[1].readUTF();
                out.writeUTF(line2);
                System.out.println("Client says:" + line2);
			} 
			catch(IOException i) 
			{ 
				System.out.println(i); 
			} 
		} 

		// close the connection 
		try
		{ 
			infk.close(); 
			out.close(); 
            infsc[0].close();
            infsc[1].close();
            sserver.close();
			socket[0].close();
            socket[1].close(); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
	} 

	public static void main(String args[]) 
	{ 
		PreClient client = new PreClient("127.0.0.1", 9999,5001); 
	} 
} 
