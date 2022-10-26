package com.example.demo;

public class EventCreated implements MessagingEvent {

    private final String type;
    private final DTO dto;

    public EventCreated(String type, DTO dto) {
        this.type = type;
        this.dto = dto;
    }

    public String getType() {
        return this.type;
    }

    public DTO getResource() {
        return this.dto;
    }

    @Override
    public String getName() {
        return "EventCreated(%s)".formatted(this.dto.getName());
    }
}
