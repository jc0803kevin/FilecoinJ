package io.filecoin.protocol.core.methods.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.filecoin.protocol.ObjectMapperFactory;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.IOException;
import java.io.Serializable;

public class FilecoinClientVersion extends Response<FilecoinClientVersion.ClientVersion> {

    @Override
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonDeserialize(using = FilecoinClientVersion.ResponseDeserialiser.class)
    public void setResult(ClientVersion result) {
        super.setResult(result);
    }

    public ClientVersion getClientVersion() {
        return getResult();
    }


    public static class ResponseDeserialiser extends JsonDeserializer<ClientVersion> {

        private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

        @Override
        public ClientVersion deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {

            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                ClientVersion clientVersion = objectReader.readValue(jsonParser, ClientVersion.class);
                return clientVersion;
            } else {
                return null; // null is wrapped by Optional in above getter
            }
        }
    }


    public static class ClientVersion implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        @JsonProperty("Version")
        private String version;
        @JsonProperty("APIVersion")
        private String apiVersion;
        @JsonProperty("BlockDelay")
        private String blockDelay;

        public ClientVersion() {
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getApiVersion() {
            return apiVersion;
        }

        public void setApiVersion(String apiVersion) {
            this.apiVersion = apiVersion;
        }

        public String getBlockDelay() {
            return blockDelay;
        }

        public void setBlockDelay(String blockDelay) {
            this.blockDelay = blockDelay;
        }
    }

}
