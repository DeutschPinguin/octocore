package net.octocore.network.packet.status.serverbound;

import net.octocore.network.datatype.DataBuffer;
import net.octocore.network.packet.PacketData;


public record StatusRequest()
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buffer) {}
        
        
        public static StatusRequest create (DataBuffer buffer)
        {
                return new StatusRequest();
        }
        
}
