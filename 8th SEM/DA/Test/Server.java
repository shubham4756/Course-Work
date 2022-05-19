import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Server {
    public static void main(String args[]) throws Exception {
        // Create server Socket
        ServerSocket ss = new ServerSocket(888);

        // connect it to client socket
        Socket s = ss.accept();
        System.out.println("Connection established");

        // to send data to the client
        PrintStream ps = new PrintStream(s.getOutputStream());

        // to read data coming from the client
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // server executes continuously
        while (true) {
            String str, str1;

            // repeat as long as the client
            // does not send a null string

            // read from client
            while ((str = br.readLine()) != null) {
                System.out.println(str);

                str1 = findDayofDate(str);

                System.out.println("  ->  " + convertToDay(str1));
                
                // send to client
                ps.println(str1);
            }

            // close connection
            ps.close();
            br.close();
            ss.close();
            s.close();

            // terminate application
            System.exit(0);
        } // end of while
    }

    static String findDayofDate(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.ENGLISH);
        Date date = sdf.parse(str);
        //specifies the pattern to print
        sdf.applyPattern("EEE, d MMM yyyy");
        //prints day name with date
        return sdf.format(date).substring(0,3);
    }

    static String convertToDay(String str) {
        if (str.equals("Sun")) return "Sunday";
        else if (str.equals("Mon")) return "Monday";
        else if (str.equals("Tue")) return "Tuesday";
        else if (str.equals("Wed")) return "Wedneday";
        else if (str.equals("Thu")) return "Thurday";
        else if (str.equals("Fri")) return "Friday";
        else return "Sarurday";
    }
}
