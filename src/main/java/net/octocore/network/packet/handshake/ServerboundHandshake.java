package net.octocore.network.packet.handshake;

import net.octocore.network.packet.ServerboundPacket;
import net.octocore.network.packet.handshake.data.ConnectionIntent;

import java.nio.BufferUnderflowException;


public class ServerboundHandshake extends ServerboundPacket
{
        
        @Override
        public void read ()
        {
                try
                {
                        int protocolVersion = this.buffer.readVarInt();
                        String serverAddress = "";
                        int serverPort = this.buffer.readShort();
                        ConnectionIntent intent = ConnectionIntent.from(this.buffer.readVarInt());
                }
                catch (BufferUnderflowException _) {}
        }
        
}
