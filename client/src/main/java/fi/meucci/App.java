package fi.meucci;
import java.io.*;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
        try
        {
            DataOutputStream out;
            BufferedReader in;
            Socket s;
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
            String host;
            int port;
            System.out.println("inserire indirizzo ip del server");
            host = tastiera.readLine();
            System.out.println("inserire la porta del server");
            port = tastiera.read();
            s = new Socket(host, port);
            out = new DataOutputStream(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("connessione effettuata");
            crittografia c = new crittografia();
            String e = in.readLine();
            c.setExtPrivateString(e);
            out.writeBytes(c.getPrivKeyString() + "\n");
            invia i = new invia(s, c);
            ascolta a = new ascolta(s, c);
            c.initFromStrings();
            a.start();
            i.start();
            a.join();
            s.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
