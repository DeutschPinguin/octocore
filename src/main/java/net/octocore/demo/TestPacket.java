package net.octocore.demo;

import net.octocore.network.datatype.DataBuffer;
import net.octocore.network.packet.PacketData;
import net.octocore.network.packet.PacketType;


public class TestPacket
        implements PacketData
{
        
        public static final int ID = PacketType.register(new PacketType<>(buffer ->
        {
                var packet = new TestPacket(buffer.readInt());
                System.out.printf("Test packet data: %d\n", packet.data);
                return packet;
        }));
        
        public int data;
        
        
        public TestPacket (int data)
        {
                this.data = data;
        }
        
        
        @Override
        public void write (DataBuffer buffer) {}
        
}
