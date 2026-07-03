package net.octocore.data;

@FunctionalInterface
public interface BufferWriter
{
        
        <T> void write (Serializer<T> serializer, T object);
        
        
        default void writeInt (int value)
        {
                this.write(Serializer.INTEGER, value);
        }
        
}
