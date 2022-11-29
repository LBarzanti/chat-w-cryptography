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
            s = new Socket("0.tcp.eu.ngrok.io", 16608);
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
