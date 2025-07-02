package com.farmcollector.dto;

import java.util.List;

public record FarmDetailsDTO(
        String name,
        String location,
        int fieldCount,
        List<String> crops
) {}

