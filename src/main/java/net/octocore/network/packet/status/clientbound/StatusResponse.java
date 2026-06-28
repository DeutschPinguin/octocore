package net.octocore.network.packet.status.clientbound;

import net.octocore.network.ServerStatus;
import net.octocore.network.datatype.DataBuffer;
import net.octocore.network.packet.PacketData;

import java.nio.BufferUnderflowException;


public record StatusResponse(ServerStatus status)
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buffer)
        {
        
        }
        
        
        public static StatusResponse create (DataBuffer buffer)
        {
                try
                {
                        return new StatusResponse(null);
                }
                catch (BufferUnderflowException e)
                {
                        return null;
                }
        }
        
}
