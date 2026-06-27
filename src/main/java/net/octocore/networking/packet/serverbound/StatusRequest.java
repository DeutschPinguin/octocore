package net.octocore.networking.packet.serverbound;

import net.octocore.datatype.DataBuffer;
import net.octocore.networking.packet.PacketData;
import net.octocore.networking.packet.clientbound.PongResponse;

import java.nio.BufferUnderflowException;


public record StatusRequest()
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buf) {}
        
        
        public static StatusRequest create (DataBuffer buf)
        {
                return new StatusRequest();
        }
        
}
