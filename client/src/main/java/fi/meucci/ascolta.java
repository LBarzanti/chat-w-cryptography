package fi.meucci;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ascolta extends Thread
{
    Socket s;
    crittografia c;

    public ascolta(Socket s, crittografia c) 
    {
        this.s = s;
        this.c = c;
    }

    public void run()
    {
        
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            for(;;)
            {
                String x = c.decrypt(in.readLine());
                if (x.equals("fine") || x.equals("FINE")  || x == null || x.equals("null")) 
                {
                    break;
                }
                else
                {
                    System.out.println(x);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
