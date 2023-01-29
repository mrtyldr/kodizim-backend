package com.kodizim.kodizimblog.valueobjects;

import java.util.UUID;

public record AddCommentCommand(
        UUID blogId,
        UUID userId,
        String content
) {
}
