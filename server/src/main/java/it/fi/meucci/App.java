package it.fi.meucci;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main(String args[])
    {
        try
        {
            ServerSocket ss = new ServerSocket(25565);
            for (int index = 1; index > 0; index++) 
            {
                Socket s = ss.accept();
                System.out.println("connessione effettuata");
                crittografia c = new crittografia(s);
                invia i = new invia(s, c);
                ascolta a = new ascolta(s, c);
                c.init();
                i.start();
                a.start();
            }
            ss.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }
}


