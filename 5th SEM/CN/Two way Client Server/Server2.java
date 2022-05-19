import java.io.*; 
import java.net.*; 

class Server2 { 
	public static void main(String args[]) throws Exception { 
		ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server started"); 
		System.out.println("Waiting for a client..."); 	
		Socket s = ss.accept(); 
		System.out.println("Connection established"); 

		// to send data to the client 
		PrintStream ps = new PrintStream(s.getOutputStream());  
		// to read data coming from the client
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); 
		// to read data from the keyboard 
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in)); 

		while (true) { 
			String str, str1; 
            while ((str = br.readLine()) != null) { 
				System.out.println(str); 
				str1 = kb.readLine(); 
				// send to client 
                ps.println(str1); 
			}
			ps.close(); 
			br.close(); 
			kb.close(); 
			ss.close(); 
			s.close(); 
            System.exit(0); 
		}
	} 
} 
