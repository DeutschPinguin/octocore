package net.octocore.networking.packet.clientbound;

import net.octocore.datatype.DataBuffer;
import net.octocore.networking.ServerStatus;
import net.octocore.networking.packet.PacketData;

import java.nio.BufferUnderflowException;


public record StatusResponse(ServerStatus status)
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buf)
        {
        
        }
        
        
        public static StatusResponse create (DataBuffer buf)
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
