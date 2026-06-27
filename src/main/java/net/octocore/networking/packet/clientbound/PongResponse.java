package net.octocore.networking.packet.clientbound;

import net.octocore.datatype.DataBuffer;
import net.octocore.networking.packet.PacketData;
import net.octocore.networking.packet.PacketType;

import java.nio.BufferUnderflowException;


public record PongResponse(long timestamp)
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buf)
        {
                buf.writeLong(this.timestamp);
        }
        
        
        public static PongResponse create (DataBuffer buf)
        {
                try
                {
                        return new PongResponse(buf.readLong());
                }
                catch (BufferUnderflowException e)
                {
                        return null;
                }
        }
        
}
