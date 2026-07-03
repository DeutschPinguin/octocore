package net.octocore.data;

import java.nio.ByteBuffer;


public class ValueInput
        implements BufferReader
{
        protected final ByteBuffer buffer = ByteBuffer.allocate(1024);
        
        
        @Override
        public <T> T read (Deserializer<T> deserializer)
        {
                return deserializer.deserialize(this.buffer);
        }
        
}
