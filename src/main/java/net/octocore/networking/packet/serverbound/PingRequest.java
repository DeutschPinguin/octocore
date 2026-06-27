package net.octocore.networking.packet.serverbound;

import net.octocore.datatype.DataBuffer;
import net.octocore.networking.packet.PacketData;
import net.octocore.networking.packet.PacketType;
import net.octocore.networking.packet.clientbound.PongResponse;

import java.nio.BufferUnderflowException;


public record PingRequest(long timestamp)
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buf)
        {
                buf.writeLong(this.timestamp);
        }
        
        
        public static PingRequest create (DataBuffer buf)
        {
                try
                {
                        return new PingRequest(buf.readLong());
                }
                catch (BufferUnderflowException e)
                {
                        return null;
                }
        }
        
}
