package net.octocore.networking.packet.serverbound;

import net.octocore.networking.ConnectionIntent;
import net.octocore.networking.packet.PacketData;
import net.octocore.datatype.DataBuffer;
import net.octocore.networking.packet.PacketType;

import java.nio.BufferUnderflowException;


public record Handshake(int protocolVersion, String serverAddress, short serverPort, ConnectionIntent intent)
        implements PacketData
{
        
        @Override
        public void write (DataBuffer buf)
        {
                buf.writeVarInt(this.protocolVersion);
                // TODO write string
                buf.writeShort(this.serverPort);
                buf.writeVarInt(intent.getId());
        }
        
        
        public static Handshake create (DataBuffer buf)
        {
                try
                {
                        int protocolVersion = buf.readVarInt();
                        String serverAddress = ""; // TODO read string
                        short serverPort = buf.readShort();
                        ConnectionIntent intent = ConnectionIntent.from(buf.readVarInt());
                        return new Handshake(protocolVersion, serverAddress, serverPort, intent);
                }
                catch (BufferUnderflowException e)
                {
                        return null;
                }
        }
        
}
