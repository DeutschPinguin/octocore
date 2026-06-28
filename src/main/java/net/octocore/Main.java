package net.octocore;

import net.octocore.network.NetworkHandler;
import org.apache.logging.log4j.LogManager;


public class Main
{
        
        static void main ()
        {
                try (var server = NetworkHandler.open(25565))
                {
                        server.run();
                }
                catch (Exception e)
                {
                        LogManager.getLogger().fatal(e.getMessage(), e);
                }
        }
        
}
