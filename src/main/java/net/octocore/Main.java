package net.octocore;

import net.octocore.networking.Server;
import net.octocore.networking.packet.PacketBuffer;


public class Main
{
        
        static void main ()
        {
                if (TestPacket.ID == 0);
                
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
