import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Client {

    public static void main(String args[]) throws Exception {

        // Create client socket
        Socket s = new Socket("localhost", 888);

        // to send data to the server
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        // to read data coming from the server
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // to read data from the keyboard
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        String str, str1;

        // repeat as long as exit
        // is not typed at client
        while (!(str = kb.readLine()).equals("exit")) {

            boolean checkDate = validationDate(str);
            if(checkDate) {
                // send to the server
                dos.writeBytes(str + "\n");
                // receive from the server
                str1 = convertToDay(br.readLine());
                System.out.println(str1);
            } else {
                System.out.println("Data is Invalid !!! ");
            }
        }

        // close connection.
        dos.close();
        br.close();
        kb.close();
        s.close();
    }

    static boolean validationDate(String str) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(str);
        } catch (ParseException e) {
            return false;
        }
        return true;
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