package com.kodizim.kodizimblog.dto;

import java.time.OffsetDateTime;

public record BlogDto(
        String title,
        String content,
        OffsetDateTime createdOn
) {
}
