package net.octocore.data;

import java.nio.ByteBuffer;


@FunctionalInterface
public interface Deserializer<T>
{
        
        T deserialize (ByteBuffer buffer);
        
        
        Deserializer<Integer> INTEGER = ByteBuffer::getInt;
        
}
