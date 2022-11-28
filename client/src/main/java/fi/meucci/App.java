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
            s = new Socket("localhost", 25565);
            out = new DataOutputStream(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("connessione effettuata");
            crittografia c = new crittografia(s);
            invia i = new invia(s, c);
            ascolta a = new ascolta(s, c);
            c.init();
            i.start();
            a.start();
            a.join();
            s.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
