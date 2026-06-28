package net.octocore.network.packet.pingpong;

import net.octocore.network.datatype.DataBuffer;
import net.octocore.network.packet.PacketData;


public record ServerboundStatusRequest()
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buffer) {}
        
        
        public static ServerboundStatusRequest create (DataBuffer buffer)
        {
                return new ServerboundStatusRequest();
        }
        
}
