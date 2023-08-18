package com.contentcal.contentcal.model;

import java.time.LocalDateTime;

public record Content(
    Integer id,
    String title,
    String desc,
    Status Status,
    Type contentType,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated,
    String Url
) {
    
}
