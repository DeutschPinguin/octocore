package net.octocore.network;

import net.octocore.network.datatype.VarInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteBuffer;


public final class ClientView
{
        private static final Logger LOGGER = LogManager.getLogger("ClientView");
        
        private final ByteBuffer sizeBuffer = ByteBuffer.allocate(5);
        private ByteBuffer packetBuffer, extraBuffer;
        private int packetSize = -1;
        
        
        /**
         * @param buffer Buffer with some chunk of the data received from real client
         */
        public void receiveDataFromClient (ByteBuffer buffer)
        {
                // TODO multithreading
                
                if (this.extraBuffer != null && this.extraBuffer.hasRemaining())
                {
                        int capacity = this.extraBuffer.capacity() + buffer.capacity();
                        buffer = ByteBuffer.allocate(capacity).put(this.extraBuffer).put(buffer).flip();
                        this.extraBuffer = null;
                }
                
                if (this.packetSize == -1)
                {
                        while (buffer.hasRemaining())
                        {
                               byte b = buffer.get();
                               this.sizeBuffer.put(b);
                               if (this.sizeBuffer.position() == 4 || !VarInt.hasNextByte(b))
                               {
                                       this.sizeBuffer.flip();
                                       this.packetSize = VarInt.read(this.sizeBuffer);
                                       this.sizeBuffer.clear();
                                       if (this.packetSize < 1)
                                       {
                                               LOGGER.trace("Received packet with 0 size");
                                               this.packetSize = -1;
                                               continue;
                                       }
                                       this.packetBuffer = ByteBuffer.allocate(this.packetSize);
                                       break;
                               }
                        }
                        if (this.packetSize == -1) return;
                }
                
                int sizeToLoad = this.packetBuffer.capacity() - this.packetBuffer.position();
                if (sizeToLoad > 0)
                {
                        sizeToLoad = Math.min(sizeToLoad, buffer.remaining());
                        var data = buffer.slice(buffer.position(), sizeToLoad);
                        this.packetBuffer.put(data);
                        buffer.position(buffer.position() + sizeToLoad);
                }
                
                int maxPosition = this.packetBuffer.capacity() - 1;
                if (this.packetBuffer.position() < maxPosition) return; // packet body isn't fully loaded
                
                if (buffer.hasRemaining()) this.extraBuffer = ByteBuffer.allocate(buffer.remaining()).put(buffer).flip();
                this.packetBuffer.flip();
                int id = VarInt.read(this.packetBuffer);
                LOGGER.trace("Received packet (size {} | id {})", this.packetSize, id);
                // TODO do something with fully loaded packet ...
                this.packetSize = -1;
        }
        
        
        /**
         * @param buffer Empty buffer which will be sent to real client after filling there
         */
        public void sendDataToClient (final ByteBuffer buffer)
        {
                
        }
        
}
