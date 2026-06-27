package net.octocore.networking.packet;

import java.nio.ByteBuffer;


public interface Packet
{

        void write (ByteBuffer bytes);
        
}
