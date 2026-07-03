package net.octocore.data;

public final class ValueTransfer
        implements BufferReader, BufferWriter
{
        private final ValueInput input = new ValueInput();
        private final ValueOutput output = new ValueOutput();
        
        
        @Override
        public <T> T read (Deserializer<T> deserializer)
        {
                return this.input.read(deserializer);
        }
        
        
        @Override
        public <T> void write (Serializer<T> serializer, T object)
        {
                this.output.write(serializer, object);
        }
        
}
