package it.fi.meucci;
import java.io.*;
import java.net.Socket;

public class invia extends Thread
{
    Socket s;
    BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
    String m;
    crittografia c;
    DataOutputStream out;

    public invia(Socket s, crittografia c) throws IOException
    {
        this.s = s;
        this.c = c;
        out = new DataOutputStream(s.getOutputStream());
    }

    public void run()
    {
        try
        {
            for(;;)
            {
                m=tastiera.readLine();
                m=c.encrypt(m);
                out.writeBytes(m + "\n");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    
}
