package net.octocore.data;

import java.nio.ByteBuffer;


@FunctionalInterface
public interface Serializer<T>
{
        
        void serialize (ByteBuffer buffer, T object);
        
        
        Serializer<Integer> INTEGER = ByteBuffer::putInt;
        
}
