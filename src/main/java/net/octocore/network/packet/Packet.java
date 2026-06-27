package net.octocore.network.packet;

import java.nio.ByteBuffer;

public interface Packet {
	void write(ByteBuffer bytes);
}
