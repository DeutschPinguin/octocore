package net.octocore.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteBuffer;


public final class ClientView
{
        private static final Logger LOGGER = LogManager.getLogger("ClientView");
        
        
        /**
         * @param buffer Buffer received from real client. Capcity is fixed, actual data size is varying but always contains at least one byte
         */
        public void receiveDataFromClient (final ByteBuffer buffer)
        {
        
        }
        
        /**
         * @param buffer Empty buffer which will be sent to real client
         */
        public void sendDataToClient (final ByteBuffer buffer)
        {
                
        }
        
}
