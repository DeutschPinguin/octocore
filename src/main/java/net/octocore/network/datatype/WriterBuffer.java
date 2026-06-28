package net.octocore.network.datatype;

import java.nio.ByteBuffer;

public class WriterBuffer {
	protected ByteBuffer buffer;

	public WriterBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}

	public WriterBuffer(byte[] buffer) {
		this(ByteBuffer.wrap(buffer));
	}

	public WriterBuffer(byte[] buffer, int offset, int length) {
		this(ByteBuffer.wrap(buffer, offset, length));
	}

	public WriterBuffer(int capacity) {
		this(ByteBuffer.allocate(capacity));
	}

	public WriterBuffer() {
		this(1);
	}

	public DataBuffer checkBufferBoundaries(int offset) {
		int size = this.buffer.capacity(), neededSpace = this.buffer.position() + offset - size + 1;
		// if (neededSpace <= 0)
		// return this;
		var newBuffer = ByteBuffer.allocate(size + neededSpace).put(this.buffer.flip());
		this.buffer = newBuffer;
	}

	public void writeByte(byte value) {
		this.checkBufferBoundaries(1);
		this.buffer.put(value);
	}

	public void writeShort(short value) {
		this.checkBufferBoundaries(2);
		this.buffer.putShort(value);
	}

	public void writeInt(int value) {
		this.checkBufferBoundaries(4);
		this.buffer.putInt(value);
	}

	public void writeVarInt(int value) {
		this.checkBufferBoundaries(VarInt.getByteSize(value));
		VarInt.write(buffer, value);
	}

	public void writeLong(long value) {
		this.checkBufferBoundaries(8);
		this.buffer.putLong(value);
	}

	public void writeVarLong(long value) {
		this.checkBufferBoundaries(VarLong.getByteSize(value));
		VarLong.write(buffer, value);
	}
}
