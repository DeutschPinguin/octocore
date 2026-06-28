package net.octocore;

import net.octocore.network.Server;


public class Main
{
        
        static void main ()
        {
                try
                {
                        var server = Server.open(25565);
                        server.run();
                }
                catch (Exception e)
                {
                        throw new RuntimeException(e);
                }
        }
        
}
