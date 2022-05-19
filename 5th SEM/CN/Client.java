// A Java program for a Client 
import java.net.*; 
import java.io.*; 

public class Client 
{ 
	// initialize socket and input output streams 
	private Socket socket[] = new Socket[2]; 
	private DataInputStream infs = null;
    private DataInputStream infk = null;
	private DataOutputStream out = null; 

	// constructor to put ip address and port 
	public Client(String address, int port1,int port2) 
	{ 
		// establish a connection 
		try
		{ 
			socket[0] = new Socket(address, port1);
            socket[1] = new Socket(address, port2); 
			System.out.println("Connected"); 

			// takes input from terminal 
			infk = new DataInputStream(System.in); 
            infs=new DataInputStream( new BufferedInputStream(socket[0].getInputStream()));
			// sends output to the socket 
			out = new DataOutputStream(socket[1].getOutputStream()); 
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
				line = infs.readUTF(); 
                System.out.println("server says: " + line);
                line2=infk.readLine();
                out.writeUTF(line2);
			} 
			catch(IOException i) 
			{ 
				System.out.println(i); 
			} 
		} 

		// close the connection 
		try
		{ 
            infs.close();
			infk.close(); 
			out.close(); 
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
		Client client = new Client("127.0.0.2", 9999,5001); 
	} 
} 