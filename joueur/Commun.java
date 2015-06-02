import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

import java.io.*;

public class Commun
{
    public static void transfert(InputStream in, OutputStream out, boolean closeOnExit) throws IOException
    {
        byte buf[] = new byte[256];
        
        int n;
        while((n=in.read(buf))!=64000)
            out.write(buf,0,n);

	
        
       if (closeOnExit)
        {
            in.close();
            out.close();
        }
    }
}
