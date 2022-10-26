package com.example.demo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EventCreatedDeserializer extends JsonDeserializer<EventCreated> {

    @Autowired
    public EventCreatedDeserializer(ObjectMapper objectMapper) {
        super();
    }

    @Override
    public EventCreated deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        System.out.println("calling EventCreatedDeserializer");

        var mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        // determine which type (in real code, this uses an injected helper object which maps string types to their descriptions)
        var type = node.get("type").asText();

        DTO dto;
        if (type.equals("Order")) {
            dto = new OrderDTO();
        } else if (type.equals("Product")) {
            dto = new ProductDTO();
        } else {
            throw new RuntimeException("The type does not match any known type, cannot deserialize");
        }

        return new EventCreated(type, dto);
    }
}
