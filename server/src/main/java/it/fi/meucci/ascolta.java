package it.fi.meucci;
import java.io.*;
import java.net.Socket;

public class ascolta extends Thread
{
    Socket s;

    public ascolta(Socket s) 
    {
        this.s = s;
    }

    public void run()
    {
        
    }
}
