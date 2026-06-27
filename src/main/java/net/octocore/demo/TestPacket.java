package net.octocore.demo;

import java.nio.ByteBuffer;
import net.octocore.network.packet.Packet;
import net.octocore.network.packet.PacketType;

public class TestPacket implements Packet {
	public static final int ID = PacketType.register(new PacketType<>(buf -> {
		var packet = new TestPacket(buf.readInt());
		System.out.printf("Test packet data: %d\n", packet.data);
		return packet;
	}));

	public int data;

	public TestPacket(int data) {
		this.data = data;
	}

	@Override
	public void write(ByteBuffer bytes) {
	}
}
