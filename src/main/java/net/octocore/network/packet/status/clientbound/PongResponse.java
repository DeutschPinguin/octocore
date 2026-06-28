package net.octocore.network.packet.status.clientbound;

import net.octocore.network.datatype.DataBuffer;
import net.octocore.network.packet.PacketData;

import java.nio.BufferUnderflowException;


public record PongResponse(long timestamp)
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buffer)
        {
                buffer.writeLong(this.timestamp);
        }
        
        
        public static PongResponse create (DataBuffer buffer)
        {
                try
                {
                        return new PongResponse(buffer.readLong());
                }
                catch (BufferUnderflowException e)
                {
                        return null;
                }
        }
        
}
