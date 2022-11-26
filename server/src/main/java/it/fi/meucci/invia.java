package it.fi.meucci;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class invia extends Thread
{
    Socket s;
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String m;
    crittografia c;

    public invia(Socket s, crittografia c) 
    {
        this.s = s;
        this.c = c;
    }

    public void run()
    {
        try
        {
            m=in.readLine();
            m=c.cifra(m);
            System.out.println(m);
            m=c.decifra(m);
            System.out.println(m);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    
}