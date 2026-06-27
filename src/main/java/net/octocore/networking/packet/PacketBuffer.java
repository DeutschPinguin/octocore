package net.octocore.networking.packet;

import net.octocore.datatype.VarInt;
import net.octocore.datatype.VarLong;

import java.nio.ByteBuffer;


public class PacketBuffer
{
        protected ByteBuffer buffer;
        
        
        public PacketBuffer (ByteBuffer buffer)
        {
                this.buffer = buffer;
        }
        
        
        public PacketBuffer (byte[] buffer)
        {
                this(ByteBuffer.wrap(buffer));
        }
        
        
        public PacketBuffer (byte[] buffer, int offset, int length)
        {
                this(ByteBuffer.wrap(buffer, offset, length));
        }
        
        
        public PacketBuffer (int capacity)
        {
                this(ByteBuffer.allocate(capacity));
        }
        
        
        public PacketBuffer ()
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
        
        
        public PacketBuffer point (int index)
        {
                this.buffer.position(index);
                return this;
        }
        
        
        public final PacketBuffer move (int offset)
        {
                this.point(this.getPointer() + offset);
                return this;
        }
        
        
        public PacketBuffer rewind ()
        {
                this.buffer.rewind();
                return this;
        }
        
        
        public PacketBuffer flip ()
        {
                this.buffer.flip();
                return this;
        }
        
        
        public byte[] getArray ()
        {
                return this.buffer.array();
        }
        
        
        public PacketBuffer checkBufferBoundaries (int offset)
        {
                int size = this.buffer.capacity(), neededSpace = this.buffer.position() + offset - size + 1;
                if (neededSpace <= 0) return this;
                var newBuffer = ByteBuffer.allocate(size + neededSpace).put(this.buffer.flip());
                this.buffer = newBuffer;
                return this;
        }
        
        
        public PacketBuffer write (byte[] buffer)
        {
                this.checkBufferBoundaries(buffer.length);
                this.buffer.put(buffer);
                return this;
        }
        
        
        public int readByte ()
        {
                return this.buffer.get();
        }
        
        
        public PacketBuffer writeByte (byte value)
        {
                this.checkBufferBoundaries(1);
                this.buffer.put(value);
                return this;
        }
        
        
        public int readShort ()
        {
                return this.buffer.getShort();
        }
        
        
        public PacketBuffer writeShort (short value)
        {
                this.checkBufferBoundaries(2);
                this.buffer.putShort(value);
                return this;
        }
        
        
        public int readInt ()
        {
                return this.buffer.getInt();
        }
        
        
        public PacketBuffer writeInt (int value)
        {
                this.checkBufferBoundaries(4);
                this.buffer.putInt(value);
                return this;
        }
        
        
        public int readVarInt ()
        {
                return VarInt.read(this.buffer);
        }
        
        
        public PacketBuffer writeVarInt (int value)
        {
                this.checkBufferBoundaries(VarInt.getByteSize(value));
                VarInt.write(buffer, value);
                return this;
        }
        
        
        public long readLong ()
        {
                return this.buffer.getLong();
        }
        
        
        public PacketBuffer writeLong (long value)
        {
                this.checkBufferBoundaries(8);
                this.buffer.putLong(value);
                return this;
        }
        
        
        public long readVarLong ()
        {
                return VarLong.read(this.buffer);
        }
        
        
        public PacketBuffer writeVarLong (long value)
        {
                this.checkBufferBoundaries(VarLong.getByteSize(value));
                VarLong.write(buffer, value);
                return this;
        }
        
}
