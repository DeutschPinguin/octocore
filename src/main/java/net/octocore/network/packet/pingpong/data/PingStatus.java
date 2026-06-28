package net.octocore.network.packet.pingpong.data;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.JsonNodeFactory;
import tools.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PingStatus
{
        protected final List<Sample> samples = new ArrayList<>();
        protected String versionName, favicon;
        protected int versionProtocol, playersLimit, playersOnline;
        protected ObjectNode description;
        protected boolean enforcesSecureChat;
        
        
        public ObjectNode serialize ()
        {
                var node = JsonNodeFactory.instance.objectNode();
                
                node.putObject("version")
                        .put("name", this.versionName)
                        .put("protocol", this.versionProtocol);
                
                var playersArrayNode = node.putObject("players")
                        .put("max", this.playersLimit)
                        .put("online", this.playersOnline)
                        .putArray("sample");
                for (var sample : this.samples) playersArrayNode.add(sample.serialize());
                
                node.set("description", this.description);
                node.put("favicon", this.favicon);
                node.put("enforcesSecureChat", this.enforcesSecureChat);
                
                return node;
        }
        
        
        public static PingStatus deserialize (final ObjectNode node)
        {
                var status = new PingStatus();
                
                if (!node.has("version")) return status;
                
                var versionNode = node.get("version").asObject();
                status.versionName = versionNode.path("name").asString(null);
                status.versionProtocol = versionNode.path("protocol").asInt(0);
                
                var playersNode = node.get("players");
                if (playersNode != null)
                {
                        status.playersLimit = playersNode.path("max").asInt(0);
                        status.playersOnline = playersNode.path("online").asInt(0);
                        
                        for (var sampleNode : playersNode.path("sample").asArray())
                        {
                                var sample = Sample.deserialize(sampleNode.asObject());
                                status.samples.add(sample);
                        }
                }
                
                var descriptionNode = node.get("description");
                if (descriptionNode instanceof ObjectNode) status.description = descriptionNode.asObject();
                
                status.favicon = node.path("favicon").asString(null);
                status.enforcesSecureChat = node.path("enforcesSecureChat").asBoolean(false);
                
                return status;
        }
        
        
        public List<Sample> samples ()
        {
                return this.samples;
        }
        
        
        public final PingStatus addSamples (Sample... samples)
        {
                for (var sample : samples) this.samples().add(sample);
                return this;
        }
        
        
        public String getVersionName ()
        {
                return this.versionName;
        }
        
        
        public PingStatus setVersionName (final String versionName)
        {
                this.versionName = versionName;
                return this;
        }
        
        
        public String getFavicon ()
        {
                return this.favicon;
        }
        
        
        public PingStatus setFavicon (final String favicon)
        {
                this.favicon = favicon;
                return this;
        }
        
        
        public int getVersionProtocol ()
        {
                return this.versionProtocol;
        }
        
        
        public PingStatus setVersionProtocol (final int versionProtocol)
        {
                this.versionProtocol = versionProtocol;
                return this;
        }
        
        
        public int getPlayersOnline ()
        {
                return this.playersOnline;
        }
        
        
        public PingStatus setPlayersOnline (final int playersOnline)
        {
                this.playersOnline = playersOnline;
                return this;
        }
        
        
        public int getPlayersLimit ()
        {
                return this.playersLimit;
        }
        
        
        public PingStatus setPlayersLimit (final int playersLimit)
        {
                this.playersLimit = playersLimit;
                return this;
        }
        
        
        public ObjectNode getDescription ()
        {
                return this.description;
        }
        
        
        public PingStatus setDescription (final ObjectNode description)
        {
                this.description = description;
                return this;
        }
        
        
        public boolean isEnforcseSecureChat ()
        {
                return this.enforcesSecureChat;
        }
        
        
        public PingStatus setEnforcesSecureChat (final boolean enforceSecureChat)
        {
                this.enforcesSecureChat = enforceSecureChat;
                return this;
        }
        
        
        public record Sample(String name, UUID id)
        {
                
                public Sample (final String name)
                {
                        this(name, UUID.randomUUID());
                }
                
                
                public JsonNode serialize ()
                {
                        var node = JsonNodeFactory.instance.objectNode();
                        node.put("name", this.name);
                        node.put("id", this.id.toString());
                        return node;
                }
                
                
                public static Sample deserialize (JsonNode node)
                {
                        var name = node.path("name").asString("");
                        var id = UUID.fromString(node.path("id").asString(""));
                        return new Sample(name, id);
                }
                
        }
        
}
