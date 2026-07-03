package net.octocore.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteBuffer;


public final class ClientView
{
        private static final Logger LOGGER = LogManager.getLogger("ClientView");
        private ByteBuffer
                packetBuffer = ByteBuffer.allocate(256),
                workBuffer = ByteBuffer.allocate(256);
        private int packetSize = -1;
        
        
        /**
         * @param buffer Buffer received from real client. Capcity is fixed, actual data size is varying but always contains at least one byte
         */
        public void receiveDataFromClient (final ByteBuffer buffer)
        {
                /*
                        TODO packets parsing
                        TODO this.buffer overflow handling
                        TODO remove all packet parsing and world updating from network thread
                        TODO split client's update work (read VS write) into different threads
                */
                
//                this.packetBuffer.put(buffer);
//
//                if (this.packetSize == -1)
//                {
//                        while (this.packetBuffer.hasRemaining())
//                        {
//                               byte b = this.packetBuffer.get();
//                               this.workBuffer.put(b);
//                               int pos = this.workBuffer.position();
//                               if (pos >= 4 || !VarInt.hasNextByte(b))
//                               {
//                                       this.packetSize = VarInt.read(this.workBuffer) + pos;
//                                       this.workBuffer.clear();
//                                       break;
//                               }
//                        }
//                }
//
//                if (this.packetBuffer.limit() < this.packetSize) return;
        }
        
        
        /**
         * @param buffer Empty buffer which will be sent to real client after filling in this method
         */
        public void sendDataToClient (final ByteBuffer buffer)
        {
                
        }
        
}
