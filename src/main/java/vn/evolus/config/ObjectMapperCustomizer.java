package vn.evolus.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.evolus.model.Car;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ObjectMapperCustomizer {

    private static final Map<Class<?>, IJsonProcessor> JSON_PROCESSOR = new HashMap<>();

    public ObjectMapperCustomizer() {
        JSON_PROCESSOR.put(Car.class, new CarJsonProcessor());
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(List.class, new ListJsonSerializer());
        module.addSerializer(Object.class, new ObjectJsonSerializer());

        mapper.registerModule(module);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper;
    }

    @SuppressWarnings("rawtypes")
    private static class ListJsonSerializer extends StdSerializer<List> {

        protected ListJsonSerializer() {
            super(List.class);
        }

        @Override
        public void serialize(List value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartArray();
            for (Object o : value) {
                gen.writeObject(o);
            }
            gen.writeEndArray();
        }
    }

    private static class ObjectJsonSerializer extends StdSerializer<Object> {

        protected ObjectJsonSerializer() {
            super(Object.class);
        }

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeStartObject();

            gen.writeEndObject();
        }
    }

    private interface IJsonProcessor {
        void process();
    }

    private static class CarJsonProcessor implements IJsonProcessor {

        @Override
        public void process() {

        }
    }
}
