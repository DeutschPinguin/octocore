package net.octocore.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public final class NetworkHandler
        implements Runnable, AutoCloseable
{
        private static final Logger LOGGER = LogManager.getLogger("NetworkHandler");
        private final ServerSocketChannel serverChannel;
        private final Selector selector;
        
        
        private NetworkHandler (final ServerSocketChannel serverChannel, final Selector serverSelector)
        {
                this.serverChannel = serverChannel;
                this.selector = serverSelector;
        }
        
        
        public static NetworkHandler open (final int port)
        {
                try
                {
                        var serverChannel = ServerSocketChannel.open();
                        serverChannel.bind(new InetSocketAddress(port));
                        serverChannel.configureBlocking(false);
                        var selector = Selector.open();
                        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
                        serverChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
                        LOGGER.info("Server's listening on port {}", port);
                        return new NetworkHandler(serverChannel, selector);
                }
                catch (Exception e)
                {
                        LOGGER.fatal("Failed to open server socket connection: ", e);
                        throw new RuntimeException(e);
                }
        }
        
        
        private void acceptNewClient (final SelectionKey key, final ServerSocketChannel channel)
                throws IOException
        {
                var clientChannel = channel.accept();
                if (clientChannel == null) return;
                clientChannel.configureBlocking(false);
                clientChannel.register(this.selector, SelectionKey.OP_READ, new ClientView());
        }
        
        
        private void transferClientData (final SelectionKey key, final SocketChannel channel)
                throws IOException
        {
                var client = (ClientView) key.attachment();
                var buffer = ByteBuffer.allocate(1024);
                
                int len = channel.read(buffer);
                
                if (len == -1)
                {
                        key.cancel();
                        channel.close();
                }
                else if (len < 1) return;
                
                buffer.flip();
                client.receiveDataFromClient(buffer);
                
                buffer.clear();
                client.sendDataToClient(buffer);
                
                buffer.flip();
                while (buffer.hasRemaining()) channel.write(buffer);
        }
        
        
        private void handleConnection (final SelectionKey key)
                throws Exception
        {
                if (key.isAcceptable() && key.channel() instanceof ServerSocketChannel channel)
                {
                        this.acceptNewClient(key, channel);
                }
                else if (key.isReadable() && key.channel() instanceof SocketChannel channel)
                {
                        this.transferClientData(key, channel);
                }
        }
        
        
        public void update ()
                throws IOException
        {
                if (this.selector.select() < 1) return;
                var iter = this.selector.selectedKeys().iterator();
                
                while (iter.hasNext())
                {
                        var key = iter.next();
                        iter.remove();
                        if (!key.isValid()) continue;
                        try
                        {
                                this.handleConnection(key);
                        }
                        catch (Exception e)
                        {
                                LOGGER.error("Dropping client connection due to the error: ", e);
                                key.channel().close();
                        }
                }
        }
        
        
        @Override
        public void run ()
        {
                try
                {
                        while (this.serverChannel.isOpen())
                        {
                                this.update();
                        }
                }
                catch (Exception e)
                {
                        LOGGER.fatal("Critical error encoutered in a server network logic: ", e);
                        throw new RuntimeException(e);
                }
        }
        
        
        @Override
        public void close ()
                throws IOException
        {
                LOGGER.info("Closing server socket connections");
                this.selector.wakeup();
                this.serverChannel.close();
                this.selector.close();
        }
        
}
