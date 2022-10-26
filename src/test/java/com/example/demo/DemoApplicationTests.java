package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	private final ObjectMapper objectMapper;

	@Autowired
	public DemoApplicationTests(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Test
	void contextLoads() {
		Assertions.assertNotNull(this.objectMapper);
	}

	@Test
	void shouldDeserializeUsingCustom() {
		Assertions.assertDoesNotThrow(() -> {
			System.out.println("Serializing:");
			var eventCreated = new EventCreated("Order", new OrderDTO());
			var serialized = objectMapper.writeValueAsString(eventCreated);

			System.out.println("Deserializing:");
			var deserialized = objectMapper.readValue(serialized, EventCreated.class);

			Assertions.assertEquals("Order", deserialized.getType());
			Assertions.assertEquals("OrderDTO", deserialized.getResource().getName());
		});
	}
}
