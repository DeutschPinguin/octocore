package net.octocore.data;

import java.nio.ByteBuffer;


public class ValueOutput
        implements BufferWriter
{
        protected final ByteBuffer buffer = ByteBuffer.allocate(1024);
        
        
        @Override
        public <T> void write (Serializer<T> serializer, T object)
        {
                serializer.serialize(this.buffer, object);
        }
        
}
