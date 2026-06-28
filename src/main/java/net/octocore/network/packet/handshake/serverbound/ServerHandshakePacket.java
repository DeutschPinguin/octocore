package net.octocore.network.packet.handshake.serverbound;

import net.octocore.network.packet.ServerboundPacket;

import java.nio.BufferUnderflowException;


public class ServerHandshakePacket extends ServerboundPacket {
	@Override
	public void read() {
		try {
			int protocolVersion = this.buffer.readVarInt();
			String serverAddress = ""; // TODO read string buffer.readString();
			int serverPort = this.buffer.readShort();
			ClientIntent intent = ClientIntent.getIntent((byte) this.buffer.readVarInt());
		} catch (BufferUnderflowException e) {
		}
	}
}
