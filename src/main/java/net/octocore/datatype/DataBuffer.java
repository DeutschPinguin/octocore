package net.octocore.datatype;

import java.nio.ByteBuffer;


public class DataBuffer
{
        protected ByteBuffer buffer;
        
        
        public DataBuffer (ByteBuffer buffer)
        {
                this.buffer = buffer;
        }
        
        
        public DataBuffer (byte[] buffer)
        {
                this(ByteBuffer.wrap(buffer));
        }
        
        
        public DataBuffer (byte[] buffer, int offset, int length)
        {
                this(ByteBuffer.wrap(buffer, offset, length));
        }
        
        
        public DataBuffer (int capacity)
        {
                this(ByteBuffer.allocate(capacity));
        }
        
        
        public DataBuffer ()
        {
                this(1);
        }
        
        
        public int getCapacity ()
        {
                return this.buffer.capacity();
        }
        
        
        public int getSize ()
        {
                return this.buffer.limit();
        }
        
        
        public int getPointer ()
        {
                return this.buffer.position();
        }
        
        
        public DataBuffer point (int index)
        {
                this.buffer.position(index);
                return this;
        }
        
        
        public final DataBuffer move (int offset)
        {
                this.point(this.getPointer() + offset);
                return this;
        }
        
        
        public DataBuffer limit (int index)
        {
                this.buffer.limit(index);
                return this;
        }
        
        
        public DataBuffer rewind ()
        {
                this.buffer.rewind();
                return this;
        }
        
        
        public DataBuffer flip ()
        {
                this.buffer.flip();
                return this;
        }
        
        
        public byte[] getArray ()
        {
                return this.buffer.array();
        }
        
        
        public DataBuffer checkBufferBoundaries (int offset)
        {
                int size = this.buffer.capacity(), neededSpace = this.buffer.position() + offset - size + 1;
                if (neededSpace <= 0) return this;
                var newBuffer = ByteBuffer.allocate(size + neededSpace).put(this.buffer.flip());
                this.buffer = newBuffer;
                return this;
        }
        
        
        public DataBuffer write (byte[] buffer)
        {
                this.checkBufferBoundaries(buffer.length);
                this.buffer.put(buffer);
                return this;
        }
        
        
        public int readByte ()
        {
                return this.buffer.get();
        }
        
        
        public DataBuffer writeByte (byte value)
        {
                this.checkBufferBoundaries(1);
                this.buffer.put(value);
                return this;
        }
        
        
        public short readShort ()
        {
                return this.buffer.getShort();
        }
        
        
        public DataBuffer writeShort (short value)
        {
                this.checkBufferBoundaries(2);
                this.buffer.putShort(value);
                return this;
        }
        
        
        public int readInt ()
        {
                return this.buffer.getInt();
        }
        
        
        public DataBuffer writeInt (int value)
        {
                this.checkBufferBoundaries(4);
                this.buffer.putInt(value);
                return this;
        }
        
        
        public int readVarInt (final int bytesLimit)
        {
                return VarInt.read(this.buffer, bytesLimit);
        }
        
        
        public int readVarInt ()
        {
                return VarInt.read(this.buffer);
        }
        
        
        public DataBuffer writeVarInt (int value)
        {
                this.checkBufferBoundaries(VarInt.getByteSize(value));
                VarInt.write(buffer, value);
                return this;
        }
        
        
        public long readLong ()
        {
                return this.buffer.getLong();
        }
        
        
        public DataBuffer writeLong (long value)
        {
                this.checkBufferBoundaries(8);
                this.buffer.putLong(value);
                return this;
        }
        
        
        public long readVarLong (final int bytesLimit)
        {
                return VarLong.read(this.buffer, bytesLimit);
        }
        
        
        public long readVarLong ()
        {
                return VarLong.read(this.buffer);
        }
        
        
        public DataBuffer writeVarLong (long value)
        {
                this.checkBufferBoundaries(VarLong.getByteSize(value));
                VarLong.write(buffer, value);
                return this;
        }
        
}
