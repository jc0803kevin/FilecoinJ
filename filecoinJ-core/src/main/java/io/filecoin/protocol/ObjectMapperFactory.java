package io.filecoin.protocol;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.deserializer.RawResponseDeserializer;

/**
 * Factory for managing our ObjectMapper instances.
 */
public class ObjectMapperFactory {

    private static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper();

    static {
        configureObjectMapper(DEFAULT_OBJECT_MAPPER, false);
    }

    public static ObjectMapper getObjectMapper() {
        return getObjectMapper(false);
    }

    public static ObjectMapper getObjectMapper(boolean shouldIncludeRawResponses) {
        if (!shouldIncludeRawResponses) {
            return DEFAULT_OBJECT_MAPPER;
        }

        return configureObjectMapper(new ObjectMapper(), true);
    }

    public static ObjectReader getObjectReader() {
        return DEFAULT_OBJECT_MAPPER.reader();
    }

    private static ObjectMapper configureObjectMapper(
            ObjectMapper objectMapper, boolean shouldIncludeRawResponses) {
        if (shouldIncludeRawResponses) {
            SimpleModule module = new SimpleModule();
            module.setDeserializerModifier(
                    new BeanDeserializerModifier() {
                        @Override
                        public JsonDeserializer<?> modifyDeserializer(
                                DeserializationConfig config,
                                BeanDescription beanDesc,
                                JsonDeserializer<?> deserializer) {
                            if (Response.class.isAssignableFrom(beanDesc.getBeanClass())) {
                                return new RawResponseDeserializer(deserializer);
                            }

                            return deserializer;
                        }
                    });

            objectMapper.registerModule(module);
        }

        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 忽略不存在的属性字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        // 首字母大写
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);

        // 使用标准的字段名映射方式
        objectMapper.configure(MapperFeature.USE_STD_BEAN_NAMING, true);

        return objectMapper;
    }
}
