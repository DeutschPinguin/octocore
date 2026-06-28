package net.octocore.network.packet.pingpong;

import net.octocore.network.datatype.DataBuffer;
import net.octocore.network.packet.PacketData;

import java.nio.BufferUnderflowException;


public record ClientboundPongResponse(long timestamp)
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buffer)
        {
                buffer.writeLong(this.timestamp);
        }
        
        
        public static ClientboundPongResponse create (DataBuffer buffer)
        {
                try
                {
                        return new ClientboundPongResponse(buffer.readLong());
                }
                catch (BufferUnderflowException e)
                {
                        return null;
                }
        }
        
}
