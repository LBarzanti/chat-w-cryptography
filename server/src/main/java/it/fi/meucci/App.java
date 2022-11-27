package it.fi.meucci;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main(String args[])
    {
        try
        {
            ServerSocket ss = new ServerSocket(25565);
            DataOutputStream out;
            BufferedReader in;
            for (int index = 1; index > 0; index++) 
            {
                Socket s = ss.accept();
                out = new DataOutputStream(s.getOutputStream());
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                System.out.println("connessione effettuata");
                crittografia c = new crittografia();
                String e = c.getPrivKeyString();
                out.writeBytes(e + "\n");
                c.setExtPrivateString(in.readLine());
                invia i = new invia(s, c);
                ascolta a = new ascolta(s, c);
                c.initFromStrings();
                i.start();
                a.start();
                a.join();
                ss.close();
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }
}


