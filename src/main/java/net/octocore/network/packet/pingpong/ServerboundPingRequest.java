package net.octocore.network.packet.pingpong;

import net.octocore.network.datatype.DataBuffer;
import net.octocore.network.packet.PacketData;

import java.nio.BufferUnderflowException;


public record ServerboundPingRequest(long timestamp)
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buffer)
        {
                buffer.writeLong(this.timestamp);
        }
        
        
        public static ServerboundPingRequest create (DataBuffer buffer)
        {
                try
                {
                        return new ServerboundPingRequest(buffer.readLong());
                }
                catch (BufferUnderflowException e)
                {
                        return null;
                }
        }
        
}
