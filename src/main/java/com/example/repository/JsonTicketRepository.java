package com.example.repository;

import com.example.model.Ticket;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonTicketRepository implements TicketRepository {

    private final String filePath;

    public JsonTicketRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Ticket> findAll() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (in == null) {
                throw new IllegalArgumentException("Файл не найден в resources: " + filePath);
            }
            Map<String, List<Ticket>> map = mapper.readValue(in, new TypeReference<>() {});
            return map.get("tickets");
        }
    }
}
