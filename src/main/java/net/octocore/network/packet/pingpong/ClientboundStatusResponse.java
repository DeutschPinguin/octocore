package net.octocore.network.packet.pingpong;

import net.octocore.network.packet.pingpong.data.PingStatus;
import net.octocore.network.datatype.DataBuffer;
import net.octocore.network.packet.PacketData;

import java.nio.BufferUnderflowException;


public record ClientboundStatusResponse(PingStatus status)
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buffer)
        {
                // TODO server status response
        }
        
        
        public static ClientboundStatusResponse create (DataBuffer buffer)
        {
                try
                {
                        return new ClientboundStatusResponse(null);
                }
                catch (BufferUnderflowException e)
                {
                        return null;
                }
        }
        
}
