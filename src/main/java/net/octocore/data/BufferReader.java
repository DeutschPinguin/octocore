package net.octocore.data;

@FunctionalInterface
public interface BufferReader
{
        
        <T> T read (Deserializer<T> deserializer);
        
        
        default int readInt ()
        {
                return this.read(Deserializer.INTEGER);
        }
        
}
