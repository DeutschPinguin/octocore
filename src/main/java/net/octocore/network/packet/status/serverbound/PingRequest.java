package net.octocore.network.packet.status.serverbound;

import net.octocore.network.datatype.DataBuffer;
import net.octocore.network.packet.PacketData;

import java.nio.BufferUnderflowException;


public record PingRequest(long timestamp)
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buffer)
        {
                buffer.writeLong(this.timestamp);
        }
        
        
        public static PingRequest create (DataBuffer buffer)
        {
                try
                {
                        return new PingRequest(buffer.readLong());
                }
                catch (BufferUnderflowException e)
                {
                        return null;
                }
        }
        
}
