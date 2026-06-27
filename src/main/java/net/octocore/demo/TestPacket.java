package net.octocore.demo;

import net.octocore.datatype.DataBuffer;
import net.octocore.networking.packet.PacketData;
import net.octocore.networking.packet.PacketType;


public class TestPacket
        implements PacketData
{
        
        public static final int ID = PacketType.register(new PacketType<>(buf ->
        {
                var packet = new TestPacket(buf.readInt());
                System.out.printf("Test packet data: %d\n", packet.data);
                return packet;
        }));
        
        public int data;
        
        
        public TestPacket (int data)
        {
                this.data = data;
        }
        
        
        @Override
        public void write (DataBuffer buf) {}
        
}
