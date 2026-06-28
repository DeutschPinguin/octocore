package net.octocore.network.datatype;

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
        
        
        

}
