package net.octocore.network.datatype;

import java.nio.ByteBuffer;

public class ReaderBuffer {
	private ByteBuffer buffer;

	public ReaderBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}

	int readByte() {
		return this.buffer.get();
	}

	short readShort() {
		return this.buffer.getShort();
	}

	public int readInt() {
		return this.buffer.getInt();
	}

	long readLong() {
		return this.buffer.getLong();
	}

	int readVarInt() {
		return VarInt.read(this.buffer);
	}

	long readVarLong() {
		return VarLong.read(this.buffer);
	}
}
