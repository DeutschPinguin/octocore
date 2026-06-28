package net.octocore;

import net.octocore.network.OldServer;


public class Main
{
        
        static void main ()
        {
                try
                {
                        var server = OldServer.open(25565);
                        server.run();
                }
                catch (Exception e)
                {
                        throw new RuntimeException(e);
                }
        }
        
}
